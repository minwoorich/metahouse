package com.multi.metahouse.domain.entity.review;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.multi.metahouse.domain.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "projectReviewId")
@Entity
@Table(name = "project_review_contents")
public class ProjectReviewContentsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="review_contents_id")
	private Long reviewContentsId;
//	@Column(name="project_review_id")
//	private Long projectReviewId;
	private String reviewStoreFilename;
	private Long reviewImgNo;
	
	////부모 참조/////////////////////////
	//양방향
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_review_id")
	private ProjectReviewEntity projectReviewId;
}
