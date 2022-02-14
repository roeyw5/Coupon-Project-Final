package com.coupons.service.ifc;

import org.springframework.http.ResponseEntity;

import com.coupons.exception.loginException.InvalidLoginException;
import com.coupons.exception.loginException.MismatchLoginException;
import com.coupons.exception.loginException.NullLoginException;
import com.coupons.utility.service.UserType;

/*
 * Interface with the methods for LoginManagerService,
 * to take care of the login system.
 */

public interface LoginManagerServiceIfc {

	public ResponseEntity<?> login(String email, String password, UserType userType)
			throws InvalidLoginException, NullLoginException, MismatchLoginException;

}
