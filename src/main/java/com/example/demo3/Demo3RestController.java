package com.example.demo3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo3.annotation.TimeLog;

//https://www.baeldung.com/spring-security-acl

@RestController
@RequestMapping("/api")
public class Demo3RestController {
	private static Logger logger = LoggerFactory.getLogger(Demo3RestController.class);
	
//	@Autowired
//	private UserRepositoryJPA userRepo;
	
	@Autowired
	private UserMapper userMapper;
	
	@TimeLog
	@RequestMapping("/ping")
	public String ping(@RequestParam(name="msg", required=false)String msg) {
		return (null == msg || msg.isEmpty()) ? "pong" : msg;
	}
	
	@RequestMapping("/user/{id}")
	@Retryable(value = { Exception.class }, maxAttempts = 2)
	public User getUser(@PathVariable("id")String id) {
		return userMapper.findById(id);
	}
	
	@RequestMapping("/addUser")
	public void addUser(@RequestParam(name="id")String id, @RequestParam(name="pwd")String pwd) {
		User user = new User();
		user.setId(id);
		user.setPwd(pwd);
		userMapper.save(user);
		
	}
	
	
}
