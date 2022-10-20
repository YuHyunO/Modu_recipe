package com.modu.service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.modu.fileset.Path;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private String[] result;

    @Override
	public String[] saveImgFile(MultipartFile file, String path, ArrayList<String> fileInfoList) {	
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");
		String ofheader = ofname.substring(0, idx); //a(�����̸� �κ�)�� ��µ�(�ε��� 0���� idx �̸�����)
		String ext = ofname.substring(idx); //Ȯ���� ���(�ε��� idx���� ������ ���)
		long ms = System.currentTimeMillis(); //���� �ð��� �и������带 �̾Ƴ�
		
		StringBuilder sb = new StringBuilder();
		log.info("#��������: " + fileInfoList);
		if (fileInfoList.size() == 0) {
			sb.append(ofheader);
			sb.append("_");
			sb.append(ms);
			sb.append(ext);
		} else {
		    if (fileInfoList.get(0).equals("STEP-0")) {
		    } else {
		        sb.append(fileInfoList.get(0));
		        sb.append("-");
		    }
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
		
		result = new String[2];

		result[0] = path + saveFileName;
		result[1] = saveFileName;
		return result;		
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
	@Override
    public String saveStore(MultipartFile file) {
        String ofname = file.getOriginalFilename();
        int idx = ofname.lastIndexOf(".");
        String ofheader = ofname.substring(0, idx); //a(�����̸� �κ�)�� ��µ�(�ε��� 0���� idx �̸�����)
        String ext = ofname.substring(idx); //Ȯ���� ���(�ε��� idx���� ������ ���)
        long ms = System.currentTimeMillis(); //���� �ð��� �и������带 �̾Ƴ�
        
        StringBuilder sb = new StringBuilder();
        sb.append(ofheader);
        sb.append("_");
        sb.append(ms);
        sb.append(ext);
        String saveFileName = sb.toString(); //���� ���������� ����Ǵ� ���� �̸��� ��
        
        long fsize = file.getSize(); //���� ������ ���ϱ�
        //log.info("#ofname: " + ofname + ", saveFileName: " + saveFileName + ", fsize: " + fsize); 
        //���� �����̸�, ������ ���� �̸�, ���ϻ�����     
        
        boolean flag = writeFile(file, saveFileName);
        if(flag) {
            log.info("#���ε� ����");
        }else {
            log.info("#���ε� ����");
        }
        //IO�� ������ ���ָ� ��
        return Path.FILE_STORE + saveFileName;      
    }
	private boolean writeFile(MultipartFile file, String saveFileName) {
        File dir = new File(Path.FILE_STORE);
        if(!dir.exists()) dir.mkdirs();
        
        FileOutputStream fos = null;
        
        try {
            byte data[] = file.getBytes();
            fos = new FileOutputStream(Path.FILE_STORE + saveFileName);
            fos.write(data);
            fos.flush();
            
            return true;
        }catch(IOException ie) {
            return false;
        }finally {
            try {
                if(fos != null) fos.close();
            }catch(IOException is) {}
        }
    }
}
	