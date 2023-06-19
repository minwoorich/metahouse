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
	private String add_option_id;
	private String project_id;
	private String add_option_name;
	private String add_option_price;
	private String add_option_description;

}
