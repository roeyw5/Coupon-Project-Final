package com.coupons.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coupons.exception.CustomExceptionList;
import com.coupons.exception.couponException.CouponAlreadyExistsException;
import com.coupons.exception.couponException.CouponDuplicateValueException;
import com.coupons.exception.couponException.CouponIsEmptyException;
import com.coupons.exception.couponException.CouponIsNullException;
import com.coupons.exception.couponException.CouponNotFoundException;
import com.coupons.exception.couponException.CouponNullValueException;
import com.coupons.model.Coupon;
import com.coupons.model.user.Company;
import com.coupons.repository.CouponRepository;
import com.coupons.repository.user.CompanyRepository;
import com.coupons.service.ifc.CompanyServiceIfc;
import com.coupons.utility.enums.Category;
import com.coupons.utility.general.StringClass;
import com.coupons.utility.service.CouponDeleter;
import com.coupons.utility.service.validation.CouponValidation;

/*
 * Handles the business logic of the system.
 */

@Service
public class CompanyService
		implements CompanyServiceIfc, CouponValidation, CouponDeleter {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CouponRepository couponRepository;
	
	/*
	 * Login as a company into the system. Checks if the email and password in the
	 * argument is a match with a company in the Companies table
	 */
	@Override
	public boolean login(String email, String password) {
		Optional<Company> optionCompany = companyRepository.findByEmailIgnoreCaseAndPassword(email, password);
		if (!optionCompany.isPresent())
			return false;
		return true;
	}

	/**
	 * Adds a Coupon to the database. 
	 */
	@Override
	public Coupon addCoupon(int companyId, Coupon addedCoupon) throws CouponNullValueException, CouponIsNullException,
			CouponAlreadyExistsException, CouponDuplicateValueException, CustomExceptionList {
		couponNullValidation(addedCoupon);
		if (couponRepository.existsById(addedCoupon.getId()))
			throw new CouponAlreadyExistsException(addedCoupon.getId());
		checkCoupon(addedCoupon, companyId);
		addedCoupon.setCompany(getCompanyDetails(companyId));
		addedCoupon = couponRepository.save(addedCoupon);
		
		return addedCoupon;
	}

	/**
	 * Updates a Coupon's parameters (Coupon ID and Company ID are immutable).
	 */
	@Override
	public Coupon updateCoupon(int companyId, Coupon updatedCoupon) throws CouponNullValueException,
			CouponIsNullException, CouponDuplicateValueException, CustomExceptionList, CouponNotFoundException {
		couponNullValidation(updatedCoupon);
		checkCoupon(updatedCoupon, companyId);
		Coupon oldCoupon;
		Optional<Coupon> optionCoupon;
		synchronized (StringClass.COUPON_ID_SYNC + updatedCoupon.getId()) {
			optionCoupon = couponRepository.findByIdAndCompanyId(updatedCoupon.getId(), companyId);
			if (!optionCoupon.isPresent())
				throw new CouponNotFoundException(updatedCoupon.getId());
			oldCoupon = optionCoupon.get();
			updatedCoupon.setCompany(oldCoupon.getCompany());
			updatedCoupon = couponRepository.save(updatedCoupon);
		}
		
		return updatedCoupon;
	}
	
	/**
	 * Deletes a Company's Coupon from the database (including the deletion of its
	 * purchase history).
	 */
	@Override
	public void deleteCoupon(int companyId, int couponId) throws CouponNotFoundException {
		synchronized (StringClass.COUPON_ID_SYNC + couponId) {
			Optional<Coupon> couponOption = couponRepository.findByIdAndCompanyId(couponId, companyId);
			if (!couponOption.isPresent())
				throw new CouponNotFoundException(couponId);
			Coupon removedCoupon = couponOption.get();
		
			deleteCoupon(removedCoupon, couponRepository);
		}
	}

	/**
	 * Fetches a Company's entire Coupon catalog.
	 */
	@Override
	public List<Coupon> getCompanyCoupons(int companyId) throws CouponIsEmptyException {
		List<Coupon> coupons = couponRepository.findByCompanyId(companyId);
		if (coupons.isEmpty())
			throw new CouponIsEmptyException();
		return coupons;
	}

	/**
	 * Returns a list of all Company's Coupons that cost less than the inserted
	 * price.
	 */
	@Override
	public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws CouponNotFoundException {
		List<Coupon> coupons = couponRepository.findByCompanyIdAndPriceLessThanEqual(companyId, maxPrice);
		if (coupons.isEmpty())
			throw new CouponNotFoundException(maxPrice);
		return coupons;
	}

	/**
	 * Returns a list of the Company's Coupons based on the inserted category.
	 */
	@Override
	public List<Coupon> getCompanyCoupons(int companyId, Category category) throws CouponNotFoundException {
		List<Coupon> coupons = couponRepository.findByCompanyIdAndCategory(companyId, category);
		if (coupons.isEmpty())
			throw new CouponNotFoundException(category);
		return coupons;
	}

	/**
	 * Returns all parameter data of a Company.
	 */
	@Override
	public Company getCompanyDetails(int companyId) {
		return companyRepository.findById(companyId).get();
	}

	/**
	 * Helper method, checks if a Coupon with the same title
	 * already exists in the same Company.
	 */
	private void checkCoupon(Coupon coupon, int companyId) throws CouponDuplicateValueException {
		if (couponRepository.findByIdNotAndCompanyIdAndTitleIgnoreCase(coupon.getId(), companyId, coupon.getTitle())
				.isPresent())
			throw new CouponDuplicateValueException(coupon.getTitle());
	};

	/**
	 * Helper method check if a company exist by email
	 */
	@Override
	public boolean companyExists(String email) {
		return companyRepository.findByEmailIgnoreCase(email).isPresent();
	}
}
