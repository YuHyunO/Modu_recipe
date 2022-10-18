package com.modu.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	String[] saveImgFile(MultipartFile file, String path, ArrayList<String> fileInfoList);
}
