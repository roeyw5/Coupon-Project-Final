package com.coupons.exception.customerException;

import com.coupons.exception.generalException.IsEmptyException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CustomerIsEmptyException extends IsEmptyException {

	private static final long serialVersionUID = 556323556945208243L;

	public CustomerIsEmptyException() {
		super.errorCode = ExceptionErrorCodeUtil.CustomerIsEmptyException.toString();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
	}

}
