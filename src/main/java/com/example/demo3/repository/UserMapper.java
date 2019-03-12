package com.example.demo3.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo3.entity.User;

@Mapper
public interface UserMapper {
	public User findById(String id);
	public void save(User user);
}
