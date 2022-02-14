package com.coupons.exception.couponException;

import java.util.Date;

public abstract class CouponExpiredException extends CouponException {

	private static final long serialVersionUID = 659136974186842471L;	/*  */
	protected Date date;
	protected String action;
	
	@Override
	public String toString() {
		return super.toString() + " has expired at: " + date + ", you can't " + action + " expired coupons.";
	}

}
