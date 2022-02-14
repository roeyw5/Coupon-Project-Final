package com.coupons.exception.customerException;

import com.coupons.exception.generalException.NullValueException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CustomerNullValueException extends NullValueException {

	private static final long serialVersionUID = -633491210916568262L;

	public CustomerNullValueException() {
		super.errorCode = ExceptionErrorCodeUtil.CustomerNullValueException.toString();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
	}

}
