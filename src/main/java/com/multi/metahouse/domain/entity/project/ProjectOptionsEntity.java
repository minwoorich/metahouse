package com.multi.metahouse.domain.entity.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="project_options")
public class ProjectOptionsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectOptionId;
	private int projectId;
	private int price;
	private String optionDescription;
	private String packageRank;
	private int workdays;
}
