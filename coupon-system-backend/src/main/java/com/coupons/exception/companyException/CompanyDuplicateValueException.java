package com.coupons.exception.companyException;

import com.coupons.exception.generalException.DuplicateValueException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CompanyDuplicateValueException extends DuplicateValueException {

	private static final long serialVersionUID = -8022985052160567479L;

	public CompanyDuplicateValueException() {
		super.errorCode = ExceptionErrorCodeUtil.CompanyDuplicateValueException.toString();
		entityName = ExceptionUtil.ENTITY_COMPANY.toString();
	}

	public void addDuplicate(ExceptionUtil duplicateName, String duplicateValue) {
		if (duplicatesString == null) {
			duplicatesString = duplicateName.name().toLowerCase();
			this.duplicateValue = duplicateValue;
		} else {
			duplicatesString = duplicatesString + ", " + duplicateName.name().toLowerCase();
			this.duplicateValue = this.duplicateValue + ", " + duplicateValue;
		}
	}
}
