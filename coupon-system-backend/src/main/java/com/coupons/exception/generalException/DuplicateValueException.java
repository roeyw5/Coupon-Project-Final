package com.coupons.exception.generalException;

import com.coupons.exception.CustomException;

public abstract class DuplicateValueException extends CustomException {

	private static final long serialVersionUID = 2023156129898996103L;
	protected String duplicatesString;
	protected String duplicateValue;
	protected String entityName;

	@Override
	public String toString() {
		return duplicateValue + " already exists in the " + entityName + " system, you need to change your "
				+ duplicatesString + ".";
	}

}
