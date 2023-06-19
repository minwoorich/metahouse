package com.multi.metahouse.domain.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAddOption {
	private String add_option_name;
	private String add_option_description;
	private int add_option_price;
}
