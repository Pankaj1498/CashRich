package com.pm.cr.service;

import org.springframework.http.ResponseEntity;

import com.pm.cr.model.RegistrationModel;

public interface UserLoginService {

	String login(String username, String password);

	ResponseEntity<String> updateUser(RegistrationModel registrationModel) throws Exception;

	

	

}
