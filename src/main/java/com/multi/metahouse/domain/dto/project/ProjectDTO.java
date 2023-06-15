package com.multi.metahouse.domain.dto.project;

import java.sql.Timestamp;
import org.apache.ibatis.type.Alias;


@Alias("project")
public class ProjectDTO {
	private int project_id;
	private int creator_id;
	private String tag;
	private String title;
	private String description;
	private Timestamp project_date;
	private int project_hits;
	private String category1;
	private String category2;
	
	public ProjectDTO() {
		super();
	}
	public ProjectDTO(int project_id, int creator_id, String tag, String title, String description,
			Timestamp project_date, int project_hits, String category1, String category2) {
		super();
		this.project_id = project_id;
		this.creator_id = creator_id;
		this.tag = tag;
		this.title = title;
		this.description = description;
		this.project_date = project_date;
		this.project_hits = project_hits;
		this.category1 = category1;
		this.category2 = category2;
	}
	@Override
	public String toString() {
		return "ProjectDTO [project_id=" + project_id + ", creator_id=" + creator_id + ", tag=" + tag + ", title="
				+ title + ", description=" + description + ", project_date=" + project_date + ", project_hits="
				+ project_hits + ", category1=" + category1 + ", category2=" + category2 + "]";
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getProject_date() {
		return project_date;
	}
	public void setProject_date(Timestamp project_date) {
		this.project_date = project_date;
	}
	public int getProject_hits() {
		return project_hits;
	}
	public void setProject_hits(int project_hits) {
		this.project_hits = project_hits;
	}
	public String getCategory1() {
		return category1;
	}
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	public String getCategory2() {
		return category2;
	}
	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	
	
}
