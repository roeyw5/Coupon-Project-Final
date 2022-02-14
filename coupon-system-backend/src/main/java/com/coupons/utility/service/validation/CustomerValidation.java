package com.coupons.utility.service.validation;

import com.coupons.exception.customerException.CustomerIsNullException;
import com.coupons.exception.customerException.CustomerNullValueException;
import com.coupons.model.user.Customer;
import com.coupons.utility.exception.ExceptionUtil;

/**
 * Containing methods that helps validate Customer business logic.
 */
public interface CustomerValidation extends StringModifier {

	/**
	 *Checks if a customer is not null, and doesn't have null values.
	 **/
	public default void customerNullValidation(Customer customer)
			throws CustomerIsNullException, CustomerNullValueException {
		if (customer == null)
			throw new CustomerIsNullException();
		boolean firstNameException = customer.getFirstName() == null;
		if (!firstNameException) {
			customer.setFirstName(trim(customer.getFirstName()));
			firstNameException = customer.getFirstName().isEmpty();
		}
		boolean lastNameException = customer.getLastName() == null;
		if (!lastNameException) {
			customer.setLastName(trim(customer.getLastName()));
			lastNameException = customer.getLastName().isEmpty();
		}
		boolean emailException = customer.getEmail() == null;
		if (!emailException) {
			customer.setEmail(removeSpace(customer.getEmail()).toLowerCase());
			emailException = !isEmail(customer.getEmail());
		}
		boolean passwordException = customer.getPassword() == null;
		if (!passwordException)
			passwordException = customer.getPassword().isEmpty();
		if (firstNameException || lastNameException || emailException || passwordException) {
			CustomerNullValueException exception = new CustomerNullValueException();
			if (firstNameException)
				exception.addNull(ExceptionUtil.FIRST_NAME);
			if (lastNameException)
				exception.addNull(ExceptionUtil.LAST_NAME);
			if (emailException)
				if (customer.getEmail() == null)
					exception.addNull(ExceptionUtil.EMAIL);
				else
					exception.addNull(ExceptionUtil.INVALID_EMAIL_FORMAT);
			if (passwordException)
				exception.addNull(ExceptionUtil.PASSWORD);
			throw exception;
		}
	}

}
