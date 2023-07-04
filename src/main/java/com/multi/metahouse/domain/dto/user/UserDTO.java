package com.multi.metahouse.domain.dto.user;

import java.util.Date;

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
@Alias("member")
public class UserDTO {
	@NonNull
	private String userID;
	private String email;
	private String userName;
	private String password;
	private String phoneNumber;
	private String gender;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date birth;
	private String selfIntroduction;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date createDate;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date lastLoginDate;
	private String userGrade;
	private int mkAgree;
	private int point;
	private String thumbnailStoreFilename;
	private String socialLoginId;
	private String socialName;
	
	
}
