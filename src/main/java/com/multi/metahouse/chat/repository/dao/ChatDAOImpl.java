package com.multi.metahouse.chat.repository.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.chat.ChatMsgDTO;
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
    public void insertChatroom(ChatroomDTO chatroomDTO) {
        sqlSession.insert("insertChatroom", chatroomDTO);
    }

	/* 채팅방 수정 (필요한지?) */
    @Override
    public void updateChatroom(ChatroomDTO chatroomDTO) {
        sqlSession.update("updateChatroom", chatroomDTO);
    }

    /* 채팅방 삭제 */
    @Override
    public void deleteChatroom(String targetId) {
        sqlSession.delete("deleteChatroom", targetId);
    }

	/* 유저의 채팅방 조회 (view 호출) */
    @Override
    public List<ChatroomDTO> getChatroomById(String userId) {
    	List<ChatroomDTO> chatrooms = sqlSession.selectList("getChatroomById", userId);
        return chatrooms;
    }
    
	/* 채팅방의 메시지 호출 */
    @Override
    public List<ChatMsgDTO> getChatMsgById(int chatroomId) {
    	List<ChatMsgDTO> chatMsgs = sqlSession.selectList("getChatMsgById", chatroomId);
        return chatMsgs;
    }
    
	/* 채팅 상대방의 프로필 호출 */
    public ChatProfileDTO getTargetProfileById(int chatroomId) {
    	ChatProfileDTO target = sqlSession.selectOne("getChatProfileById", chatroomId);
    	System.out.println("target:" + target);
    	return target;
    }

	@Override
	public int insertMessage(ChatMsgDTO chatMsgDTO) {
		return sqlSession.insert("insertChatMsg", chatMsgDTO);
	}
    
}
