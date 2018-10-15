package com.example.demo3;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserRepository {
	private Map<String, User> users = new HashMap<>();
	
	public void add(User user) {
		users.put(user.getId(), user);
	}
	
	public User get(String id) {
		return users.get(id);
	}
}
