package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.constant.RoleCode;
import com.example.entity.Role;

//không cần đánh dẫu cũng đc, SpringData có thế tự hiểu

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findRoleByRoleCode(RoleCode role);
}
