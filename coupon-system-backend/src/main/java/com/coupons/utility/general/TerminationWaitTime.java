package com.coupons.utility.general;

/**
 * Helper enum that contains the amount of minutes until the DailyJob thread
 * will shut down.
 */
public enum TerminationWaitTime {

	TIME(15);

	private final int MINUTES;

	TerminationWaitTime(int minutes) {
		MINUTES = minutes;
	}

	public int toInt() {
		return MINUTES;
	}

}
