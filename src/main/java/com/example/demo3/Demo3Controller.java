package com.example.demo3;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String foo() {
		logger.info("foo");
		return "redirect:/";
	}
	
	@PostMapping("/register")
	public String register(User user) {
		user.setPwd(pwdEncoder.encode(user.getPwd()));
		
		logger.info("register : user[{}]", user);
		
		UserRole role = new UserRole();
		role.setName("BASIC");
		user.setRoles(Arrays.asList(role));
		
		userRepo.add(user);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String loginFrom(HttpServletRequest req) {
		String referer = req.getHeader("Referer");
		req.getSession().setAttribute("prevPage", referer);
		logger.info("login : Referer[{}]", referer);
		return "login";
	}
}
