package com.coupons.exception.companyException;

import com.coupons.exception.generalException.NullValueException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CompanyNullValueException extends NullValueException {

	private static final long serialVersionUID = -6461212727416722187L;

	public CompanyNullValueException() {
		super.errorCode = ExceptionErrorCodeUtil.CompanyNullValueException.toString();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
	}

}
