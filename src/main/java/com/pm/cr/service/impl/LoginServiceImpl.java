package com.pm.cr.service.impl;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pm.cr.model.RegistrationModel;
import com.pm.cr.repository.RegistrationRespository;
import com.pm.cr.service.UserLoginService;

@Service
public class LoginServiceImpl implements UserLoginService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RegistrationRespository registrationRespository;

	@Override
	public String login(String username, String password) {
		try {

			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return "Login successful";
		} catch (BadCredentialsException ex) {
			return "Invalid credentials";
		}
	}

	@Override
	public ResponseEntity<String> updateUser(RegistrationModel registrationModel) throws Exception {
		RegistrationModel user = registrationRespository.findById(registrationModel.getId())
				.orElseThrow(() -> new AccountNotFoundException("User not Found"));

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.toString());
		String currentUsername = authentication.getName();
		System.out.println(currentUsername);

		if (!currentUsername.equals(registrationModel.getUsername())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorized to update this user");
		}
		if (registrationModel.getFirstname() != null) {
			user.setFirstname(registrationModel.getFirstname());
		}
		if (registrationModel.getLastname() != null) {
			user.setLastname(registrationModel.getLastname());
		}
		if (registrationModel.getMobileNo() != null) {
			user.setMobileNo(registrationModel.getMobileNo());
		}
		if (registrationModel.getPassword() != null) {

			user.setPassword(passwordEncoder.encode(registrationModel.getPassword()));
		}

		registrationRespository.save(user);
		return ResponseEntity.status(HttpStatus.OK).body("Details Updated`");

	}

}
