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
import org.springframework.data.annotation.CreatedDate;

import com.multi.metahouse.domain.entity.order.ProjectOrdersEntity;
import com.multi.metahouse.domain.entity.project.AddOptionEntity;
import com.multi.metahouse.domain.entity.project.ProjectContentsEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.project.ProjectPackageSingleEntity;
import com.multi.metahouse.domain.entity.project.ProjectPackageTripleEntity;
import com.multi.metahouse.domain.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "project_review")
public class ProjectReviewEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_review_id")
	private Long projectReviewId;
//	@Column(name="project_order_id")
//	private Long projectOrderId;
//	@Column(name="writer_id")
//	private String writerId;
//	@Column(name="project_id")
//	private Long projectId;
	private Long rating;
	private String reviewText;
	@CreationTimestamp
	private LocalDateTime reviewDate;
	/////////////////////////////////////
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_order_id")
	private ProjectOrdersEntity projectOrderId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "writer_id")
	private User writerId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private ProjectEntity projectId;
	
	//////////////////////////////////////////////////////////////
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "projectReviewId")
	private ProjectReviewCommentEntity reviewCommentEntity; 
	
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "projectReviewId")
	List<ProjectReviewContentsEntity> reviewContentsEntityList = new ArrayList<>();
}
