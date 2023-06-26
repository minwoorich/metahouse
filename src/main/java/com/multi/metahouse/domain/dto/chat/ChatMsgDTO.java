package com.multi.metahouse.domain.dto.chat;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("chatmsg")
public class ChatMsgDTO {
	private int message_id;
	private String writer_id;
	private int chatroom_id;
	private Date write_time;
	private String message_content;
	
}
