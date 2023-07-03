package com.multi.metahouse.chat.service;

import java.util.List;
import java.util.Map;

import com.multi.metahouse.domain.dto.chat.ChatMsgDTO;
import com.multi.metahouse.domain.dto.chat.ChatMsgFileDTO;
import com.multi.metahouse.domain.dto.chat.ChatProfileDTO;
import com.multi.metahouse.domain.dto.chat.ChatroomDTO;

public interface ChatService {
 	void createChatroom(String user_1_id, String user_2_id);
    void deleteChatroom(String targetId);
    
    // 현재 세션 id 의 채팅방 조회
    List<ChatroomDTO> getChatroomView(String userId);
    
    
    // 채팅방 별 상대방 프로필 조회
    List<ChatProfileDTO> getProfileList(List<ChatroomDTO> chatrooms, String loginUser);
	// 채팅 상대방 프로필 조회
    ChatProfileDTO getProfileById(int chatroomId, String loginUser);
    
    
    // 채팅 메시지 조회
    List<ChatMsgDTO> getChatMsgById(int chatroomId);

    
    // 채팅 메시지 저장
    int insertMessage(ChatMsgDTO chatMsgDTO);
    // 채팅 메시지 파일 저장
	int insertMessageFile(ChatMsgFileDTO chatMsgFileDTO);
	
	// message_id 얻기 위한 메소드
	int getLastInsertID();
    
}






