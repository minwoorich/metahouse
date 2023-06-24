package com.multi.metahouse.chat.repository.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		System.out.println("dao 메소드 실행!");
    	List<ChatroomDTO> chatrooms = sqlSession.selectList("getChatroomById", userId);
        return chatrooms;
    }
    
//    @Override
//    public String getLastMsgDateById(int chatroom_id) {
//    	System.out.println("dao 메소드 실행!! chatroom_id = " + chatroom_id);
//    	Date last_msg_date = sqlSession.selectOne("getLastMsgDateById", chatroom_id);
//    	System.out.println("쿼리결과:" + last_msg_date);
//    	String last_msg_date_str = "";
//    	/* Date 포맷 가공 */
//    	// Date가 null 이 아닌경우에만 포맷
//    	if(last_msg_date != null) {
//    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        	last_msg_date_str = format.format(last_msg_date);
//        	System.out.println("가공된 Date:" + last_msg_date_str);
//    	}
//    	
//        return last_msg_date_str;
//    }

    // 추가적인 메서드들을 필요에 따라 구현합니다.
}
