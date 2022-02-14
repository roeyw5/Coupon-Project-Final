package com.coupons.utility.exception;

/**
 * Enum that contains information used by the exception 
 * that extend the CustomException class.
 *
 * The method toString should be used after specifying which
 * information is wanted, when using an "ENTITY_" type, in order to get the
 * correct information.

 * The method name should be used when specifying a type that does not
 * contain the "CLASS_", to get the type name.
 */
public enum ExceptionUtil {

	ENTITY_COMPANY, ENTITY_COUPON, ENTITY_CUSTOMER,

	CATEGORY, TITLE, DESCRIPTION, START_DATE, END_DATE, IMAGE, NAME, FIRST_NAME, LAST_NAME, EMAIL,INVALID_EMAIL_FORMAT, PASSWORD, MAX_PRICE,
	PURCHASE, CREATE, ID, UPDATE;

	@Override
	public String toString() {
		switch (this) {
		case ENTITY_COMPANY:
			return "Company";
		case ENTITY_COUPON:
			return "Coupon";
		case ENTITY_CUSTOMER:
			return "Customer";
		default:
			return super.toString();
		}
	}

}
