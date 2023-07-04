package com.multi.metahouse.domain.dto.project;

import com.multi.metahouse.domain.entity.project.AddOptionEntity;
import com.multi.metahouse.domain.entity.project.ProjectPackageSingleEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAddOption {
	private String add_option_id;
	private String add_option_name;
	private String add_option_description;
	private String add_option_price;
	
	public AddOptionEntity toEntity() {
		return AddOptionEntity.builder()
				.addOptionName(add_option_name)
				.addOptionPrice(Integer.parseInt(add_option_price))
				.addOptionDescription(add_option_description)
				.build();
	}
	
	public ProjectAddOption fromEntity(AddOptionEntity entity) {
		return ProjectAddOption.builder()
				.add_option_name(entity.getAddOptionName())
				.add_option_price(entity.getAddOptionPrice()+"")
				.add_option_description(entity.getAddOptionDescription())
				.build();
	}
}
