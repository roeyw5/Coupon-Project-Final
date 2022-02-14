package com.coupons.utility.service;

/**
 * Default username and password of the admin.
 */
public enum AdminDetails {

	/* attributes */
	ADMIN_USER, ADMIN_PASS;

	/* toString */
	@Override
	public String toString() {
		switch (this) {
		case ADMIN_USER:
			return "admin@admin.com";
		case ADMIN_PASS:
			return "admin";
		default:
			return super.toString();
		}
	}

}
