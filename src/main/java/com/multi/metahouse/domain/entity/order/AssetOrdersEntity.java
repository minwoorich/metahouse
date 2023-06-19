package com.multi.metahouse.domain.entity.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asset_orders")
public class AssetOrdersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String orderId;
	private String assetId;
	private String buyerId2;
	private String orderNumber;
	private String assetOrderDate;
}
