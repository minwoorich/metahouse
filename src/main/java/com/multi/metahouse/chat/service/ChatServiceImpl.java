package com.multi.metahouse.chat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.chat.repository.dao.ChatDAO;
import com.multi.metahouse.domain.dto.chat.ChatMsgDTO;
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
	public void updateChatroom(ChatroomDTO chatroomDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteChatroom(String targetId) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public Map<String, Object> getChatroomView(String userId) {
//		System.out.println("service 메소드 실행!");
//		
//		Map<String, Object> chatroom_json = new HashMap<>();
//		List<ChatroomDTO> chatrooms = dao.getChatroomById(userId);
//		
//		System.out.println(chatrooms);
//		
////		List<String> chatrooms_last_msg = new ArrayList<>();
////		for(int i=0; i<chatrooms.size(); i++) {
////			chatrooms_last_msg.add(dao.getLastMsgDateById(chatrooms.get(i).getChatroom_id()));
////		}
//		chatroom_json.put("chatrooms", chatrooms);
////		chatroom_json.put("chatrooms_last_msg", chatrooms_last_msg);
//		
//		return chatroom_json;
//	}
	
	@Override
	public List<ChatroomDTO> getChatroomView(String userId) {
		System.out.println("service 메소드 실행!");
		List<ChatroomDTO> chatrooms = dao.getChatroomById(userId);
		System.out.println(chatrooms);
		
		return chatrooms;
	}
	
	// chatMsg 관련
	@Override
	public List<ChatMsgDTO> getChatMsgById(int chatroomId) {
		return null;
	}
	
}
