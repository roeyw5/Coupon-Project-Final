package com.coupons.exception.loginException;

import com.coupons.exception.CustomException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;

public class MismatchLoginException extends CustomException {

	private static final long serialVersionUID = 6938953722353707073L;

	public MismatchLoginException() {
		super.errorCode = ExceptionErrorCodeUtil.MismatchLoginException.toString();
	}

	@Override
	public String toString() {
		return "Incorrect user type for the entered details";
	}
}
