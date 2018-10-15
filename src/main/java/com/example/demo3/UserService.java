package com.example.demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = userRepo.get(id);
		if(null == user) throw new UsernameNotFoundException(id); 
		return new SecurityUser(user);
	}

}
