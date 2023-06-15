package com.multi.metahouse.project.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.dto.project.ProjectDTO;

@Service
public interface ProjectFileService {
	//파일을 업로드 할 경로 가져오는 메서드
	String getUploadpath(String filename);
	//여러개 파일 업로드 로직
	List<ProjectDTO> uploadFiles(List<MultipartFile> multipartFiles);
	// 파일 한개 업로드 로직
	String uploadFile(MultipartFile multipartFile) throws IllegalStateException, IOException;
	// 저장용이름 생성기(UUID)
	String createStoreFileName(String originalFilename);
}

/*
//서버 내부에 파일을 저장하지 않고 외부 디렉토리에 업로드되는 파일을 저장 할 것
	//따라서 디렉토리는 application.properties파일에 정의한 속성에 대한 값을 가져와야한다.
	//설정파일에 정의된 property (name과 value를 가지고 있는 것)를 쉽게 가져 올 수 있도록 스프링에서 지원된다.
	//uploadpath에 C:/Users/minwo/Desktop/IT STUDY/javaweb/upload가 셋팅됨
	@Value("${file.dir}")
	private String uploadpath;
	public String getUploadpath(String filename) {
		return uploadpath + filename;
	}

	public List<BoardFileDTO> uploadFiles(List<MultipartFile> multipartFiles)
			throws IllegalStateException, IOException {
		List<BoardFileDTO> fileDtoList = new ArrayList<BoardFileDTO>();
		int count = 1;

		for (MultipartFile multipartFile : multipartFiles) {
			// uploadFile() 호출해서 사용
			String storeFilename = uploadFile(multipartFile);
			fileDtoList.add(new BoardFileDTO(null, multipartFile.getOriginalFilename(), storeFilename,
					Integer.toString(count)));

			count++;
		}
		return fileDtoList;
	}

	// 파일 한 개 업로드하고 저장된 파일명을 리턴하는 메서드
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

	// UUID 를 이용해서 파일명을 변경해서 리턴하는 메서드 (고유파일명 생성)
	private String createStoreFileName(String originalFilename) {
		int pos = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(pos + 1); // [pos+1] ~ [마지막 index] 까지, 확장자
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}
*/
