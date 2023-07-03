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
@Alias("chatmsgfile")
public class ChatMsgFileDTO {
	private int message_file_id;
	private int message_id;
	private String file_origin_name;
	private String file_store_name;
	private int file_seq;
}
