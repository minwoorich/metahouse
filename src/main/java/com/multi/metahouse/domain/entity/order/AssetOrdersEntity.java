package com.multi.metahouse.domain.entity.order;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.multi.metahouse.domain.entity.asset.AssetEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "asset_orders")
public class AssetOrdersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;
	@Column(name="buyer_id2")
	private String buyerId2;
	private LocalDateTime assetOrderDate;

	//////자식참조/////////////////////////////
	//단방향
	@ManyToOne
	@JoinColumn(name = "asset_id")
	private AssetEntity assetId;
}
