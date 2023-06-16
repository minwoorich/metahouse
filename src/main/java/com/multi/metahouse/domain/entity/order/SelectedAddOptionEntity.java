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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "selected_add_option")
public class SelectedAddOptionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String selectedAddOptionId;
	private String orderId;
	private String addOptionId;
//	추가옵션 선택수량
	private String count;
}
