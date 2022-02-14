package com.coupons.utility.service;

/**
 * Enum containing the type of users in the system.
 */
public enum UserType {

	ADMIN, COMPANY, CUSTOMER;

	@Override
	public String toString() {
		switch (this) {
		case ADMIN:
			return "Admin";
		case COMPANY:
			return "Company";
		case CUSTOMER:
			return "Customer";
		default:
			return super.toString();
		}
	}

}
