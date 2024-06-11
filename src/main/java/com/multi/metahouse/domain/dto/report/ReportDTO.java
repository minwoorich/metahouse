package com.multi.metahouse.domain.dto.report;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("report")
public class ReportDTO {
	private int report_no;
	private String state;
	private String user_id;
	private String report_location;
	private String report_target_id;
	private String report_reason;
	private String report_datetime;
}
