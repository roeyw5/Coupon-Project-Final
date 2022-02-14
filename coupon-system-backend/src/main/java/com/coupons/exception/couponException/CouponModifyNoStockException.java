package com.coupons.exception.couponException;

import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public class CouponModifyNoStockException extends CouponNoStockException {

	private static final long serialVersionUID = -1695511067874496587L;

	public CouponModifyNoStockException(int id) {
		super.errorCode = ExceptionErrorCodeUtil.CouponModifyNoStockException.toString();
		action = ExceptionUtil.UPDATE.name().toLowerCase() + " or " + ExceptionUtil.CREATE.name().toLowerCase();
		this.id = id;
	}

}
