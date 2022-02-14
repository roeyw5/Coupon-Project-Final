package com.coupons.utility.service.validation;

import com.coupons.exception.companyException.CompanyIsNullException;
import com.coupons.exception.companyException.CompanyNullValueException;
import com.coupons.model.user.Company;
import com.coupons.utility.exception.ExceptionUtil;

/**
 * Containing methods that helps validate Company business logic.
 */
public interface CompanyValidation extends StringModifier {

	/**
	 *Checks if a company is not null, and doesn't have null values.
	 **/
	public default void companyNullValidation(Company company)
			throws CompanyNullValueException, CompanyIsNullException {
		if (company == null || company.getEmail() == null || company.getPassword() == null || company.getName() == null)
			throw new CompanyIsNullException();
		boolean nameException = company.getName() == null;
		if (!nameException) {
			company.setName(trim(company.getName()));
			nameException = company.getName().isEmpty();
		}
		boolean emailException = company.getEmail() == null;
		if (!emailException) {
			company.setEmail(removeSpace(company.getEmail()).toLowerCase());
			emailException = !isEmail(company.getEmail());
		}
		boolean passwordException = company.getPassword() == null;
		if (!passwordException) {
			passwordException = company.getPassword().isEmpty();
		}
		if (nameException || emailException || passwordException) {
			CompanyNullValueException exception = new CompanyNullValueException();
			if (nameException)
				exception.addNull(ExceptionUtil.NAME);
			if (emailException)
				if (company.getEmail() == null)
					exception.addNull(ExceptionUtil.EMAIL);
				else
					exception.addNull(ExceptionUtil.INVALID_EMAIL_FORMAT);
			if (passwordException)
				exception.addNull(ExceptionUtil.PASSWORD);
			throw exception;
		}
	}

}
