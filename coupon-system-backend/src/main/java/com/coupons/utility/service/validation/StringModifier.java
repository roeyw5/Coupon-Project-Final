package com.coupons.utility.service.validation;

import java.util.regex.Pattern;


public interface StringModifier {

	/**
	 * Trims and removes extra spaces from Strings.
	 */
	public default String trim(String string) {
		if (string == null)
			return null;
		return string.trim().replaceAll(" +", " ");
	}

	/**
	 * Removes all spaces from Strings.
	 */
	public default String removeSpace(String string) {
		if (string == null)
			return null;
		return string.replace(" ", "");
	}

	/**
	 * True if the String is in email format. 
	 * False otherwise, or if it's null
	 */
	public default boolean isEmail(String email) {
		if (email == null)
			return false;
		return Pattern.compile("^(.+)@(.+)$").matcher(email).matches();
	}

}
