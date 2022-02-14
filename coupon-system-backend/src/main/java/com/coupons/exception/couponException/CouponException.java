package com.coupons.exception.couponException;

import com.coupons.exception.CustomException;

public abstract class CouponException extends CustomException {

	private static final long serialVersionUID = -148510015680968765L;
	protected int id;
	
	@Override
	public String toString() {
		return "Coupon id: " + id;
	}

}
