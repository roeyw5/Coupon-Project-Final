package com.coupons.exception.couponException;

import com.coupons.utility.exception.ExceptionErrorCodeUtil;

public final class CouponNegativePriceException extends CouponException {

	private static final long serialVersionUID = 7509044611898086927L;
	private double price;

	public CouponNegativePriceException(double price) {
		super.errorCode = ExceptionErrorCodeUtil.CouponNegativePriceException.toString();
		this.price = price;
	}

	@Override
	public String toString() {
		return "The coupon price cannot be: " + price + ", the minimum value is: 0.";
	}

}
