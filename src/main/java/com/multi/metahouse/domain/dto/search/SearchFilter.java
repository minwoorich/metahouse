package com.multi.metahouse.domain.dto.search;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("searchfilter")
public class SearchFilter {
	private String category; //로 록스 제페토인 ...
	private String option; // 에셋인지 프로젝트인 
	private String order; // 최신 , 가격높은 낮은 , 인기순인지 
	private String keyword; // 검색키워 
	
}
