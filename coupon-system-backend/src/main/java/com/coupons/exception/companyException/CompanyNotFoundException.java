package com.coupons.exception.companyException;

import com.coupons.exception.generalException.NotFoundException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CompanyNotFoundException extends NotFoundException {

	private static final long serialVersionUID = 4924233908046554183L;

	public CompanyNotFoundException(int id) {
		setErrorCode();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
		this.id = id;
	}

	public CompanyNotFoundException(String email) {
		setErrorCode();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
		this.email = email;
	}
	
	public void setErrorCode() {
		super.setErrorCode(ExceptionErrorCodeUtil.CompanyNotFoundException.toString());
	}

}
