package com.coupons.exception.couponException;

import com.coupons.exception.generalException.DuplicateValueException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CouponDuplicateValueException extends DuplicateValueException {

	private static final long serialVersionUID = -6334847561117102189L;

	public CouponDuplicateValueException(String couponTitle) {
		super.errorCode = ExceptionErrorCodeUtil.CouponDuplicateValueException.toString();
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
		duplicatesString = ExceptionUtil.TITLE.name().toLowerCase();
		this.duplicateValue = couponTitle;
	}
}
