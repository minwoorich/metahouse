package com.multi.metahouse.domain.dto.asset;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetFormDTO {
	private String user_id;
	private String title;
	private String category1;
	private String category2_as;
	private String description;
	private String price;
	private MultipartFile thumbnail;
	private List<MultipartFile> detailImages;
	private MultipartFile attach_file;
	
}
