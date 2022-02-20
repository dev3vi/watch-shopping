package com.example.dto;

import java.util.List;


public class ImgResponse {
	
	private Long id;

	private String name;
	
	private List<String> image;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}

	public ImgResponse(Long id, String name, List<String> image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}

}
