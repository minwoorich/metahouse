package com.multi.metahouse.domain.dto.search;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("serviceSearchResult")
public class ServiceSearchResultDTO {
	String title;
	int price;
	String img;
	String writer_id;
	double rating;
	int review_count;
	String regdate;
	String category;
	String flag;
}
