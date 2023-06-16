package com.multi.metahouse.chat.repository.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.ChatroomDTO;
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
    public void deleteChatroom(int chatroomId) {
        sqlSession.delete("deleteChatroom", chatroomId);
    }

    @Override
    public ChatroomDTO getChatroomById(int chatroomId) {
        return sqlSession.selectOne("getChatroomById", chatroomId);
    }

    // 추가적인 메서드들을 필요에 따라 구현합니다.
}
