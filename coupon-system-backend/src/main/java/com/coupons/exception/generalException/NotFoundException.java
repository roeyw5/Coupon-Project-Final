package com.coupons.exception.generalException;

import com.coupons.exception.CustomException;

public abstract class NotFoundException extends CustomException {

	private static final long serialVersionUID = -4992870382681308172L;
	protected int id;
	protected String entityName;
	protected String email;

	@Override
	public String toString() {
		if (email != null) {
			return entityName + " email: " + email + " was not found, make sure that the " + entityName
					+ " email is correct.";
		}
		return entityName + " id: " + id + " was not found, make sure that the " + entityName + " id is correct.";
	}

}
