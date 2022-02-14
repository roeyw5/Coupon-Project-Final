package com.coupons.exception.couponException;

import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public class CouponPurchaseNoStockException extends CouponNoStockException {

	private static final long serialVersionUID = 1079138568606931980L;

	public CouponPurchaseNoStockException(int id) {
		super.errorCode = ExceptionErrorCodeUtil.CouponPurchaseNoStockException.toString();
		action = ExceptionUtil.PURCHASE.name().toLowerCase();
		this.id = id;
	}

}
