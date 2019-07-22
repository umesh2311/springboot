package com.security.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.app.impl.UserDetailsImpl;
import com.security.app.model.Users;
import com.security.app.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = userRepo.findByusername(username);
		
		if(user== null) throw new UsernameNotFoundException("User doesn't exist");
			
		return new UserDetailsImpl(user);
	}

}
