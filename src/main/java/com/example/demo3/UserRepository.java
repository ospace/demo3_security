package com.example.demo3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

//http://asfirstalways.tistory.com/299
@Component
public class UserRepository {
	private Map<String, User> users = new HashMap<>();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void add(User user) {
		users.put(user.getId(), user);
	}
	
	public User get(String id) {
		return users.get(id);
	}
	
	public User get2(String id) {
		return (User) jdbcTemplate.query("SELECT * FROM user WEHRE id=?", new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
		
//		return (User) jdbcTemplate.query("SELECT * FROM user WEHRE id=:id", new MapSqlParameterSource().addValue("id", id));
	}
}
