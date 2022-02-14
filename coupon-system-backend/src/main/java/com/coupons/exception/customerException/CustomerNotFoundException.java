package com.coupons.exception.customerException;

import com.coupons.exception.generalException.NotFoundException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CustomerNotFoundException extends NotFoundException {

	private static final long serialVersionUID = -5686637033251638610L;

	public CustomerNotFoundException(int id) {
		setErrorCode();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
		this.id = id;
	}

	public CustomerNotFoundException(String email) {
		setErrorCode();
		entityName = ExceptionUtil.ENTITY_CUSTOMER.toString();
		this.email = email;
	}
	

	public void setErrorCode() {
		super.errorCode = ExceptionErrorCodeUtil.CustomerNotFoundException.toString();
	}

}
