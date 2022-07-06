package com.aditya.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aditya.blog.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
