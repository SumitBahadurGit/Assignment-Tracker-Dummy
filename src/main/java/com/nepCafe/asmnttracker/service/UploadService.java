package com.nepCafe.asmnttracker.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

	@Value("${service.upload.dir}")
	private String uploadDir;
	
	public Set<String> uploadFiles(Long id, MultipartFile[] files) {
		Set<String> fileNames = new HashSet<>();
		Path copyLocation = Paths.get(uploadDir);
		for(MultipartFile file : files) {
			try {
				String fileName =  id + "-" + file.getOriginalFilename();
				Files.copy(file.getInputStream(), Paths.get(copyLocation  + "\\" + fileName), StandardCopyOption.REPLACE_EXISTING);
				fileNames.add(fileName);
			} catch (IOException e) {
				e.printStackTrace();					
			}			
		}
		return fileNames;
	}
}
