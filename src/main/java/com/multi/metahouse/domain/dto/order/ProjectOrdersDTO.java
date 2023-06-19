package com.multi.metahouse.domain.dto.order;

import org.apache.ibatis.type.Alias;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("projectOrder")
public class ProjectOrdersDTO {
	private String order_id;
	private String project_id;
	private String buyer_id;
	private String project_option_id;
	private String pre_order_status;
	private String order_commit_date; 
	private String request;

}
