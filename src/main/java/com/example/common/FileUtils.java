package com.example.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class FileUtils {
	
	public static String saveImg(MultipartFile file, String location, HttpServletRequest request) throws IOException{
		String baseUrl =  ServletUriComponentsBuilder.fromRequestUri(request)
				.replacePath(null)
				.build()
				.toUriString();
		String fileName = UUID.randomUUID().toString() +"."+ FilenameUtils.getExtension(file.getOriginalFilename());
		System.out.println(fileName);
		Path path = Paths.get(location);
		Files.createDirectories(path);
		File saveFile = Paths.get(location, fileName).toFile();
		file.transferTo(saveFile);
		return baseUrl+"/product/"+fileName;
		
	}
	
	public static String saveImgDetail(MultipartFile file, String location, HttpServletRequest request) throws IOException{
		String baseUrl =  ServletUriComponentsBuilder.fromRequestUri(request)
				.replacePath(null)
				.build()
				.toUriString();
		String fileName = UUID.randomUUID().toString() +"."+ FilenameUtils.getExtension(file.getOriginalFilename());
		System.out.println(fileName);
		Path path = Paths.get(location);
		Files.createDirectories(path);
		File saveFile = Paths.get(location, fileName).toFile();
		file.transferTo(saveFile);
		return baseUrl+"/detailImg/"+fileName;
		
	}

}
