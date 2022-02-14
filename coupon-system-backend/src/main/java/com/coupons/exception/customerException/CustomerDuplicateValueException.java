package com.coupons.exception.customerException;

import com.coupons.exception.generalException.DuplicateValueException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CustomerDuplicateValueException extends DuplicateValueException {

	private static final long serialVersionUID = -3439177959321226748L;

	public CustomerDuplicateValueException(String customerEmail) {
		super.errorCode = ExceptionErrorCodeUtil.CustomerDuplicateValueException.toString();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
		duplicatesString = ExceptionUtil.EMAIL.name().toLowerCase();
		this.duplicateValue = customerEmail;
	}
}
