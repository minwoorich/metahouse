package com.multi.metahouse.domain.dto.asset;

public class AssetContentDTO {
	private String asset_content_id;
	private String asset_id;
	private String asset_store_filename;
	private String asset_fileno;
	
	
	//toString
	@Override
	public String toString() {
		return "AssetContentDTO [asset_content_id=" + asset_content_id + ", asset_id=" + asset_id
				+ ", asset_store_filename=" + asset_store_filename + ", asset_fileno=" + asset_fileno + "]";
	}
	
	//Constructor using field
	public AssetContentDTO(String asset_content_id, String asset_id, String asset_store_filename, String asset_fileno) {
		super();
		this.asset_content_id = asset_content_id;
		this.asset_id = asset_id;
		this.asset_store_filename = asset_store_filename;
		this.asset_fileno = asset_fileno;
	}

	//getter, setter 메소드
	public String getAsset_content_id() {
		return asset_content_id;
	}
	public void setAsset_content_id(String asset_content_id) {
		this.asset_content_id = asset_content_id;
	}
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
	public String getAsset_store_filename() {
		return asset_store_filename;
	}
	public void setAsset_store_filename(String asset_store_filename) {
		this.asset_store_filename = asset_store_filename;
	}
	public String getAsset_fileno() {
		return asset_fileno;
	}
	public void setAsset_fileno(String asset_fileno) {
		this.asset_fileno = asset_fileno;
	}
	
	
}
