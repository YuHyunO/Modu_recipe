package com.modu.service;

import java.io.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import com.modu.fileset.RecipePath;

@Log4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Override
	public String saveStore(MultipartFile file) {
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");
		String ofheader = ofname.substring(0, idx);
		String ofext = ofname.substring(idx);
		long ms = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder();
		sb.append(ofheader);
		sb.append("_");
		sb.append(ms);
		sb.append(ofext);
		String saveFileName = sb.toString();
		long fsize = file.getSize();
		
		log.info("ofname: " + ofname + ", ofheader: " + ofheader + ", ofext: " + ofext);
		log.info("saveFileName: " + saveFileName + ", fsize: " + fsize);
		
		//IO로 파일 업로드
		boolean flag = writeFile(file, saveFileName);
		if (flag) {
			log.info(saveFileName + " 업로드 성공");
		} else {
			log.info(saveFileName + " 업로드 실패");
		}
		return RecipePath.FILE_STORE + saveFileName;
	}
	
	private boolean writeFile(MultipartFile file, String saveFileName) {
		File dir = new File(RecipePath.FILE_STORE );
		if(!dir.exists()) dir.mkdirs();  // 해당 경로가 없을 경우 폴더 생성
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
			fos = new FileOutputStream(RecipePath.FILE_STORE + saveFileName);
			fos.write(data);
			fos.flush();
			return true;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if(fos!=null) fos.close();
			}catch(IOException ie) {}
		}
	}
}
