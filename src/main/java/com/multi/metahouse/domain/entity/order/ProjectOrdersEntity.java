package com.multi.metahouse.domain.entity.order;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import com.multi.metahouse.domain.entity.review.ProjectReviewEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_orders")
public class ProjectOrdersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;
//	private Long projectId;
	private String buyerId;
	private String orderStatus;
	@CreationTimestamp
	private LocalDateTime orderCommitDate;
	private String request;
	private int orderPrice;

	///////////////////////////부모 참조하기//////////////////
	//단방향
	@ManyToOne
	@JoinColumn(name="project_id")
	ProjectEntity projectId;
	
	//////////////자식 참조하기/////////////////////////////////////////
	//양방향
	@Builder.Default
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "orderId")
	private ProjectOrdersDetailEntity orderDetail = new ProjectOrdersDetailEntity();

	//양방향
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private List<SelectedAddOptionEntity> selectedOptionList = new ArrayList<>();
	
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "project_order_id", referencedColumnName = "order_id")
	private List<ProjectReviewEntity> reviewList = new ArrayList<>();
	

	

}
