package com.multi.metahouse.domain.entity.order;

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
import org.hibernate.annotations.UpdateTimestamp;

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
@ToString(exclude = "orderId")
@Entity
@Table(name = "project_orders_detail")
public class ProjectOrdersDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private Long orederDetailId;
//	private Long orderId;
	@CreationTimestamp
	private LocalDateTime orderDate;
	@UpdateTimestamp
	private LocalDateTime completionDate;
	
	////////////////////////////////////////////
	@OneToOne
	@JoinColumn(name = "order_id")
	private ProjectOrdersEntity orderId;
}
