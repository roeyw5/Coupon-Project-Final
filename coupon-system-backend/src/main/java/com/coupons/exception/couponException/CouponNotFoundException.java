package com.coupons.exception.couponException;

import com.coupons.exception.generalException.NotFoundException;
import com.coupons.utility.enums.Category;
import com.coupons.utility.exception.ExceptionErrorCodeUtil;
import com.coupons.utility.exception.ExceptionUtil;

public final class CouponNotFoundException extends NotFoundException {

	private static final long serialVersionUID = -2097523446869921554L;
	private Category category;
	private double maxPrice;
	private ExceptionUtil toString;

	public CouponNotFoundException(int id) {
		setErrorCode();
		this.id = id;
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
		toString = ExceptionUtil.ID;
	}

	public CouponNotFoundException(Category category) {
		setErrorCode();
		this.category = category;
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
		toString = ExceptionUtil.CATEGORY;
	}

	public CouponNotFoundException(double maxPrice) {
		setErrorCode();
		this.maxPrice = maxPrice;
		entityName = ExceptionUtil.ENTITY_COUPON.toString();
		toString = ExceptionUtil.MAX_PRICE;
	}

	/* toString */
	@Override
	public String toString() {
		if (toString.equals(ExceptionUtil.CATEGORY)) {
			return entityName + " category: " + category + " was not found, make sure that the " + entityName
					+ " category is correct.";
		}
		if (toString.equals(ExceptionUtil.MAX_PRICE)) {
			return entityName + " max price: " + maxPrice + " was not found, make sure that the " + entityName
					+ " max price is correct.";
		}
		return super.toString();
	}
	

	public void setErrorCode() {
		super.errorCode = ExceptionErrorCodeUtil.CouponNotFoundException.toString();
	}
	
	

}
