package com.coupons.exception.loginException;

import com.coupons.exception.CustomException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.service.UserType;

public final class InvalidLoginException extends CustomException {

	private static final long serialVersionUID = -2182762866749059906L;

	private UserType userType;
	private String email;
	private boolean emailExists;

	public InvalidLoginException(String email, boolean emailExists, UserType userType) {
		super.errorCode = ExceptionErrorCodeUtil.InvalidLoginException.toString();
		this.email = email;
		this.emailExists = emailExists;
		this.userType = userType;
	}

	@Override
	public String toString() {
		if (userType.equals(UserType.ADMIN))
			return "An Admin with the email and/or password doesn't exist, make sure the information is correct.";
		if (!emailExists) {
			return "A user with the email: " + email
					+ " doesn't exist, make sure the email is correct or create a new account.";
		}
		return "The password is incorrect, try again.";
	}

}
