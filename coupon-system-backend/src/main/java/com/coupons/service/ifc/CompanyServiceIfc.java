package com.coupons.service.ifc;

import java.util.List;

import com.coupons.exception.CustomExceptionList;
import com.coupons.exception.couponException.CouponAlreadyExistsException;
import com.coupons.exception.couponException.CouponDuplicateValueException;
import com.coupons.exception.couponException.CouponIsEmptyException;
import com.coupons.exception.couponException.CouponIsNullException;
import com.coupons.exception.couponException.CouponNotFoundException;
import com.coupons.exception.couponException.CouponNullValueException;
import com.coupons.model.Coupon;
import com.coupons.model.user.Company;
import com.coupons.utility.enums.Category;

/*
 * Interface with the methods for CompanyService,
 * to take care of the business logic in the system.
 */

public interface CompanyServiceIfc extends ClientLogin {

	boolean companyExists(String email);

	public Coupon addCoupon(int companyId, Coupon coupon) throws CouponNullValueException, CouponIsNullException,
			CouponAlreadyExistsException, CouponDuplicateValueException, CustomExceptionList;

	public Coupon updateCoupon(int companyId, Coupon coupon) throws CouponNullValueException, CouponIsNullException,
			CouponDuplicateValueException, CustomExceptionList, CouponNotFoundException;

	public void deleteCoupon(int companyId, int couponId) throws CouponNotFoundException;

	public List<Coupon> getCompanyCoupons(int companyId) throws CouponIsEmptyException;

	public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws CouponNotFoundException;

	public List<Coupon> getCompanyCoupons(int companyId, Category category) throws CouponNotFoundException;

	public Company getCompanyDetails(int companyId);

}
