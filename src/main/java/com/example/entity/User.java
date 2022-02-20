package com.example.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.constant.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Table(name = "tbl_user")
@Entity
@ToString
public class User {

	@Id
	private String username;
	
	@Column(name="hash_password")
	private String hashPassword;

	private String fullName;
	
	private String email;
	
	private String avatar;
	
	private Long phone;
	
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	@Enumerated(EnumType.STRING)
	private UserType type;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	@ToString.Exclude
	private Role role;
	
//	@ManyToMany
//	@JsonIgnore
//	@JoinTable(
//			name = "cart",
//			joinColumns = @JoinColumn(name="username"),
//			inverseJoinColumns = @JoinColumn(name="product_id")
//			)
//	private List<Products> products;
	}
