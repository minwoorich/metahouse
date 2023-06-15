package com.multi.metahouse.domain.dto.asset;

public class AssetDetailImgDTO {
	private String asset_detail_img_id;
	private String asset_id;
	private String asset_detail_img_store_filename;
	private String asset_fileno;
	
	
	//toString
	@Override
	public String toString() {
		return "AssetDetailImgDTO [asset_detail_img_id=" + asset_detail_img_id + ", asset_id=" + asset_id
				+ ", asset_detail_img_store_filename=" + asset_detail_img_store_filename + ", asset_fileno="
				+ asset_fileno + "]";
	}
	
	//Constructor using field
	public AssetDetailImgDTO(String asset_detail_img_id, String asset_id, String asset_detail_img_store_filename,
			String asset_fileno) {
		super();
		this.asset_detail_img_id = asset_detail_img_id;
		this.asset_id = asset_id;
		this.asset_detail_img_store_filename = asset_detail_img_store_filename;
		this.asset_fileno = asset_fileno;
	}

	//getter, setter 메소드
	public String getAsset_detail_img_id() {
		return asset_detail_img_id;
	}
	public void setAsset_detail_img_id(String asset_detail_img_id) {
		this.asset_detail_img_id = asset_detail_img_id;
	}
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
	public String getAsset_detail_img_store_filename() {
		return asset_detail_img_store_filename;
	}
	public void setAsset_detail_img_store_filename(String asset_detail_img_store_filename) {
		this.asset_detail_img_store_filename = asset_detail_img_store_filename;
	}
	public String getAsset_fileno() {
		return asset_fileno;
	}
	public void setAsset_fileno(String asset_fileno) {
		this.asset_fileno = asset_fileno;
	}
	
	
	
}
