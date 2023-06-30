package com.multi.metahouse.domain.dto.chat;

import java.io.File;
import java.util.Date;
import java.util.List;

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
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date write_time;
	private String message_content;
	private List<String> filenamelist;
	private String message_type;
}
