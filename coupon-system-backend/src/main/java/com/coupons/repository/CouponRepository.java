package com.coupons.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coupons.model.Coupon;
import com.coupons.utility.enums.Category;

/*
 * Repository for the Coupon actions in the system.
 */

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	public Optional<Coupon> findByIdNotAndCompanyIdAndTitleIgnoreCase(int couponId, int companyId, String title);
	public Optional<Coupon> findByIdAndCompanyId(int couponId, int companyId);
	public List<Coupon> findByCompanyId(int companyId);
	public List<Coupon> findByCompanyIdAndCategory(int companyId, Category couponCategory);
	public List<Coupon> findByCompanyIdAndPriceLessThanEqual(int companyId, double maxPrice);

	@Query("SELECT coupon FROM Customer customer JOIN customer.coupons coupon WHERE customer.id = ?1 AND coupon.id = ?2")
	public Optional<Coupon> findByCustomerIdAndCouponId(int customerId, int couponId);
	@Query("SELECT coupon FROM Customer customer JOIN customer.coupons coupon WHERE customer.id = ?1")
	public Set<Coupon> findByCustomerId(int customerId);
	@Query("SELECT coupon FROM Customer customer JOIN customer.coupons coupon WHERE customer.id = ?1 AND coupon.category = ?2")
	public Set<Coupon> findByCustomerIdAndCouponCategory(int customerId, Category couponCategory);
	@Query("SELECT coupon FROM Customer customer JOIN customer.coupons coupon WHERE customer.id = ?1 AND coupon.price <= ?2")
	public Set<Coupon> findByCustomerIdAndCouponPrice(int customerId, double maxPrice);

}
