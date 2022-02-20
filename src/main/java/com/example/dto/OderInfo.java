package com.example.dto;

import java.util.List;

import lombok.Data;

@Data
public class OderInfo {
	
	private String username;

	private String fullname;
	
	private String address;
	
	private Long phone;
	
	private List<ItemOderInfo> item; 
}
