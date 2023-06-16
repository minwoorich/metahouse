package com.multi.metahouse.chat.repository.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.ChatroomDTO;

@Repository
public interface ChatDAO {
	 void insertChatroom(ChatroomDTO chatroomDTO);
	    void updateChatroom(ChatroomDTO chatroomDTO);
	    void deleteChatroom(int chatroomId);
	    ChatroomDTO getChatroomById(int chatroomId);
	    // 추가적인 메서드들을 필요에 따라 정의합니다.
	}
