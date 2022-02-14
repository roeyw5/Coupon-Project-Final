package com.coupons.exception.couponException;

import com.coupons.exception.generalException.IsNullException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CouponIsNullException extends IsNullException {

	private static final long serialVersionUID = 2343329825146715443L;

	public CouponIsNullException() {
		super.errorCode = ExceptionErrorCodeUtil.CouponIsNullException.toString();
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
	}

}
