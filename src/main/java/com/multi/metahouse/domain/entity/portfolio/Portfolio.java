package com.multi.metahouse.domain.entity.portfolio;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.multi.metahouse.domain.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "portfolio")
public class Portfolio {
	@Id
	@NonNull
	private String portfolioId;
	private String userId;
	private String category1;
	private String category2;
	private String portfolioTitle;
	private Date portfolioPjStartDay;
	private Date portfolioPjEndDay;
	private String mainImg;
	private String mainImgStoreFilename;
	private String portfolioPjContent;
	private String portfolioPjPoint;
	private String portfolioPjStyle;
	private Date portfolioWriteDay;
}
