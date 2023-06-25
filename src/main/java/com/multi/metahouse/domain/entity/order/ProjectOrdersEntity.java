package com.multi.metahouse.domain.entity.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String orderId;
	private String projectId;
	private String buyerId;
	private String projectOptionId;
	private String preOrderStatus;
	private String orderCommitDate;
	private String request;

}
