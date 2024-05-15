package com.pm.cr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pm.cr.model.RegistrationModel;
import com.pm.cr.repository.UserLoginRepository;

@Service
public class UserLoginServiceImpl implements UserDetailsService {

	@Autowired
	private UserLoginRepository userLoginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		RegistrationModel user = userLoginRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
				.password(user.getPassword()).roles("USER").build();
	}

}
