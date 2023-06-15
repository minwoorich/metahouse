package com.multi.metahouse.domain.entity.project;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="project_content")
public class ProjectContentsEntity {
	private int project_content_id;
	private int project_id;
	private String project_store_filename;
	private String project_file_no;
	public ProjectContentsEntity() {
		super();
	}
	public ProjectContentsEntity(int project_content_id, int project_id, String project_store_filename,
			String project_file_no) {
		super();
		this.project_content_id = project_content_id;
		this.project_id = project_id;
		this.project_store_filename = project_store_filename;
		this.project_file_no = project_file_no;
	}
	public int getProject_content_id() {
		return project_content_id;
	}
	public void setProject_content_id(int project_content_id) {
		this.project_content_id = project_content_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_store_filename() {
		return project_store_filename;
	}
	public void setProject_store_filename(String project_store_filename) {
		this.project_store_filename = project_store_filename;
	}
	public String getProject_file_no() {
		return project_file_no;
	}
	public void setProject_file_no(String project_file_no) {
		this.project_file_no = project_file_no;
	}
	@Override
	public String toString() {
		return "ProjectContentsEntity [project_content_id=" + project_content_id + ", project_id=" + project_id
				+ ", project_store_filename=" + project_store_filename + ", project_file_no=" + project_file_no + "]";
	}
	
	
}
