package com.multi.metahouse.domain.entity.order.dtoforjpa;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class ProjectOrdersConfirmUpdateDTO {
//	private LocalDateTime completion_date;
	private Long orderId;
	private String orderStatus;
	private String acceptanceValue;
	private String orderDate;
}
