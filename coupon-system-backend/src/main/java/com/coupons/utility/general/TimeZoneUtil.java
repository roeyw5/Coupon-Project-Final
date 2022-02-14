package com.coupons.utility.general;

/**
 * Enum that contains the current time zone.
 */
public enum TimeZoneUtil {

	ISRAEL;

	@Override
	public String toString() {
		switch (this) {
		case ISRAEL:
			return "Israel";
		default:
			return super.toString();
		}
	}

}
