package com.pm.cr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pm.cr.model.RegistrationModel;
import com.pm.cr.service.RegistrationService;
import com.pm.cr.service.UserLoginService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserLoginController {

	@Autowired
	private UserLoginService userLoginService;

	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
		String loginMessage = userLoginService.login(username, password);

		if ("Login successful".equals(loginMessage)) {
			return ResponseEntity.ok(loginMessage);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMessage);
		}
	}

	@PutMapping("/update-user")
	public ResponseEntity<String> updateUser(@RequestBody RegistrationModel registrationModel) throws Exception {
		return userLoginService.updateUser(registrationModel);
	}
}
