package com.multi.metahouse.domain.dto.project;

import org.apache.ibatis.type.Alias;

@Alias("projectoption")
public class ProjectOptionsDTO {
	private int project_option_id;
	private int project_id;
	private String subname;
	private int price;
	private String option_description;
	private String package_rank;
	public ProjectOptionsDTO() {
		super();
	}
	public ProjectOptionsDTO(int project_option_id, int project_id, String subname, int price,
			String option_description, String package_rank) {
		super();
		this.project_option_id = project_option_id;
		this.project_id = project_id;
		this.subname = subname;
		this.price = price;
		this.option_description = option_description;
		this.package_rank = package_rank;
	}
	public int getProject_option_id() {
		return project_option_id;
	}
	public void setProject_option_id(int project_option_id) {
		this.project_option_id = project_option_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getOption_description() {
		return option_description;
	}
	public void setOption_description(String option_description) {
		this.option_description = option_description;
	}
	public String getPackage_rank() {
		return package_rank;
	}
	public void setPackage_rank(String package_rank) {
		this.package_rank = package_rank;
	}
	@Override
	public String toString() {
		return "ProjectOptionsDTO [project_option_id=" + project_option_id + ", project_id=" + project_id + ", subname="
				+ subname + ", price=" + price + ", option_description=" + option_description + ", package_rank="
				+ package_rank + "]";
	}
	
	
}
