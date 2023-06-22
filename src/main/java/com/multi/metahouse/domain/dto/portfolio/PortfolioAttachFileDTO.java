package com.multi.metahouse.domain.dto.portfolio;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("portfolio_attach_file")
public class PortfolioAttachFileDTO{
	private String attach_file_id;
	private String portfolio_id;
	private String attach_filename;
	private String attach_store_filename;
	private String attach_fileno;
}
