package com.multi.metahouse.domain.entity.review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "asset_review")
public class AssetReviewEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "asset_review_id")
	private Long assetReviewId;
	@Column(name="asset_order_id")
	private Long assetOrderId;
	@Column(name="writer_id")
	private String writerId;
	@Column(name="asset_id")
	private Long assetId;
	private Long rating;
	private String reviewText;
	@CreationTimestamp
	private LocalDateTime reviewDate;
	/////////////////////////////////////
	
	
	/////자식 훔쳐보기///////////////////////////////////////////
	//양방향
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "assetReviewId" )
	private AssetReviewCommentEntity reviewCommentEntity; 
	
	//양방향
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "assetReviewId")
	List<AssetReviewContentsEntity> reviewContentsEntityList = new ArrayList<>();
}
