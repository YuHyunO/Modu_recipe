package com.modu.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	String saveStore(MultipartFile file);
}
