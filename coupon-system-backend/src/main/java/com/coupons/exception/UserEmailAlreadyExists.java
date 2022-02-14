package com.coupons.exception;

import com.coupons.utility.exception.ExceptionErrorCodeUtil;

public class UserEmailAlreadyExists extends CustomException {

	private static final long serialVersionUID = 7409449700762540450L;
	private String email;

	public UserEmailAlreadyExists(String email) {
		super.errorCode = ExceptionErrorCodeUtil.UserEmailAlreadyExists.toString();
		this.email = email;
	}

	@Override
	public String toString() {
		return "A user with the adress \"" + email + "\" already exists, please choose a different adress.";
	}

}
