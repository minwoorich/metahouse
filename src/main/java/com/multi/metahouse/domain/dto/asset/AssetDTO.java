package com.multi.metahouse.domain.dto.asset;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("asset")
public class AssetDTO {
	private String asset_id;
	private String seller_id;
	private String title;
	private String category1;
	private String category2_as;
	private String description;
	private int price;
	//첨부파일
	private List<MultipartFile> attach_file;
	private String main_img;
	//대표 이미지
	private MultipartFile thumbnail_img;
	//상세 이미지
	private List<MultipartFile> optional_img;
	private String asset_date;
	private String asset_hits;
}
