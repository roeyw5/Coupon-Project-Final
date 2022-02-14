package com.coupons.exception.customerException;

import com.coupons.exception.generalException.AlreadyExistsException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CustomerAlreadyExistsException extends AlreadyExistsException {

	private static final long serialVersionUID = 2638421074780523510L;

	public CustomerAlreadyExistsException(int id) {
		super.errorCode = ExceptionErrorCodeUtil.CustomerAlreadyExistsException.toString();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
		this.id = id;
	}

}
