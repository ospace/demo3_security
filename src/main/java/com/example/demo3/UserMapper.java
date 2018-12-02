package com.example.demo3;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	public User findById(String id);
	public void save(User user);
}
