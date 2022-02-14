package com.coupons.exception;

import java.util.ArrayList;
import java.util.List;

import com.coupons.utility.exception.ExceptionErrorCodeUtil;

public class CustomExceptionList extends CustomException {

	private static final long serialVersionUID = 4139293387609694779L;
	private List<CustomException> exceptions;

	public CustomExceptionList() {
		super.errorCode=ExceptionErrorCodeUtil.CustomExceptionList.toString();
		exceptions = new ArrayList<>();
	}

	public void addException(CustomException exception) {
		exceptions.add(exception);
	}

	@Override
	public String toString() {
		String toString = "";
		for (CustomException customException : exceptions) {
			toString += customException.toString() + "\n";
		}
		return toString.substring(0,toString.length()-1);
	}
	
}
