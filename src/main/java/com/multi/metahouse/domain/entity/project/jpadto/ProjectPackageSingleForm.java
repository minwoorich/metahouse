package com.multi.metahouse.domain.entity.project.jpadto;

import java.util.List;
import java.util.Map;

import com.multi.metahouse.domain.dto.project.ProjectAddOption;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.project.ProjectPackageSingleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPackageSingleForm implements ProjectPackageForm{
	
	private int add_option_id;
	private String pkg_title;
	private String pkg_description;
	private String price;
	private String revision;
	private String workdays;
	private List<ProjectAddOption> projectAddOptionList;
	
	public ProjectPackageSingleEntity toEntity() {
		return ProjectPackageSingleEntity.builder()
				.pkgTitle(pkg_title)
				.price(Integer.parseInt(price))
				.pkgDescription(pkg_description)
				.revision(Integer.parseInt(revision))
				.workdays(Integer.parseInt(workdays))
				.build();
	}
}
