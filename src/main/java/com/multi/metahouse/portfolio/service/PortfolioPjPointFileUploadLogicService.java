package com.multi.metahouse.portfolio.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioContentImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioPointImgDTO;
import com.multi.metahouse.domain.entity.portfolio.PortfolioPointImg;


@Service
public class PortfolioPjPointFileUploadLogicService {
	//파일업로드를 수행하는 메소드 - 업로드된 파일의 정보를 BoardFileDTO로 변환해서 리턴(1개인 경우)
	//여러개인 경우 BoardFileDTO를 List에 담아서 리턴
	public List<PortfolioPointImgDTO> uploadFiles(List<MultipartFile> multipartFiles, String path) throws IllegalStateException, IOException {
		List<PortfolioPointImgDTO> filedtolist = new ArrayList<PortfolioPointImgDTO>();
		//파일 갯수만큼 각 업로드된 파일 마다 번호를 증가시키며 생성 (순서 저장하는거)
		int count = 1;
		for(MultipartFile multipartFile : multipartFiles) {
			//업로드를 하는 경우 원본파일명과 서버에서 식별할 수 있는 실제 서버에 저장되는 파일명 두 개를 관리
			//클라이언트가 업로드한 원본 파일명
			String storeFilename = uploadFile(multipartFile, path);
			System.out.println("optionalFile-upload-method(storeFilename : " + storeFilename);
			filedtolist.add(new PortfolioPointImgDTO(null, null, storeFilename, count+""));
			count++;
		}
		return filedtolist;
	}
	
	public List<PortfolioPointImg> entityUploadFiles(List<MultipartFile> multipartFiles, String path) throws IllegalStateException, IOException {
		List<PortfolioPointImg> filedtolist = new ArrayList<PortfolioPointImg>();
		//파일 갯수만큼 각 업로드된 파일 마다 번호를 증가시키며 생성 (순서 저장하는거)
		int count = 1;
		for(MultipartFile multipartFile : multipartFiles) {
			//업로드를 하는 경우 원본파일명과 서버에서 식별할 수 있는 실제 서버에 저장되는 파일명 두 개를 관리
			//클라이언트가 업로드한 원본 파일명
			String storeFilename = uploadFile(multipartFile, path);
			System.out.println("optionalFile-upload-method(storeFilename : " + storeFilename);
			filedtolist.add(new PortfolioPointImg(null, null, storeFilename, count+""));
			count++;
		}
		return filedtolist;
	}
	
	
	//파일 한 개를 업로드하고 저장된 파일명을 리턴하는 메소드
	public String uploadFile(MultipartFile multipartFile, String path) throws IllegalStateException, IOException {
		String storeFilename = "";
		
		if(!multipartFile.isEmpty()) { //multipartFile 비어있지 않으면 작업
			System.out.println("multifile is empty");
			String originalFilename = multipartFile.getOriginalFilename(); //API 차원에서 지원해줌.
			//서버에서 식별할 수 있도록 파일명을 변경
			storeFilename  =createStoreFilename(originalFilename);
			//파일명과 path를 이용해서 실제 File객체를 만든 후 업로드 하기 //multipartFile에서 지원하는 transferTo로 파일 업로드 
			multipartFile.transferTo(new File(path+File.separator+storeFilename));
			// path는 upload까지 들어가기 때문에 path+ file객체가 가지는 separator라는 상수(구분기호) + storeFilename //XXXX/WEB-INF/upload +/ + 파일명
	
			System.out.println("원본파일명:"+originalFilename);
			System.out.println("저장파일명:"+storeFilename);
		}
		System.out.println("multifile is not empty");
		return storeFilename;
	}
	
	//UUID를 이용해서 파일명을 변경해서 리턴하는 메소드
	private String createStoreFilename(String originalFilename) {
		
		//확장자의 위치 저장
		int pos = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(pos+1); //시작 index만 지정하면 시작 index부터 끝까지 문자열 추출 pos 위치에서 하면 ..dmfh skdha.
		
		//UUID만들기 (API제공)
		String uuid = UUID.randomUUID().toString(); //UUID 문자열 출력
		return uuid+"."+ext; //서버에서 식별할 수 있는 파일명 생서
	}
	//8-4-4-12로 파일 이름 생성됨.
	//원본 파일명 추출, 원본 파일 기준으로 식별될 파일 명 만들고, 저장
}