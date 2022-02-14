package com.coupons.exception.generalException;

import com.coupons.exception.CustomException;

public abstract class IsNullException extends CustomException {

	private static final long serialVersionUID = 6918078131467279137L;
	protected String entityName;

	@Override
	public String toString() {
		return "The " + entityName + " value is \"null\" or contains nulls, you need to initalize the " + entityName + " properly.";
	}
}
