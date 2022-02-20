package com.example.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.constant.RoleCode;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbl_role")
@Getter
@Setter
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private RoleCode roleCode;
	
	@OneToMany(mappedBy = "role")
	private Set<User> users;
	
}
