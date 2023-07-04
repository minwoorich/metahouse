package com.multi.metahouse.domain.entity.review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "projectReviewId")
@Entity
@Table(name = "project_review_comment")
public class ProjectReviewCommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_review_comment_id")
	private Long projectReviewCommentId;
//	@Column(name="project_review_id")
//	private Long projectReviewId;
	private String commentWriterId;
	private String commentText;
	@CreationTimestamp
	private LocalDateTime commentDate;
	
	////////////////부모 참조////////////////////
	//양방향
	@OneToOne
	@JoinColumn(name = "project_review_id")
	private ProjectReviewEntity projectReviewId;
	
}
