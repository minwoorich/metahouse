package com.multi.metahouse.domain.dto.chat;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("chatroom")
public class ChatroomDTO {
	private int chatroomId;
	private String user1Id;
	private String user2Id;
	private Date openDate;
	
}
