package com.multi.metahouse.domain.entity.order.dtoforjpa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.multi.metahouse.domain.entity.order.ProjectOrdersEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
public class ProjectOrdersResponse {
	static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy.MM.dd hh:mm");
	@Getter
	@ToString
	@Setter
	public static class Response{
		private Long orderId;
		private String buyerId;
		private String sellerId;
		private String orderStatus;
		private String orderCommitDate;
		private String orderDate;
		private String completionDate;
		private String request;
		private String totalPrice;
		private String packageType;
		private Long reviewCheck;
		private OrderedProjectDto project;
		
		
		public Response(ProjectOrdersEntity order, String totalPrice, String packageType, LocalDateTime orderDate, LocalDateTime completionDate) {
			this.orderId = order.getOrderId();
			this.buyerId = order.getBuyerId();
			this.sellerId = new OrderedProjectDto(order.getProjectId()).getCreatorId();
			this.orderStatus = order.getOrderStatus();
			this.orderCommitDate = order.getOrderCommitDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			this.request = order.getRequest();
			this.project = new OrderedProjectDto(order.getProjectId());
			this.totalPrice = totalPrice;
			this.packageType = packageType;
			this.orderDate = (orderDate!=null)? orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")):LocalDateTime.of(9999,12,31,23,59,59,0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			this.completionDate = (completionDate!=null)? completionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")):LocalDateTime.of(9999,12,31,23,59,59,0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		}
	}
	
//	@Getter
//	@ToString
//	public static class SellerResponse{
//		private Long orderId;
//		private String buyerId;
//		private String sellerId;
//		private String orderStatus;
//		private LocalDateTime orderCommitDate;
//		private String request;
//		private String totalPrice;
//		private String packageType;
//		private OrderedProjectDto project;
//		
//		
//		public SellerResponse(ProjectOrdersEntity order) {
//			this.orderId = order.getOrderId();
//			this.buyerId = order.getBuyerId();
//			this.sellerId = new OrderedProjectDto(order.getProjectId()).getCreatorId();
//			this.orderStatus = order.getOrderStatus();
//			this.orderCommitDate = order.getOrderCommitDate();
//			this.request = order.getRequest();
//			this.project = new OrderedProjectDto(order.getProjectId());
//		}
//	}
	
	@Getter
	@ToString
	public static class OrderedProjectDto{
		private Long projectId;
		private String title;
		private String creatorId;
		private String category1;
		private String category2Pj;
		private String thumbnail;
		
		public OrderedProjectDto(ProjectEntity entity) {
			this.projectId = entity.getProjectId();
			this.title = entity.getTitle();
			this.creatorId = entity.getCreatorId();
			this.category1 = entity.getCategory1();
			this.category2Pj = entity.getCategory2Pj();
			this.thumbnail = entity.getThumbnail();
		}
	}
	
}
