package com.coupons.exception.companyException;

import com.coupons.exception.generalException.AlreadyExistsException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CompanyAlreadyExistsException extends AlreadyExistsException {

	private static final long serialVersionUID = 9051665933246097577L;

	public CompanyAlreadyExistsException(int id) {
		super.errorCode = ExceptionErrorCodeUtil.CompanyAlreadyExistsException.toString();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
		this.id = id;
	}

}
