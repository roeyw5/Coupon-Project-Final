package com.coupons.exception.couponException;

public abstract class CouponNoStockException extends CouponException {

	private static final long serialVersionUID = -8169406035430103383L;
	protected String action;

	@Override
	public String toString() {
		return super.toString() + " is out of stock, you can't " + action + " coupons that are out of stock.";
	}

}
