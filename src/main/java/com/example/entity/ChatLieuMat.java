package com.example.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity 
@Data
@Table(name="chat_lieu_mat")
public class ChatLieuMat {

	@Id
	private Long id;

	private String loaiMatKinh;
	
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	@OneToMany(mappedBy = "cLMat", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Products> products;
}
