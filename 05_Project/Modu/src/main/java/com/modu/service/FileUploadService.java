package com.modu.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	String saveImgFile(MultipartFile file, String path, ArrayList<String> fileInfoList);
}
