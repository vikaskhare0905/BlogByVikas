package com.blogAppApi.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogAppApi.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileService{

	@Override
	public String uploadingImage(String path, MultipartFile file) throws IOException {
		log.info(" Starting Request  for uploading Image ");
		//File name
		String name = file.getOriginalFilename();
		//abc.png
		
		//random name generate file
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
		
		//Fullpath
		String filePath = path + File.separator + fileName1;
		
		//create folder if not created
		File f =  new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		//file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		log.info(" Ending Request  for uploading Image  ");
		
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		log.info(" Starting Request  for accessing Image ");
		String fullPath = path + File.separator + fileName;
		InputStream is =  new FileInputStream(fullPath);
		//db logic to return input stream
		log.info(" Ending Request  for accessing Image ");
		
		return is;
	}

}
