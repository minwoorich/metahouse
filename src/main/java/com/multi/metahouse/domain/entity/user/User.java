package com.multi.metahouse.domain.entity.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.multi.metahouse.domain.entity.project.ProjectContentsEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user")
public class User {
	@NonNull
	@Id
	private String userId;
	private String email;
	private String userName;
	private String password;
	private String phoneNumber;
	private String gender;
	private String birth;
	private String selfIntroduction;
	private Date createDate;
	private Date lastLoginDate;
	private String userGrade;
	private boolean mkAgree;
	private int point;
	private String thumbnailStoreFilename;
	private String socialLoginId;
	private String socialName;
	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "creatorId")
//	private List<ProjectEntity> projectEntityList = new ArrayList<>();
}
