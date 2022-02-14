package com.coupons.exception.companyException;

import com.coupons.exception.generalException.IsEmptyException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public class CompanyIsEmptyException extends IsEmptyException {

	private static final long serialVersionUID = 556323556945208243L;

	public CompanyIsEmptyException() {
		super.errorCode = ExceptionErrorCodeUtil.CompanyIsEmptyException.toString();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
	}

}