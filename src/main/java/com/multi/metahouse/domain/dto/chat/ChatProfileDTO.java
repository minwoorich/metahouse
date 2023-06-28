package com.multi.metahouse.domain.dto.chat;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("chatprofile")
public class ChatProfileDTO {
	private String user_name;
	private String self_introduction;
	private String thumbnail_store_filename;
;
}
