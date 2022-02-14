package com.coupons.exception.generalException;

import com.coupons.exception.CustomException;
import com.coupons.utility.exception.ExceptionUtil;

public abstract class NullValueException extends CustomException {

	private static final long serialVersionUID = -4344357858102499390L;
	protected byte numOfNull;
	protected ExceptionUtil nullName;
	protected String entityName;
	protected String nullsString;

	public void addNull(ExceptionUtil nameOfNull) {
		numOfNull++;
		if (nullsString == null) {
			nullsString = nameOfNull.name().toLowerCase();
		} else {
			nullsString = nullsString + ", " + nameOfNull.name().toLowerCase();
		}
	}

	@Override
	public String toString() {
		return "You cannot have any \"null\" values in the " + entityName + ", you didn't enter a value for "
				+ numOfNull + " values which are " + nullsString + ".";
	}

}
