package com.multi.metahouse.domain.dto.asset;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetPageDTO {
	// 전체글의 갯수
	private int total;
	// 현재 페이지 번호
	private int currentPage;
	// 전체 페이지 수
	private int totalPages;
	// 시작 페이지 번호
	private int startPage;
	// 종료 페이지 번호
	private int endPage;
	// 페이지 수
	private int pagingCount;

	public AssetPageDTO(int total, int currentPage, int size, int pagingCount) {
		super();
		this.total = total;
		this.currentPage = currentPage;
		this.pagingCount = pagingCount;

		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			
			startPage = currentPage/pagingCount*pagingCount+1;
			if (currentPage%pagingCount==0) {
				startPage -= pagingCount;
			}
			
			endPage = startPage + pagingCount-1;
			if(endPage>totalPages) {
				endPage = totalPages;
			}
		}
	}

}
