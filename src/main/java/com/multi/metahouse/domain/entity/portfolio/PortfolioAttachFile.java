package com.multi.metahouse.domain.entity.portfolio;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "portfolio_attach_file")
public class PortfolioAttachFile{
	@Id
	@NonNull
	private String attachFileId;
	private String portfolioId;
	private String attachFilename;
	private String attachStoreFilename;
	private String attachFileno;
}
