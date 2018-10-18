package com.example.demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService; 
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/lib/**", "/image/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ADMIN') and hasRole('SUPER')")
			.antMatchers("/user/**").authenticated()
			.antMatchers("/api/**").permitAll()
			.antMatchers("/**").permitAll()
		.and().formLogin()
			.loginPage("/api/login") // 인증페이지 위치(GET)
//			.loginProcessingUrl("/api/login") //인증 처리(POST)
			.defaultSuccessUrl("/")
			.failureUrl("/api/login")
		.and().exceptionHandling()
			.accessDeniedPage("/denied.html")
		.and().logout();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
