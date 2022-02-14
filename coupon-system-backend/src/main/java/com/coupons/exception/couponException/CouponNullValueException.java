package com.coupons.exception.couponException;

import com.coupons.exception.generalException.NullValueException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CouponNullValueException extends NullValueException {

	private static final long serialVersionUID = 3947556183985859582L;

	public CouponNullValueException() {
		super.errorCode = ExceptionErrorCodeUtil.CouponNullValueException.toString();
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
	}

}
