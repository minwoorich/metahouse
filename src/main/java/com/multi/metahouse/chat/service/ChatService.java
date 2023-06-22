package com.multi.metahouse.chat.service;

import java.util.List;

import com.multi.metahouse.domain.dto.ChatroomDTO;

public interface ChatService {

	 void createChatRoom(ChatroomDTO chatRoomDTO);
	    void updateChatroom(ChatroomDTO chatRoomDTO);
	    void deleteChatroom(int chatroomId);
	    ChatroomDTO getChatroomById(int chatroomId);
	    List<ChatroomDTO> getAllChatRooms();
	}






