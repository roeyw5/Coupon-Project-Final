package com.coupons.service.ifc;

import java.util.Set;

import com.coupons.exception.couponException.CouponExpiredPurchaseException;
import com.coupons.exception.couponException.CouponIsEmptyException;
import com.coupons.exception.couponException.CouponNotFoundException;
import com.coupons.exception.couponException.CouponOwnedException;
import com.coupons.exception.couponException.CouponPurchaseNoStockException;
import com.coupons.model.Coupon;
import com.coupons.model.user.Customer;
import com.coupons.utility.enums.Category;

/*
 * Interface with the methods for CustomerService,
 * to take care of the business logic in the system.
 */

public interface CustomerServiceIfc extends ClientLogin {

	boolean customerExists(String email);

	public Coupon purchaseCoupon(int customerId, int couponId) throws CouponOwnedException, CouponNotFoundException,
			CouponPurchaseNoStockException, CouponExpiredPurchaseException;

	public Set<Coupon> getCustomerCoupons(int customerId) throws CouponIsEmptyException;

	public Set<Coupon> getCustomerCoupons(int customerId, double maxPrice) throws CouponNotFoundException;

	public Set<Coupon> getCustomerCoupons(int customerId, Category category) throws CouponNotFoundException;

	public Customer getCustomerDetails(int customerId);

}
