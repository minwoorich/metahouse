package com.multi.metahouse.chat.repository.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.chat.ChatMsgDTO;
import com.multi.metahouse.domain.dto.chat.ChatMsgFileDTO;
import com.multi.metahouse.domain.dto.chat.ChatProfileDTO;
import com.multi.metahouse.domain.dto.chat.ChatroomDTO;

@Repository
public class ChatDAOImpl implements ChatDAO {

    SqlSession sqlSession;

    @Autowired
    public ChatDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
	/* 채팅방 생성 */
    @Override
    public void createChatroom(String user_1_id, String user_2_id) {
    	ChatroomDTO chatroomDTO = new ChatroomDTO(user_1_id, user_2_id);
    	chatroomDTO.setOpen_date(new Date());
        sqlSession.insert("insertChatroom", chatroomDTO);
    }
    
	/* 채팅방 중복 체크 */
    @Override
    public int checkChatroom(String user_1_id, String user_2_id) {
    	ChatroomDTO chatroomDTO = new ChatroomDTO(user_1_id, user_2_id);
    	return sqlSession.selectOne("checkChatroom", chatroomDTO);
    }
    
    /* 채팅방 삭제 */
    @Override
    public void deleteChatroom(String targetId) {
        sqlSession.delete("deleteChatroom", targetId);
    }

	/* 유저의 채팅방 조회 (view 호출) */
    @Override
    public List<ChatroomDTO> getChatroomByUserId(String userId) {
    	List<ChatroomDTO> chatrooms = sqlSession.selectList("getChatroomByUserId", userId);
        return chatrooms;
    }
    
    @Override
    public ChatroomDTO getChatroomById(int chatroomId) {
    	ChatroomDTO chatroom = sqlSession.selectOne("getChatroomById", chatroomId);
        return chatroom;
    }
    
	/* 채팅방의 메시지 호출 */
    @Override
    public List<ChatMsgDTO> getChatMsgById(int chatroomId) {
    	List<ChatMsgDTO> chatMsgs = sqlSession.selectList("getChatMsgById", chatroomId);
        return chatMsgs;
    }
    
	/* 채팅 상대방의 프로필 호출 */
    public ChatProfileDTO getProfileById(String target) {
    	ChatProfileDTO targetProfile = sqlSession.selectOne("getChatProfileById", target);
    	System.out.println("targetProfile:" + targetProfile);
    	return targetProfile;
    }

	@Override
	public int insertMessage(ChatMsgDTO chatMsgDTO) {
		return sqlSession.insert("insertChatMsg", chatMsgDTO);
	}
	
	@Override
	public int insertMessageFile(ChatMsgFileDTO chatMsgFileDTO) {
		return sqlSession.insert("insertChatFileMsg", chatMsgFileDTO);
	}
	
	@Override
	public int updateLastChat(ChatMsgDTO chatMsgDTO) {
		return sqlSession.update("updateLastChat", chatMsgDTO);
	}
	
	/* message_id 얻기 위한 메소드 */
	@Override
	public int getLastInsertID() {
		return sqlSession.selectOne("getLastInsertID");
	}
    
}
