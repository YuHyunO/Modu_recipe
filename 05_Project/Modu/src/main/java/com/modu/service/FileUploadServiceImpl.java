package com.modu.service;

import java.io.*;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Override
	public String saveImgFile(MultipartFile file, String path, ArrayList<String> fileInfoList) {	
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");
		String ofheader = ofname.substring(0, idx); //a(파일이름 부분)가 출력됨(인덱스 0부터 idx 미만까지)
		String ext = ofname.substring(idx); //확장자 출력(인덱스 idx부터 끝까지 출력)
		long ms = System.currentTimeMillis(); //현재 시간의 밀리세컨드를 뽑아냄
		
		StringBuilder sb = new StringBuilder();
		log.info("#파일정보: " + fileInfoList);
		if (fileInfoList == null) {
			sb.append(ofheader);
			sb.append("_");
			sb.append(ms);
			sb.append(ext);
		} else {
			sb.append(fileInfoList.get(0));
			sb.append("-");
			sb.append(ofheader);
			sb.append("_");
			sb.append(ms);
			sb.append(ext);
		}
		String saveFileName = sb.toString(); //실제 물리적으로 저장되는 파일 이름이 됨
		
		long fsize = file.getSize(); //파일 사이즈 구하기
		log.info("#ofname: " + ofname + ", saveFileName: " + saveFileName + ", fsize: " + fsize); 
		//원래 파일이름, 저장한 파일 이름, 파일사이즈 	
		
		boolean flag = writeFile(file, saveFileName, path);
		if(flag) {
			log.info("#업로드 성공");
		}else {
			log.info("#업로드 실패");
		}
		
		//IO로 파일을 써주면 됨
		return path + saveFileName;		
	}

	//내 로컬폴더에 물리적으로 파일생성하는 메소드 
	private boolean writeFile(MultipartFile file, String saveFileName, String path) {
		
		File dir = new File(path);
		if(!dir.exists()) dir.mkdirs(); //해당 경로가 없다면 디렉토리를 생성함
		
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
			fos = new FileOutputStream(path + saveFileName);
			fos.write(data);
			fos.flush();
			
			return true;
		}catch(IOException ie) {
			log.info("#파일업로드서비스impl boolean writeFile메소드에서 예외 ie 발생: "+ ie);
			return false;
		}finally {
			try {
				if(fos != null) fos.close();
			}catch(IOException is) {
				log.info("#파일업로드서비스impl boolean writeFile메소드에서 예외 is 발생: "+ is);
			}
		}
	}
}
	