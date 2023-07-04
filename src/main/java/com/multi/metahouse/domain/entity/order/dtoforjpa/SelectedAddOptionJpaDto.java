package com.multi.metahouse.domain.entity.order.dtoforjpa;

import com.multi.metahouse.domain.entity.order.SelectedAddOptionEntity;
import com.multi.metahouse.domain.entity.project.AddOptionEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectedAddOptionJpaDto {
	@Getter
	public static class Response {
		private Long selected_add_option_id;
		private Long order_id;
		private int count;
		private AddOptionDto add_option_id;
		
		public Response(SelectedAddOptionEntity entity) {
			this.selected_add_option_id = entity.getSelectedAddOptionId();
			this.order_id = entity.getOrderId();
			this.count = Integer.parseInt(entity.getCount());
			this.add_option_id = new AddOptionDto(entity.getAddOptionId());
		}
	}
	@Getter
	public static class AddOptionDto{
		private int addOptionPrice;
		
		public AddOptionDto(AddOptionEntity entity) {
			this.addOptionPrice = entity.getAddOptionPrice();
		}
	}
	
	
}
