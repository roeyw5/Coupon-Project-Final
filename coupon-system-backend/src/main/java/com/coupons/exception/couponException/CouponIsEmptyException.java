package com.coupons.exception.couponException;

import com.coupons.exception.generalException.IsEmptyException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public class CouponIsEmptyException extends IsEmptyException {

	private static final long serialVersionUID = 556323556945208243L;

	public CouponIsEmptyException() {
		super.errorCode = ExceptionErrorCodeUtil.CouponIsEmptyException.toString();
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
	}

}
