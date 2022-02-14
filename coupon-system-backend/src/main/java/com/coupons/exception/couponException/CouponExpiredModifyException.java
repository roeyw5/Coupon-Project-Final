package com.coupons.exception.couponException;

import java.util.Date;

import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CouponExpiredModifyException extends CouponExpiredException {

	private static final long serialVersionUID = -746966472553091269L;

	public CouponExpiredModifyException(int id, Date date) {
		super.errorCode = ExceptionErrorCodeUtil.CouponExpiredModifyException.toString();
		this.id = id;
		this.date = date;
		action = ExceptionUtil.CREATE.name().toLowerCase() + " or " + ExceptionUtil.UPDATE.name().toLowerCase();
	}

}
