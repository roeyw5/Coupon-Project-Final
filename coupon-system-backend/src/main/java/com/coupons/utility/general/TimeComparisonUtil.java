package com.coupons.utility.general;

import java.sql.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Interface that contains methods related to
 * time comparison.
 */
public interface TimeComparisonUtil {

	/**
	 * Returns true if the endDate is in the past.
	 */
	public default boolean isPast(Date sqlDate) {
		Date now = Date.valueOf(ZonedDateTime.now(ZoneId.of(TimeZoneUtil.ISRAEL.toString()))
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		if (now.after(sqlDate)) {
			return true;
		}
		return false;
	}

}
