package com.multi.metahouse.domain.dto.project;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPackageSingleForm {
	private int project_id;
	private int add_option_id;
	private String pkg_title;
	private String pkg_description;
	private String price;
	private String revision;
	private String workdays;
	private List<ProjectAddOption> projectAddOptionList;
}
