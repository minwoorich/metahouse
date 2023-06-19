package com.multi.metahouse.domain.dto.project;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPackageTripleForm {
	private int project_id;
	private int add_option_id;
	//베이직 패키지
	private String basic_pkg_title;
	private String basic_pkg_description;
	private String basic_price;
	private String basic_revision;
	private String basic_workdays;
	//이코노미 패키지
	private String economy_pkg_title;
	private String economy_pkg_description;
	private String economy_price;
	private String economy_revision;
	private String economy_workdays;
	//프리미엄 패키지
	private String premium_pkg_title;
	private String premium_pkg_description;
	private String premium_price;
	private String premium_revision;
	private String premium_workdays;
	//추가옵션영역
	private List<ProjectAddOption> projectAddOptionList;
}
