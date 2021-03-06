package com.tsk.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tsk.ecommerce.entities.auth.UserEntity;
import com.tsk.ecommerce.service.user.UserService;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEntity = userService.getByUsername(username);
		
		return CustomUserDetails.fromUserEntityToUserDetails(userEntity);
	}

}
