package com.example.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.dto.ImgDTO;
import com.example.dto.ImgResponse;

@Service
public interface ImageService {

	void saveImg(ImgDTO imgDTO, HttpServletRequest httpServletRequest) throws IOException;

	List<ImgResponse> getImgAndName();
	
	void clearImg(Long id);

}
