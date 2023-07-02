package com.multi.metahouse.domain.dto.asset;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.dto.user.UserDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	private LocalDateTime asset_date;
	
	//판매자 정보를 저장하기위한 변수
	private UserDTO userName;
	
	//리뷰 평균값
	private double average_reviews;
	//리 갯
	private int review_count;

	public AssetDTO(String asset_id, String seller_id, String title, String category1, String category2_as,
			String description, int price, String main_img, LocalDateTime asset_date, double average_reviews , int review_count) {
		this.asset_id = asset_id;
		this.seller_id = seller_id;
		this.title = title;
		this.category1 = category1;
		this.category2_as = category2_as;
		this.description = description;
		this.price = price;
		this.main_img = main_img;
		this.asset_date = asset_date;
		this.average_reviews = average_reviews;
		this.review_count = review_count;
	}
	 public static AssetDTO toDTO(AssetEntity entity) {
	        return AssetDTO.builder()
	                .asset_id(entity.getAssetId())
	                .seller_id(entity.getSellerId())
	                .title(entity.getTitle())
	                .category1(entity.getCategory1())
	                .category2_as(entity.getCategory2())
	                .description(entity.getDescription())
	                .price(entity.getPrice())
	                .main_img(entity.getMainImg())
	                .asset_date(entity.getAssetDate())
	                .build();
	    }
	
}
