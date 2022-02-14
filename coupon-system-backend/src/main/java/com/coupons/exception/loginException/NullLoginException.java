package com.coupons.exception.loginException;

import com.coupons.exception.CustomException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;

public final class NullLoginException extends CustomException {

	private static final long serialVersionUID = -9138544192447606819L;

	public NullLoginException() {
		super.errorCode = ExceptionErrorCodeUtil.NullLoginException.toString();
	}

	@Override
	public String toString() {
		return "The email, password or client value is null, make sure you enter all the needed information.";
	}

}
