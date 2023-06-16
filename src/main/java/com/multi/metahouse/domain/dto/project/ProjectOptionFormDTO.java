package com.multi.metahouse.domain.dto.project;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectOptionFormDTO {
	private int project_option_id;
	private int project_id;
	private int add_option_id;
	private int price;
	private String option_description;
	private String pacakge_rank;
	private int workdays;
	private String add_option_name;
	private String add_option_description;
	private int add_option_price;
}
