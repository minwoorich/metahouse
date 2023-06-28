package com.multi.metahouse.chat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.chat.repository.dao.ChatDAO;
import com.multi.metahouse.domain.dto.chat.ChatMsgDTO;
import com.multi.metahouse.domain.dto.chat.ChatProfileDTO;
import com.multi.metahouse.domain.dto.chat.ChatroomDTO;

@Service
public class ChatServiceImpl implements ChatService {

	ChatDAO dao;
	
	@Autowired
	public ChatServiceImpl(ChatDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public void createChatroom(ChatroomDTO chatroomDTO) {
		// TODO Auto-generated method stub

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
//			System.out.println("loginUser : " + loginUser);
//			System.out.println("chatrooms.get(i).getUser_1_id() : " + chatrooms.get(i).getUser_1_id());
//			System.out.println("chatrooms.get(i).getUser_2_id() : " + chatrooms.get(i).getUser_2_id());
			// 세션 로그인 유저와 채팅방 오픈 유저가 같다면
			if(loginUser.equals(chatrooms.get(i).getUser_1_id())) {
//				System.out.println("세션 유저와 채팅방 오픈 유저가 같다");
				target = chatrooms.get(i).getUser_2_id();
			}else {
//				System.out.println("세션 유저와 채팅방 오픈 유저가 다르다");
				target = chatrooms.get(i).getUser_1_id();
			}
			
//			System.out.println("target : " + target);
			
			profiles.add(dao.getProfileById(target));
			
//			System.out.println("profiles.get("+i+") : " + profiles.get(i));
		}
		
		return profiles;
	}
	
	/* 채팅방 메시지 조회 */
	@Override
	public List<ChatMsgDTO> getChatMsgById(int chatroomId) {
		return dao.getChatMsgById(chatroomId);
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
	public int insertMessage(ChatMsgDTO chatMsgDTO) {
		return dao.insertMessage(chatMsgDTO);
	}
	
	
	
}
