package com.coupons.exception.couponException;

import java.util.Date;

import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CouponExpiredPurchaseException extends CouponExpiredException {

	private static final long serialVersionUID = 218981284770480943L;

	public CouponExpiredPurchaseException(int id, Date date) {
		super.errorCode = ExceptionErrorCodeUtil.CouponExpiredPurchaseException.toString();
		this.id = id;
		this.date = date;
		this.action = ExceptionUtil.PURCHASE.name().toLowerCase();
	}

}
