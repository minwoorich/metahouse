package com.multi.metahouse.chat.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multi.metahouse.domain.dto.chat.ChatMsgDTO;

@Component
@ServerEndpoint(value="/chat")
public class ChatHandler extends AbstractWebSocketHandler{
	
	private static Set<Session> clientset = Collections.synchronizedSet(new HashSet<Session>());
	private String fileUploadSessionMsg = "";
	private ChatMsgDTO chatMsg = null;
	private int fileIdx = 0;
	private static ChatService service;
	private static ResourceLoader resourceLoader;
	
	public ChatHandler() {
	}
	
	@Autowired
	public ChatHandler(ChatService service, ResourceLoader resourceLoader) {
		super();
		this.service = service;
		this.resourceLoader = resourceLoader;
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
		// JSON 형식 메시지 static 멤버변수에 저장
		fileUploadSessionMsg = msg;
		
		// 수신 메시지 DB에 저장
		ObjectMapper objectMapper = new ObjectMapper();
		chatMsg = objectMapper.readValue(msg, ChatMsgDTO.class);
		
		if(chatMsg.getMessage_type().equals("Text")) {
			System.out.println("Text Only 메시지 수신됨.");
			
			// 수신 메시지 DB에 저장
			service.insertMessage(chatMsg);
			
		}else {
			System.out.println("File 첨부 메시지 수신됨.");
			fileIdx = 0;
			
			// 수신 메시지 DB에 저장
			service.insertMessage(chatMsg);
		}
		
		System.out.println("chatMsg -> " + chatMsg);
		
		//웹소켓에 접속한 모든 웹소켓클라이언트에게 메세지를 전송
		for(Session data:clientset) {
			System.out.println("전송메세지:"+msg);
			data.getBasicRemote().sendText(msg);
		}
	}
	
	@OnMessage
	public void onMessage(ByteBuffer msg, Session session) throws IOException {
		//System.out.println("바이너리 수신 메세지(ByteBuffer):" + msg);
		//System.out.println(resourceLoader.getResource("classpath:static/upload").getFile().getAbsolutePath());
		
		// 첨부파일 절대 경로 지정
		final String FILE_PATH = resourceLoader.getResource("classpath:static/upload").getFile().getAbsolutePath() 
				+ File.separator + "chat" + File.separator + "attach";
		
		//System.out.println(FILE_PATH);
		
		// 파일 디렉토리 생성 (없으면)
		File dir = new File(FILE_PATH);
        if(!dir.exists()) {
            dir.mkdirs();
        }
		
        // 실제 파일 확장자 추출
        int pos = chatMsg.getFilenamelist().get(fileIdx).lastIndexOf(".");
        String ext = chatMsg.getFilenamelist().get(fileIdx).substring(pos+1);
		String filename = UUID.randomUUID().toString() + "." + ext;
		
		fileIdx++;
		
		File attachedFile = new File(FILE_PATH, filename);
		FileOutputStream out = null;
        FileChannel outChannel = null;
        
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
	
//	@Override
//	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
//		System.out.println("바이너리 메세지 수신: " + message);
//	}
}
