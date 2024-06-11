package com.multi.metahouse.review.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.dto.project.ProjectContentsDTO;
import com.multi.metahouse.domain.entity.review.jpadto.ProjectReviewJpaDto;

@Service
public class ProjectReviewFileUploadService {
	public List<ProjectReviewJpaDto.ProjectReviewContents> uploadFiles(List<MultipartFile> multipartFiles, String path)
			throws IllegalStateException, IOException {
//		List<ProjectContentsEntity> contentsList = new ArrayList<ProjectContentsEntity>();
		List<ProjectReviewJpaDto.ProjectReviewContents> contentsList = new ArrayList<ProjectReviewJpaDto.ProjectReviewContents>();
		int count = 1;
		for (MultipartFile multipartFile : multipartFiles) {
			// uploadFile() 호출해서 사용
			String storeFilename = uploadFile(multipartFile,path);
			//ProjectContentsEntity 데이터 옮겨담기
			
			contentsList.add(ProjectReviewJpaDto.ProjectReviewContents
					.builder()
					.reviewStoreFilename(storeFilename)
					.reviewImgNo(Long.valueOf(count)).build());
			count++;
		}
		return contentsList;
	}
	
	// 파일 한 개 업로드하고 저장된 파일명을 리턴하는 메서드
		public String uploadFile(MultipartFile multipartFile, String uploadPath) throws IllegalStateException, IOException {
			String storeFilename = "";
			if (!multipartFile.isEmpty()) {
				// key = "식별자명(고유번호)", value="원본 파일명"
				// 원본 파일명
				String originalFilename = multipartFile.getOriginalFilename();

				// 파일 식별자로 파일명을 변경
				storeFilename = createStoreFileName(originalFilename);

				// 파일명과 path를 이용해서 실제 File객체를 만든 후 업로드 하기
				multipartFile.transferTo(new File(uploadPath+File.separator+storeFilename));

			}
				
			return storeFilename;
		}
		// UUID 를 이용해서 파일명을 변경해서 리턴하는 메서드 (고유파일명 생성)
		private String createStoreFileName(String originalFilename) {
			int pos = originalFilename.lastIndexOf(".");
			String ext = originalFilename.substring(pos + 1); // [pos+1] ~ [마지막 index] 까지, 확장자
			String uuid = UUID.randomUUID().toString();
			return uuid + "." + ext;
		}
}
