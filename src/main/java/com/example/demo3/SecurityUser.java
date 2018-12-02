package com.example.demo3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SecurityUser extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 6534003199197032736L;
	
	private static final String ROLE_PREFIX = "ROLE_";
	public SecurityUser(User user) {
//		super(user.getId(), user.getPwd(), createAuthority(user.getRoles()));
		super(user.getId(), user.getPwd(), null);
	}

	private static List<GrantedAuthority> createAuthority(List<UserRole> roles) {
		List<GrantedAuthority> ret = new ArrayList<>();
		if(null != roles) {
			for(UserRole it : roles) ret.add(createAuthority(it));
		}
		return ret;
	}
	
	private static GrantedAuthority createAuthority(UserRole role) {
		return new SimpleGrantedAuthority(ROLE_PREFIX+role.getName());
	}
	
	@Override
	public boolean isAccountNonExpired() { return true; }
	
	@Override
	public boolean isAccountNonLocked() { return true; }
	
	@Override
	public boolean isCredentialsNonExpired() { return true; }
	
	@Override
	public boolean isEnabled() { return true; }
	
}
