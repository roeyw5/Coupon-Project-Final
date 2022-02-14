package com.coupons.exception.couponException;

import com.coupons.exception.generalException.AlreadyExistsException;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CouponAlreadyExistsException extends AlreadyExistsException {

	private static final long serialVersionUID = 8381693827455066967L;

	public CouponAlreadyExistsException(int id) {
		super.errorCode = ExceptionErrorCodeUtil.CouponAlreadyExistsException.toString();
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
		this.id = id;
	}

}
