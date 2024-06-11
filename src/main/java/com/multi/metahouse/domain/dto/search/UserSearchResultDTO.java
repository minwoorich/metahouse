package com.multi.metahouse.domain.dto.search;

import java.util.Date;

import javax.persistence.Column;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.multi.metahouse.domain.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Alias("user")
public class UserSearchResultDTO {
	@NonNull
	private String user_id;
	private String email;
	private String user_name;
	private String password;
	private String phone_number;
	private String gender;
	private String birth;
	private String self_introduction;
	private String create_date;
	private String last_login_date;
	private String user_grade;
	private int mk_agree;
	private int point;
	private String thumbnail_store_filename;
	
	
}
