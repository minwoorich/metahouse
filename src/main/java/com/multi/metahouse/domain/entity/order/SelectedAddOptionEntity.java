package com.multi.metahouse.domain.entity.order;

import javax.persistence.Column;
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
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "selected_add_option")
public class SelectedAddOptionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "selected_add_option_id")
	private Long selectedAddOptionId;
	@Column(name="order_id")
	private Long orderId;
	private Long addOptionId;
//	추가옵션 선택수량
	private String count;
}
