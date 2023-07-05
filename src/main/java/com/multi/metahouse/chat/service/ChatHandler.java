package com.multi.metahouse.chat.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.multi.metahouse.domain.dto.chat.ChatMsgDTO;
import com.multi.metahouse.domain.dto.chat.ChatMsgFileDTO;

@Component
@ServerEndpoint(value="/chat")
public class ChatHandler{
	// 프로그래밍 방식의 트랜잭션 처리를 위한 PlatformTransactionManager 생성
    private static PlatformTransactionManager transactionManager;
    
    // 채팅 유저 세션 Set 생성
	private static Set<Session> clientset = Collections.synchronizedSet(new HashSet<Session>());
	
	// Text 메시지 (JSON String) 임시 저장 변수 선언
	private String originJsonString = "";
	
	// Binary 메시지 임시 저장 변수 선언
	private List<ByteBuffer> filelist = new ArrayList<>();
	
	// service 메소드에 사용될 DTO 변수 저장
	private ChatMsgDTO chatMsg = null;
	private List<ChatMsgFileDTO> chatMsgFileList = new ArrayList<>();
	
	// File Sequence 구분을 위한 Index 변수 선언
	private int fileIdx = 0;
	
	private static ChatService service;
	private static ResourceLoader resourceLoader;
	
	private static String FILE_PATH;
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("application");
	
	public ChatHandler() {
	}
	
	@Autowired
	public ChatHandler(ChatService service, ResourceLoader resourceLoader, PlatformTransactionManager transactionManager) {
		super();
		this.service = service;
		this.resourceLoader = resourceLoader;	
		this.transactionManager = transactionManager;
	}

	@OnOpen
	public void onOpen(Session client) {
		if(!clientset.contains(client)) {
			clientset.add(client);
			System.out.println("클라이언트가 접속했습니다:"+client);
		}
	}
	
	@OnMessage
	public void onMessage(String msg, Session session) throws IOException {
		System.out.println("텍스트 수신메세지:"+msg);
		// JSON 형식 메시지 전역 멤버변수에 저장
		originJsonString = msg;
		
		// 수신 메시지 DB에 저장
		ObjectMapper objectMapper = new ObjectMapper();
		chatMsg = objectMapper.readValue(msg, ChatMsgDTO.class);
		
		//System.out.println("chatMsg : " + chatMsg);
		
		if(chatMsg.getMessage_type().equals("Text")) {
			//System.out.println("Text Only 메시지 수신됨.");
			
			// 수신 메시지 DB에 저장
			insertChatMsg(chatMsg);
			
			//웹소켓에 접속한 모든 웹소켓클라이언트에게 메세지를 전송
			for(Session data:clientset) {
				System.out.println("전송메세지:"+msg);
				data.getBasicRemote().sendText(msg);
			}
			
		}else {
			//System.out.println("File 첨부 메시지 수신됨.");
			// 신규 메시지 수신으로 인한 기존 데이터 비우기
			fileIdx = 0;
			filelist.clear();
			chatMsgFileList.clear();
		}
		
	}
	
	@OnMessage
	public void onMessage(ByteBuffer msg, Session session) throws IOException {
		//System.out.println("바이너리 수신메세지:"+msg);
		// ArrayBuffer 형식 메시지 static 멤버변수에 저장
		filelist.add(0, msg);
				
		// 첨부파일 절대 경로 지정
		//final String FILE_PATH = resourceLoader.getResource("classpath:static/upload").getFile().getAbsolutePath() 
		//		+ File.separator + "chat" + File.separator + "attach";
		
		FILE_PATH = bundle.getString("file.directory");
		
		// 파일 디렉토리 생성 (없으면)
		System.out.println(FILE_PATH);
		File dir = new File(FILE_PATH);
        if(!dir.exists()) {
            dir.mkdirs();
        }
		
        // 실제 파일 확장자 추출
        int pos = chatMsg.getFilenamelist().get(fileIdx).lastIndexOf(".");
        String ext = chatMsg.getFilenamelist().get(fileIdx).substring(pos+1);
		String filename = UUID.randomUUID().toString() + "." + ext;
		
		// DTO 저장
		ChatMsgFileDTO file = new ChatMsgFileDTO();
		file.setFile_origin_name(chatMsg.getFilenamelist().get(fileIdx));
		file.setFile_store_name(filename);
		file.setFile_seq(fileIdx);
		
		chatMsgFileList.add(file);
		
		// 새 파일 생성 
		File attachedFile = new File(FILE_PATH, filename);
		FileOutputStream out = null;
        FileChannel outChannel = null;
        
        // 업로드 될 파일 작성
        try {
        	msg.flip(); //byteBuffer를 읽기 위해 세팅
            out = new FileOutputStream(attachedFile, true); //생성을 위해 OutputStream을 연다.
            outChannel = out.getChannel(); //채널을 열고
            msg.compact(); //파일을 복사한다.
            outChannel.write(msg); //파일을 쓴다.
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null) {
                    out.close();
                }
                if(outChannel != null) {
                    outChannel.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        msg.position(0); //파일을 저장하면서 position값이 변경되었으므로 0으로 초기화한다.
        
        // 파일 정보 DB 저장
 		if(fileIdx+1 == chatMsg.getFilenamelist().size()) {
 			insertChatMsgFile(chatMsg, chatMsgFileList);
 			
 			ObjectMapper mapper = new ObjectMapper();
 			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
 			mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
 			String modifiedJsonString = mapper.writeValueAsString(chatMsg);
 			
 			//웹소켓에 접속한 모든 웹소켓클라이언트에게 메세지를 전송
 			for(Session data:clientset) {
 				//System.out.println("전송메세지:"+modifiedJsonString);
 				data.getBasicRemote().sendText(modifiedJsonString);
 				// 클라이언트 단에 파일 전송
 				for(ByteBuffer arrayBuffer:filelist) {
// 					System.out.println("전송파일:"+arrayBuffer);
 					data.getBasicRemote().sendBinary(arrayBuffer);
 				}
 			}
 		}
 		
 		// 파일 시퀀스 증가
		fileIdx++;
	}
	
	@OnClose
	public void onClose(Session client) {
		System.out.println("접속종료"+client);
		clientset.remove(client);
	}
	
	@OnError
	public void onError(Session session, Throwable th) {
		th.printStackTrace();
	}
	
	// 트랜잭션 적용을 위한 Text 메시지 저장 메소드
	public void insertChatMsg(ChatMsgDTO chatMsgDTO) {
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		
        try {
        	
        	service.insertMessage(chatMsgDTO);
        	
        	transactionManager.commit(transactionStatus);
        	
		} catch (Exception e) {
			
			transactionManager.rollback(transactionStatus);
			
		}
        
	}
	
	// 트랜잭션 적용을 위한 Binary 메시지 저장 메소드
	public void insertChatMsgFile(ChatMsgDTO chatMsgDTO, List<ChatMsgFileDTO> chatMsgFileList) {
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		
        try {
        	// Message 테이블 저장
        	service.insertMessage(chatMsgDTO);
        	// Message 테이블에 저장된 id 값 호출
        	int msg_id = service.getLastInsertID();
        	
        	// ChatMsgFileDTO 의 message id 값 할당 후 테이블에 저장
        	for(int i=0; i<chatMsgFileList.size(); i++) {
        		ChatMsgFileDTO fileDTO = chatMsgFileList.get(i);
        		fileDTO.setMessage_id(msg_id);
        		service.insertMessageFile(fileDTO);
        	}
        	
        	transactionManager.commit(transactionStatus);
        	
		} catch (Exception e) {
			
			transactionManager.rollback(transactionStatus);
			
		}
	}
	
}
