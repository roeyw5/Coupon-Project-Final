package com.coupons.exception.companyException;

import com.coupons.exception.generalException.IsNullException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CompanyIsNullException extends IsNullException {

	private static final long serialVersionUID = 4496682272249313607L;

	public CompanyIsNullException() {
		super.errorCode = ExceptionErrorCodeUtil.CompanyIsNullException.toString();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
	}

}
