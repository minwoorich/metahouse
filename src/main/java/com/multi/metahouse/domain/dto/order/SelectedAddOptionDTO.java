package com.multi.metahouse.domain.dto.order;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("selectedAddOption")
public class SelectedAddOptionDTO {
	private String selected_add_option_id;
	private String order_id;
	private String add_option_id;
	private String count;
}
