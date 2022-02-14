package com.coupons.exception.customerException;

import com.coupons.exception.generalException.IsNullException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CustomerIsNullException extends IsNullException {

	private static final long serialVersionUID = 6062395087108093928L;

	public CustomerIsNullException() {
		super.errorCode = ExceptionErrorCodeUtil.CustomerIsNullException.toString();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
	}

}
