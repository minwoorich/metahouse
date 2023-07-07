package com.multi.metahouse.domain.entity.review;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@ToString(exclude = "assetReviewId")
@Entity
@Table(name = "asset_review_contents")
public class AssetReviewContentsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="review_contents_id")
	private Long reviewContentsId;
	private String reviewStoreFilename;
	private Long reviewImgNo;
	
	////부모 참조/////////////////////////
	//양방향
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_review_id")
	private AssetReviewEntity assetReviewId;
}
