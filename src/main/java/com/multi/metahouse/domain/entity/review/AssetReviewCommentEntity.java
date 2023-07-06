package com.multi.metahouse.domain.entity.review;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@ToString(exclude = "assetReviewId")
@Entity
@Table(name = "asset_review_comment")
public class AssetReviewCommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "asset_review_comment_id")
	private Long assetReviewCommentId;
	private String commentWriterId;
	private String commentText;
	@CreationTimestamp
	private LocalDateTime commentDate;

	//////////////// 부모 참조////////////////////
	// 양방향
	@OneToOne
	@JoinColumn(name = "asset_review_id")
	private AssetReviewEntity assetReviewId;
}
