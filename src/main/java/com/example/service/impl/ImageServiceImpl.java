package com.example.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.common.FileUtils;
import com.example.dto.ImgDTO;
import com.example.dto.ImgResponse;
import com.example.entity.Image;
import com.example.entity.Products;
import com.example.repository.ImageRepository;
import com.example.repository.ProductsRepository;
import com.example.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	@Value("D://watch/detailImg")
	private String location;
	
	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private ImageRepository imageServiceImpl;
	
	@Override
	public void saveImg(ImgDTO imgDTO, HttpServletRequest httpServletRequest) throws IOException {
		Products products = productsRepository.findByName(imgDTO.getNames());
		Image image = new Image();
		image.setProductId(products);
		image.setDataImg(FileUtils.saveImgDetail(imgDTO.getImage(), this.location, httpServletRequest));
		imageServiceImpl.save(image);
	}

	@Override
	public List<ImgResponse> getImgAndName() {
		List<Products> product = productsRepository.findAll();
		List<ImgResponse> result = new ArrayList<ImgResponse>();
		product.forEach(p->{
			List<String> img = new ArrayList<String>();
			p.getProductImg().forEach(p2->{
				img.add(p2.getDataImg());
			});
			ImgResponse imgResponse = new ImgResponse(p.getId(), p.getName(), img);
			result.add(imgResponse);
		}
		);
		return	result;
	}

	@Override
	public void clearImg(Long id) {
//		Products products = productsRepository.getById(id);
		List<Image> img =  imageServiceImpl.findByProductIdId(id);
		img.forEach(e->{
			System.out.println(e.getId());
			imageServiceImpl.deleteById(e.getId());
		});
		

	}	

}