package com.multi.metahouse.chat.service;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.multi.metahouse.chat.repository.dao.ChatDAO;
import com.multi.metahouse.domain.dto.chat.ChatMsgDTO;
import com.multi.metahouse.domain.dto.chat.ChatMsgFileDTO;
import com.multi.metahouse.domain.dto.chat.ChatProfileDTO;
import com.multi.metahouse.domain.dto.chat.ChatroomDTO;

@Service
public class ChatServiceImpl implements ChatService {

	ChatDAO dao;
	ResourceLoader resourceLoader;
	
	@Autowired
	public ChatServiceImpl(ChatDAO dao, ResourceLoader resourceLoader) {
		super();
		this.dao = dao;
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void createChatroom(String user_1_id, String user_2_id) {
		// 채팅방 중복 체크
		int check = dao.checkChatroom(user_1_id, user_2_id);
		System.out.println("check : " + check);
		if(check == 0) {
			// 매치되는 값이 없음
			dao.createChatroom(user_1_id, user_2_id);
			
		}else {
			System.out.println("채팅방이 중복되었습니다!!! 채팅방을 생성하지 않습니다!!!");
			
		}
		
	}

	@Override
	public void deleteChatroom(String targetId) {
		// TODO Auto-generated method stub

	}
	
	/* 채팅방 조회 */
	@Override
	public List<ChatroomDTO> getChatroomView(String userId) {
		List<ChatroomDTO> chatrooms = dao.getChatroomByUserId(userId);
		return chatrooms;
	}
	
	/* 채팅방 별 상대방 프로필 조회 */
	@Override
	public List<ChatProfileDTO> getProfileList(List<ChatroomDTO> chatrooms, String loginUser) {
		List<ChatProfileDTO> profiles = new ArrayList<>();
		
		String target = "";
		
		for(int i=0; i<chatrooms.size(); i++) {
			// 세션 로그인 유저와 채팅방 오픈 유저가 같다면
			if(loginUser.equals(chatrooms.get(i).getUser_1_id())) {
				target = chatrooms.get(i).getUser_2_id();
			}else {
				target = chatrooms.get(i).getUser_1_id();
			}
			
			profiles.add(dao.getProfileById(target));
			
		}
		
		return profiles;
	}
	
	/* 채팅방 메시지 조회 */
	@Override
	public List<ChatMsgDTO> getChatMsgById(int chatroomId) {
		return dao.getChatMsgById(chatroomId);
	}
	
	@Override
	public List<ChatMsgFileDTO> getChatMsgFileById(int chatMsgId) {
		return dao.getChatMsgFileById(chatMsgId);
	}
	
	/* 채팅 상대방 프로필 조회 */
	@Override
	public ChatProfileDTO getProfileById(int chatroomId, String loginUser) {
		ChatroomDTO chatroom = dao.getChatroomById(chatroomId);
		
		String target = "";
		
		// 세션 로그인 유저와 채팅방 오픈 유저가 같다면
		if(loginUser.equals(chatroom.getUser_1_id()))
			target = chatroom.getUser_2_id();
		else
			target = chatroom.getUser_1_id();
		
		return dao.getProfileById(target);
	}

	/* 채팅 메시지 저장 */
	@Override
	@Transactional
	public int insertMessage(ChatMsgDTO chatMsgDTO) {
		dao.insertMessage(chatMsgDTO);
		return dao.updateLastChat(chatMsgDTO);
	}
	
	/* 채팅 메시지 파일 저장 */
	@Override
	public int insertMessageFile(ChatMsgFileDTO chatMsgFileDTO) {
		return dao.insertMessageFile(chatMsgFileDTO);
	}
	
	/* message_id 얻기 위한 메소드 */
	@Override
	public int getLastInsertID() {
		return dao.getLastInsertID();
	}
	
	/* 채팅방의 채팅 리스트를 받아서 실제 파일을 읽어 리스트로 반환하는 메소드 */
	@Override
	public List<ByteBuffer> getFileListById(List<ChatMsgFileDTO> chatMsgFileList) throws IOException {
		List<ByteBuffer> fileList = new ArrayList<>();
		
		final String FILE_PATH = resourceLoader.getResource("classpath:static/upload").getFile().getAbsolutePath() 
				+ File.separator + "chat" + File.separator + "attach";
		
		FileInputStream fis = null;
		FileChannel channel = null;
		
		for(int i=0; i<chatMsgFileList.size(); i++) {
			String storeFileNameNow = chatMsgFileList.get(i).getFile_store_name();
			
			System.out.println(i+1 + "번째 파일 : " + chatMsgFileList.get(i).getFile_origin_name());
			
			// 파일 열기
			File file = new File(FILE_PATH + File.separator + storeFileNameNow);
			
			// 파일 읽기를 위한 FIS 와 채널 생성
			fis = new FileInputStream(file);
			channel = fis.getChannel();
			
			// 채널의 크기(파일의 크기)와 동일하게 ByteBuffer의 크기를 할당
			ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
//			ByteBuffer retBuffer = ByteBuffer.allocate((int)channel.size());
			ByteBuffer retBuffer = ByteBuffer.allocate((int)channel.size());
			
			System.out.println("파일 : " + file);
			System.out.println("채널 크기 : " + channel.size());
			
			// 버퍼를 읽기
			int bytesRead = channel.read(buffer);
			
			System.out.println("bytesRead : " + bytesRead);
			System.out.println("buffer : " + buffer);
			
//			int test_count = 1;
			
			while (bytesRead != -1) {
//				System.out.println("버퍼를 " + test_count + "번 째 읽고 있습니다.");
				
				// 최종 버퍼에 삽입
//				retBuffer.put(buffer);
				
//				System.out.println("Read " + bytesRead);
				
				// 버퍼의 position 0 으로 이동
				buffer.flip();
				
				while(buffer.hasRemaining()){
//					System.out.print((char)buffer.get());
					retBuffer.put(buffer.get());
//					System.out.println("retBuffer = " + retBuffer.toString());
				}
//				System.out.println();
				
				// 버퍼 초기화
				buffer.clear();
				
				// 새로 buffer에 read
				bytesRead = channel.read(buffer);
				
//				test_count++;
			}
			
			System.out.println("bytesRead(end) : " + bytesRead);
//			for(int j=0; j<retBuffer.capacity(); j++) {
//				System.out.print((char)retBuffer.get(j));
//			}
			
			retBuffer.rewind();
			
			fileList.add(retBuffer);
			
		}
		
		System.out.println("filelist : " + fileList);
		
		return fileList;
	}
	
}
