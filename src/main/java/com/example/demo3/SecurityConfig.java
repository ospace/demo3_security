package com.example.demo3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	OAuth2ClientContext oauth2ClientContext; 
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/lib/**", "/image/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ADMIN') and hasRole('SUPER')")
			.antMatchers("/user/**").authenticated()
			.antMatchers("/api/**").permitAll()
			.antMatchers("/**").permitAll()
		.and().formLogin()
			.loginPage("/api/login") // 인증페이지 위치(GET)
//			.loginProcessingUrl("/api/login") //인증 처리(POST)
//			.defaultSuccessUrl("/")
			.failureUrl("/api/login")
			.successHandler(new LonginSuccessHandler("/"))
		.and().exceptionHandling()
			.accessDeniedPage("/denied.html")
		.and().logout()
		.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	private Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		
		filters.add(new Filter() {
			@Override
			public void init(FilterConfig filterConfig) throws ServletException {
				logger.info("Filter : init");
			}
			@Override
			public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
					throws IOException, ServletException {
				logger.info("Filter : doFilter[{}]", ((HttpServletRequest)request).getRequestURI());
				chain.doFilter(request, response);
			}
			@Override
			public void destroy() {
				logger.info("Filter : destroy");
			}
		});
		
	
		filters.add(oauth2Filter("/login/facebook"));
		
		filter.setFilters(filters);
		return filter;
	}
	
	private Filter oauth2Filter(String path) {
		OAuth2ClientAuthenticationProcessingFilter oauth2Filter = new OAuth2ClientAuthenticationProcessingFilter(path);
		AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();
		OAuth2RestTemplate template = new OAuth2RestTemplate(client);
		oauth2Filter.setRestTemplate(template);
		ResourceServerProperties resource = new ResourceServerProperties(); 
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(resource.getUserInfoUri(), resource.getClientId()); 
		tokenServices.setRestTemplate(template);
		oauth2Filter.setTokenServices(tokenServices);
		return oauth2Filter;
	}
}
