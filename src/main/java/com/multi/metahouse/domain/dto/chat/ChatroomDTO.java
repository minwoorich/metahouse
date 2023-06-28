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
	private int chatroom_id;
	private String user_1_id;
	private String user_2_id;
	private Date open_date;
	private String last_chat_time;
	private String last_chat;
	private String user_2_name;
}
