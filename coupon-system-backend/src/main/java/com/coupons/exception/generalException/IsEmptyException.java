package com.coupons.exception.generalException;

import com.coupons.exception.CustomException;

public abstract class IsEmptyException extends CustomException {

	private static final long serialVersionUID = -3039016111589446069L;
	protected String entityName;

	@Override
	public String toString() {
		return "No " + entityName + "'s exist, you need to add a " + entityName + " to the system.";
	}

}
