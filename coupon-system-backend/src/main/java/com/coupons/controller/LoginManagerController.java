package com.coupons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coupons.exception.CustomException;
import com.coupons.message.response.ExceptionResponse;
import com.coupons.service.ifc.LoginManagerServiceIfc;
import com.coupons.utility.service.UserType;

/**
 * Controller for the login manager
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login-manager")
public class LoginManagerController {

	@Autowired
	private LoginManagerServiceIfc loginManagerService;

	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password, @RequestParam UserType userType) {
		try {
			return loginManagerService.login(email, password, userType);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}


}
