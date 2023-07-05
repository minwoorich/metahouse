package com.multi.metahouse.domain.entity.project.jpadto;

import java.util.List;

import com.multi.metahouse.domain.dto.project.ProjectAddOption;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.project.ProjectPackageTripleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPackageTripleForm implements ProjectPackageForm{
	
	//베이직 패키지
	private String basic_pkg_title;
	private String basic_pkg_description;
	private int basic_price;
	private String basic_revision;
	private String basic_workdays;
	//이코노미 패키지
	private String economy_pkg_title;
	private String economy_pkg_description;
	private int economy_price;
	private String economy_revision;
	private String economy_workdays;
	//프리미엄 패키지
	private String premium_pkg_title;
	private String premium_pkg_description;
	private int premium_price;
	private String premium_revision;
	private String premium_workdays;
	//추가옵션영역
	private List<ProjectAddOption> projectAddOptionList;
	
	public ProjectPackageTripleEntity toEntity() {
		return ProjectPackageTripleEntity.builder()
				.basicPkgTitle(basic_pkg_title)
				.basicPkgDescription(basic_pkg_description)
				.basicPrice(basic_price)
				.basicRevision(Integer.parseInt(basic_revision))
				.basicWorkdays(Integer.parseInt(basic_workdays))
				.economyPkgTitle(economy_pkg_title)
				.economyPkgDescription(economy_pkg_description)
				.economyPrice(economy_price)
				.economyRevision(Integer.parseInt(economy_revision))
				.economyWorkdays(Integer.parseInt(economy_workdays))
				.premiumPkgTitle(premium_pkg_title)
				.premiumPkgDescription(premium_pkg_description)
				.premiumPrice(premium_price)
				.premiumRevision(Integer.parseInt(premium_revision))
				.premiumWorkdays(Integer.parseInt(premium_workdays))
				.build();
	}
	
	public ProjectPackageTripleForm fromEntity(ProjectPackageTripleEntity entity) {
		return ProjectPackageTripleForm.builder()
				.basic_pkg_title(entity.getBasicPkgTitle())
				.basic_pkg_description(entity.getBasicPkgDescription())
				.basic_price(entity.getBasicPrice())
				.basic_revision(entity.getBasicRevision()+"")
				.basic_workdays(entity.getBasicWorkdays()+"")
				.economy_pkg_title(entity.getEconomyPkgTitle())
				.economy_pkg_description(entity.getEconomyPkgDescription())
				.economy_price(entity.getEconomyPrice())
				.economy_revision(entity.getEconomyRevision()+"")
				.economy_workdays(entity.getEconomyWorkdays()+"")
				.premium_pkg_title(entity.getPremiumPkgTitle())
				.premium_pkg_description(entity.getPremiumPkgDescription())
				.premium_price(entity.getPremiumPrice())
				.premium_revision(entity.getPremiumRevision()+"")
				.premium_workdays(entity.getPremiumWorkdays()+"")
				.build();
	}
}
