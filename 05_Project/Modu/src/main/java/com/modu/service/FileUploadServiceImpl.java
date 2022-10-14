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
		String ofheader = ofname.substring(0, idx); //a(�����̸� �κ�)�� ��µ�(�ε��� 0���� idx �̸�����)
		String ext = ofname.substring(idx); //Ȯ���� ���(�ε��� idx���� ������ ���)
		long ms = System.currentTimeMillis(); //���� �ð��� �и������带 �̾Ƴ�
		
		StringBuilder sb = new StringBuilder();
		log.info("#��������: " + fileInfoList);
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
		String saveFileName = sb.toString(); //���� ���������� ����Ǵ� ���� �̸��� ��
		
		long fsize = file.getSize(); //���� ������ ���ϱ�
		log.info("#ofname: " + ofname + ", saveFileName: " + saveFileName + ", fsize: " + fsize); 
		//���� �����̸�, ������ ���� �̸�, ���ϻ����� 	
		
		boolean flag = writeFile(file, saveFileName, path);
		if(flag) {
			log.info("#���ε� ����");
		}else {
			log.info("#���ε� ����");
		}
		
		//IO�� ������ ���ָ� ��
		return path + saveFileName;		
	}

	//�� ���������� ���������� ���ϻ����ϴ� �޼ҵ� 
	private boolean writeFile(MultipartFile file, String saveFileName, String path) {
		
		File dir = new File(path);
		if(!dir.exists()) dir.mkdirs(); //�ش� ��ΰ� ���ٸ� ���丮�� ������
		
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
			fos = new FileOutputStream(path + saveFileName);
			fos.write(data);
			fos.flush();
			
			return true;
		}catch(IOException ie) {
			log.info("#���Ͼ��ε弭��impl boolean writeFile�޼ҵ忡�� ���� ie �߻�: "+ ie);
			return false;
		}finally {
			try {
				if(fos != null) fos.close();
			}catch(IOException is) {
				log.info("#���Ͼ��ε弭��impl boolean writeFile�޼ҵ忡�� ���� is �߻�: "+ is);
			}
		}
	}
}
	