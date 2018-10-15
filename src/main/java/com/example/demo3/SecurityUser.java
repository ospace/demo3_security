package com.example.demo3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SecurityUser extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 6534003199197032736L;
	
	private static final String ROLE_PREFIX = "ROLE_";
	public SecurityUser(User user) {
		super(user.getId(), user.getPwd(), createAuthority(user.getRoles()));
	}

	protected static List<GrantedAuthority> createAuthority(List<UserRole> roles) {
		List<GrantedAuthority> ret = new ArrayList<>();
		for(UserRole it : roles) ret.add(new SimpleGrantedAuthority(ROLE_PREFIX+it.getName()));
		return ret;
	}
}
