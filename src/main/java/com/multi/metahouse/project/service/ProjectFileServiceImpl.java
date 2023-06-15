package com.multi.metahouse.project.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.dto.project.ProjectDTO;

public class ProjectFileServiceImpl implements ProjectFileService {
	@Value("${file.dir}")
	private String uploadpath;
	
	@Override
	//파일을 업로드 할 경로 가져오는 메서드
	public String getUploadpath(String filename) {
		
		return uploadpath + filename;
	}

	@Override
	//여러개 파일 업로드 로직
	public List<ProjectDTO> uploadFiles(List<MultipartFile> multipartFiles) {
		return null;
	}

	@Override
	// 파일 한개 업로드 로직
	public String uploadFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
		
		String storeFilename = "";
		if (!multipartFile.isEmpty()) {

			// key = "식별자명(고유번호)", value="원본 파일명"
			// 원본 파일명
			String originalFilename = multipartFile.getOriginalFilename();

			// 파일 식별자로 파일명을 변경
			storeFilename = createStoreFileName(originalFilename);

			// 파일명과 path를 이용해서 실제 File객체를 만든 후 업로드 하기
			multipartFile.transferTo(new File(getUploadpath(storeFilename)));

			System.out.println("원본 파일명 : " + originalFilename);
			System.out.println("저장 파일명 : " + storeFilename);

		}
		return storeFilename;
	}

	@Override
	// 저장용이름 생성기(UUID)
	public String createStoreFileName(String originalFilename) {
		// TODO Auto-generated method stub
		return null;
	}

}
