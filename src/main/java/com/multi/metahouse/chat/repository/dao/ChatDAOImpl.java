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

    @Override
    public void insertChatroom(ChatroomDTO chatroomDTO) {
        sqlSession.insert("insertChatroom", chatroomDTO);
    }

    @Override
    public void updateChatroom(ChatroomDTO chatroomDTO) {
        sqlSession.update("updateChatroom", chatroomDTO);
    }

    @Override
    public void deleteChatroom(String targetId) {
        sqlSession.delete("deleteChatroom", targetId);
    }

    @Override
    public List<ChatroomDTO> getChatroomById(String userId) {
    	List<ChatroomDTO> chatrooms = sqlSession.selectList("getChatroomById", userId);
        return chatrooms;
    }
    
    @Override
    public List<ChatMsgDTO> getChatMsgById(int chatroomId) {
    	List<ChatMsgDTO> chatMsgs = sqlSession.selectList("getChatMsgById", chatroomId);
    	System.out.println("chatMsgs:" + chatMsgs);
        return chatMsgs;
    }
    
    // 추가적인 메서드들을 필요에 따라 구현합니다.
    public ChatProfileDTO getTargetProfileById(int chatroomId) {
    	ChatProfileDTO target = sqlSession.selectOne("getChatProfileById", chatroomId);
    	System.out.println("target:" + target);
    	return target;
    }
}
