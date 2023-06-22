package com.multi.metahouse.domain.entity.project;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.multi.metahouse.domain.dto.project.ProjectFormDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="project")
public class ProjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectId;
	private String creatorId;
	private String tag;
	private String title;
	private String description;
	@CreationTimestamp
	private LocalDateTime projectDate;
	private int projectHits;
	private String category1;
	///////////LCH//////////////
	@Column(name="category2_pj")
	private String category2Pj;
	private String thumbnail; 
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="project_id")
	private List<ProjectContentsEntity> projectContentsEntity = new ArrayList<>();
	
	public ProjectEntity toEntity(ProjectFormDTO dto) {
		return ProjectEntity.builder().
				creatorId(dto.getCreator_id())
				.title(dto.getTitle())
				.description(dto.getDescription())
				.category1(dto.getCategory1())
				.category2Pj(dto.getCategory2_pj())
				.build();
	}
}
//UUID = 