package com.example.demo3;

//import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.annotation.PropertySources;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo3.annotation.TimeLog;
import com.example.demo3.entity.Department;
import com.example.demo3.entity.GroupUser;
import com.example.demo3.entity.User;
import com.example.demo3.repository.DepartmentRepositoryJPA;
import com.example.demo3.repository.GroupUserRepositoryJPA;
import com.example.demo3.repository.UserRepositoryJPA;

//https://www.baeldung.com/spring-security-acl

@RestController
@RequestMapping("/api")
public class Demo3RestController {
	private static Logger logger = LoggerFactory.getLogger(Demo3RestController.class);
	
//	private Demo3Configuration2 config2 = Demo3Configuration2.instance();
	
	@Autowired
	private Demo3Configuration config;
	
	@Autowired
	private UserRepositoryJPA userRepo;
	
	@Autowired
	private GroupUserRepositoryJPA groupUserRepo;
	
	@Autowired
	private DepartmentRepositoryJPA deptRepo;
	
	@Autowired(required=false)
	private FooComponent foo;
//	@Autowired
//	private UserMapper userMapper;
	
	private BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder(); 
	
	@PostConstruct
//	@RequestMapping("/init") 
	private void init() {
//		userRepo.save(User.of("z", pwdEncoder.encode("z"), Arrays.asList(UserRole.of("BASIC"))));
//		userRepo.save(User.of("z", pwdEncoder.encode("z"), null));
//		userRepo.save(User.of("y", pwdEncoder.encode("y"), Arrays.asList(UserRole.of("ADMIN"))));
//		
//		logger.info("inited");
		logger.info("FooComponent : {}", (null==foo?"disable":"enable"));
		logger.info(">> name : {}, {}", config.getName(), config.getName2());
		
		Demo3Configuration2 config2 = Demo3Configuration2.instance();
		logger.info("Demo3Configuration2 config : {}", config2.getPropertiesFile());
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
	
	@RequestMapping("/groupUser/{id}")
	@Retryable(value = { Exception.class }, maxAttempts = 2)
	public GroupUser getGroupUser(@PathVariable("id")String id) {
		return groupUserRepo.findById(Integer.parseInt(id)).get();
	}
	
	@RequestMapping("/project/{id}")
	public Department getProject(@PathVariable("id")String id) {
		return deptRepo.findById(Integer.parseInt(id)).get();
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
