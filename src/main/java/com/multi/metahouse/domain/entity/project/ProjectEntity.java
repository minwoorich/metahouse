package com.multi.metahouse.domain.entity.project;

import java.sql.Timestamp;

import javax.persistence.Column;
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
@Table(name="project")
public class ProjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectId;
	private String creatorId;
	private String tag;
	private String title;
	private String description;
	private Timestamp projectDate;
	private int projectHits;
	private String category1;
	///////////LCH//////////////
	@Column(name="category2_pj")
	private String category2Pj;
}
//UUID = 