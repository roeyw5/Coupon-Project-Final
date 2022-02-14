package com.coupons.utility.service;

import com.coupons.model.Coupon;
import com.coupons.repository.CouponRepository;

/**
 * Helper method that deletes a coupon and removes it from all customers and companies
 */
public interface CouponDeleter {

	public default void deleteCoupon(Coupon coupon, CouponRepository couponRepository) {
		coupon.setCustomers(null);
		coupon.setCompany(null);
		couponRepository.save(coupon);
		couponRepository.delete(coupon);
	}

}
