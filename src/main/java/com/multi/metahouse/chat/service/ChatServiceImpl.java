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
	public void updateChatroom(ChatroomDTO chatroomDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteChatroom(String targetId) {
		// TODO Auto-generated method stub

	}
	
	/* 채팅방 조회 */
	@Override
	public List<ChatroomDTO> getChatroomView(String userId) {
		List<ChatroomDTO> chatrooms = dao.getChatroomById(userId);
		return chatrooms;
	}
	
	/* 채팅방 메시지 조회 */
	@Override
	public List<ChatMsgDTO> getChatMsgById(int chatroomId) {
		return dao.getChatMsgById(chatroomId);
	}
	
	/* 채팅 상대방 프로필 조회 */
	@Override
	public ChatProfileDTO getTargetProfileById(int chatroomId) {
		return dao.getTargetProfileById(chatroomId);
	}

	/* 채팅 메시지 저장 */
	@Override
	public int insertMessage(ChatMsgDTO chatMsgDTO) {
		return dao.insertMessage(chatMsgDTO);
	}
	
	
	
}
