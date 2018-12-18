package com.example.demo3;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo3.annotation.TimeLog;

//https://www.baeldung.com/spring-security-acl

@RestController
@RequestMapping("/api")
public class Demo3RestController {
	private static Logger logger = LoggerFactory.getLogger(Demo3RestController.class);
	
	@Autowired
	private UserRepositoryJPA userRepo;
	
//	@Autowired
//	private UserMapper userMapper;
	
	private BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder(); 
	
	@RequestMapping("/init") 
	public void init() {
//		userRepo.save(User.of("z", pwdEncoder.encode("z"), Arrays.asList(UserRole.of("BASIC"))));
		userRepo.save(User.of("z", pwdEncoder.encode("z"), null));
		userRepo.save(User.of("y", pwdEncoder.encode("y"), Arrays.asList(UserRole.of("ADMIN"))));
		
		logger.info("inited");
	}
	
	@TimeLog
	@RequestMapping("/ping")
	public String ping(@RequestParam(name="msg", required=false)String msg) {
		return (null == msg || msg.isEmpty()) ? "pong" : msg;
	}
	
	@RequestMapping("/user/{id}")
	@Retryable(value = { Exception.class }, maxAttempts = 2)
	public User getUser(@PathVariable("id")String id) {
		return userRepo.findById(id).get();
	}
	
	@RequestMapping("/addUser")
	public void addUser(@RequestParam(name="id")String id, @RequestParam(name="pwd")String pwd) {
		User user = new User();
		user.setId(id);
		user.setPwd(pwd);
		userRepo.save(user);
		
	}
	
	@TimeLog
	@PostMapping("/register")
	public void register(User user) {
		user.setPwd(pwdEncoder.encode(user.getPwd()));
//		user.setRoles(Arrays.asList(UserRole.of("BASIC")));
		
		logger.info("register : user[{}]", user);
		
		userRepo.save(user);
	}
	
}
