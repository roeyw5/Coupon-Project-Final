package com.coupons.exception.couponException;

import com.coupons.utility.exception.ExceptionErrorCodeUtil;

public final class CouponOwnedException extends CouponException {

	private static final long serialVersionUID = 9010697755872441982L;

	public CouponOwnedException(int id) {
		super.errorCode = ExceptionErrorCodeUtil.CouponOwnedException.toString();
		this.id = id;
	}

	@Override
	public String toString() {
		return super.toString() + " is already owned, you can't purchase already owned coupons.";
	}

}
