package com.example.demo3;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api")
public class Demo3Controller {
	private static Logger logger = LoggerFactory.getLogger(Demo3Controller.class);
	@Autowired
	private UserRepository userRepo;
	
	private BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder(); 
	
	@ExceptionHandler({Exception.class})
	public void handleException(HttpServletRequest request, Exception ex) {
		logger.error("{}[{}]", ex.getClass().getName(), ex.getMessage(), ex);
	}
	
	@RequestMapping("/test") 
	public String test() {
		logger.info("test");
		return "redirect:/";
	}
	
	@PostMapping("/hello")
	//@RequestMapping(name="/register", method=RequestMethod.POST)
	public String foo() {
		logger.info("foo");
		return "redirect:/";
	}
	
	@PostMapping("/register")
	public String register(User user) {
		logger.info("register : user[{}]", user);
		
		user.setPwd(pwdEncoder.encode(user.getPwd()));
		
		UserRole role = new UserRole();
		role.setName("BASIC");
		user.setRoles(Arrays.asList(role));
		
		userRepo.add(user);
		return "redirect:/";
	}
}
