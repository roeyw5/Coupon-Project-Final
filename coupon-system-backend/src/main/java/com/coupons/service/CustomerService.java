package com.coupons.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coupons.exception.couponException.CouponExpiredPurchaseException;
import com.coupons.exception.couponException.CouponIsEmptyException;
import com.coupons.exception.couponException.CouponNotFoundException;
import com.coupons.exception.couponException.CouponOwnedException;
import com.coupons.exception.couponException.CouponPurchaseNoStockException;
import com.coupons.model.Coupon;
import com.coupons.model.user.Customer;
import com.coupons.repository.CouponRepository;
import com.coupons.repository.user.CustomerRepository;
import com.coupons.service.ifc.CustomerServiceIfc;
import com.coupons.utility.enums.Category;
import com.coupons.utility.general.StringClass;
import com.coupons.utility.general.TimeComparisonUtil;

/*
 * Handles the business logic of the system.
 */

@Service
public class CustomerService
		implements CustomerServiceIfc, TimeComparisonUtil {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CouponRepository couponRepository;
	
	/**
	 * Log-in as a customer into the system. Checks if the email and password in the
	 * argument match a Customer table in the database.
	 */
	@Override
	public boolean login(String email, String password) {
		Optional<Customer> optionCustomer = customerRepository.findByEmailIgnoreCaseAndPassword(email, password);
		if (!optionCustomer.isPresent())
			return false;
		return true;
	}

	/**
	 * Purchases a Coupon for the logged-in Customer unless the inserted coupon is
	 * expired or not in stock.
	 */
	@Override
	public Coupon purchaseCoupon(int customerId, int couponId) throws CouponOwnedException, CouponNotFoundException,
			CouponPurchaseNoStockException, CouponExpiredPurchaseException {
		synchronized (StringClass.COUPON_ID_SYNC + couponId) {
			if (couponRepository.findByCustomerIdAndCouponId(customerId, couponId).isPresent())
				throw new CouponOwnedException(couponId);
			Optional<Coupon> couponOption = couponRepository.findById(couponId);
			if (!couponOption.isPresent())
				throw new CouponNotFoundException(couponId);
			Coupon purchasedCoupon = couponOption.get();
			if (purchasedCoupon.getAmount() <= 0)
				throw new CouponPurchaseNoStockException(couponId);
			if (isPast(purchasedCoupon.getEndDate())) {
				throw new CouponExpiredPurchaseException(purchasedCoupon.getId(), purchasedCoupon.getEndDate());
			}
			Customer customer = getCustomerDetails(customerId);
			purchasedCoupon.setAmount(purchasedCoupon.getAmount() - 1);
			customer.getCoupons().add(purchasedCoupon);
			customerRepository.save(customer);
			return couponRepository.save(purchasedCoupon);
		}
	}
	
	/**
	 * Fetches a list of the logged-in Customer's Coupons.
	 */
	@Override
	public Set<Coupon> getCustomerCoupons(int customerId) throws CouponIsEmptyException {
		Set<Coupon> coupons = couponRepository.findByCustomerId(customerId);
		if (coupons.isEmpty())
			throw new CouponIsEmptyException();
		return coupons;
	}

	/**
	 * Fetches a list of a Customer's Coupons based on the price (only coupons priced
	 * lower than the inserted value will be returned).
	 */
	@Override
	public Set<Coupon> getCustomerCoupons(int customerId, double maxPrice) throws CouponNotFoundException {
		Set<Coupon> coupons = couponRepository.findByCustomerIdAndCouponPrice(customerId, maxPrice);
		if (coupons.isEmpty())
			throw new CouponNotFoundException(maxPrice);
		return coupons;
	}

	/**
	 * Fetches a list of the logged-in Customer's Coupons (based on the inserted
	 * Category).
	 */
	@Override
	public Set<Coupon> getCustomerCoupons(int customerId, Category category) throws CouponNotFoundException {
		Set<Coupon> coupons = couponRepository.findByCustomerIdAndCouponCategory(customerId, category);
		if (coupons.isEmpty())
			throw new CouponNotFoundException(category);
		return coupons;
	}

	/**
	 * Fetches the data parameters of the logged-in Customer.
	 */
	@Override
	public Customer getCustomerDetails(int customerId) {
		return customerRepository.findById(customerId).get();
	}
	
	/**
	*Helper method, check if customer exists by email
	*/
	@Override
	public boolean customerExists(String email) {
		return customerRepository.findByEmailIgnoreCase(email).isPresent();
	}

}
