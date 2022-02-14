package com.coupons.exception.generalException;

import com.coupons.exception.CustomException;

public abstract class AlreadyExistsException extends CustomException {

	private static final long serialVersionUID = 8495176260690798221L;
	protected int id;
	protected String entityName;

	@Override
	public String toString() {
		return "The " + entityName + " with the id " + id + " already exists, try creating a \"new\" " + entityName
				+ ".";
	}
}
