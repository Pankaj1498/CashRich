package com.pm.cr.service.impl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pm.cr.model.RegistrationModel;
import com.pm.cr.repository.RegistrationRespository;
import com.pm.cr.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationRespository registrationRespository;

	@Autowired
	PasswordEncoder passwordEncoder;

	private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,15}$";

	private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);

	@Override
	public RegistrationModel createUser(RegistrationModel registrationModel) throws Exception {
		if (!isValidUsername(registrationModel.getUsername())) {
			throw new IllegalArgumentException("Invalid username format");
		}

		if (!isValidEmail(registrationModel.getEmail())) {
			throw new IllegalArgumentException("Invalid email format");
		}

		if (!isValidPassword(registrationModel.getPassword())) {
			throw new IllegalArgumentException("Invalid password format");
		}

		if (!isEmailUnique(registrationModel.getEmail())) {
			throw new IllegalArgumentException("Email already exists");
		}
		if (!isUsernameUnique(registrationModel.getUsername())) {
			throw new IllegalArgumentException("Username already exists");
		}

		String encryptedPassword = passwordEncoder.encode(registrationModel.getPassword());
		registrationModel.setPassword(encryptedPassword);

		return registrationRespository.save(registrationModel);
	}

	private boolean isValidUsername(String username) {
		String usernamePattern = "^[a-zA-Z0-9]{4,15}$";
		return username.matches(usernamePattern);
	}

	public static boolean isValidEmail(String email) {
		return pattern.matcher(email).matches();
	}

	public static boolean isValidPassword(String password) {
		return passwordPattern.matcher(password).matches();
	}

	public boolean isEmailUnique(String email) {
		return registrationRespository.findByEmail(email) == null;
	}

	public boolean isUsernameUnique(String username) {
		return registrationRespository.findByUsername(username) == null;
	}

}
