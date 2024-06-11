package com.multi.metahouse.domain.entity.order.dtoforjpa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.domain.entity.order.AssetOrdersEntity;
import com.multi.metahouse.domain.entity.order.dtoforjpa.ProjectOrdersResponse.OrderedProjectDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AssetOrdersResponse {
	@Getter
	@ToString
	@Setter
	@AllArgsConstructor
	@Builder
	public static class Response{
		private Long orderId;
		private String buyerId2;
		private String assetOrderDate;
		private OrderedAssetDto asset;
	    private Long reviewCheck;
		
		public Response(AssetOrdersEntity entity) {
			this.orderId = entity.getOrderId();
			this.buyerId2 = entity.getBuyerId2();
			this.assetOrderDate = entity.getAssetOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			this.asset = new OrderedAssetDto(entity.getAssetId());
		}
	}
	@Getter
	@ToString
	@Builder
	@AllArgsConstructor
	public static class OrderedAssetDto{
		private String assetId;
	    private String sellerId;
	    private String title;
	    private String category1;
	    private String category2;
	    private String price;
	    private String mainImg;
	    private String assetContent; //세터주입
	    
	    public OrderedAssetDto(AssetEntity entity) {
	    	this.assetId = entity.getAssetId();
	    	this.sellerId = entity.getSellerId();
	    	this.title = entity.getTitle();
	    	this.category1 = entity.getCategory1();
	    	this.category2 = entity.getCategory2();
	    	this.price = String.format("%,d",entity.getPrice());
	    	this.mainImg = entity.getMainImg();
	    }
	}
}
