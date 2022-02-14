package com.coupons.utility.service.validation;

import com.coupons.exception.CustomExceptionList;
import com.coupons.exception.couponException.CouponExpiredModifyException;
import com.coupons.exception.couponException.CouponIsNullException;
import com.coupons.exception.couponException.CouponModifyNoStockException;
import com.coupons.exception.couponException.CouponNegativePriceException;
import com.coupons.exception.couponException.CouponNullValueException;
import com.coupons.model.Coupon;
import com.coupons.utility.exception.ExceptionUtil;
import com.coupons.utility.general.TimeComparisonUtil;

/**
 * Containing methods that helps validate Coupon business logic.
 */
public interface CouponValidation extends TimeComparisonUtil, StringModifier {

	/**
	 *Checks if a coupon is not null, and doesn't have null values.
	 **/
	public default void couponNullValidation(Coupon coupon)
			throws CouponNullValueException, CustomExceptionList, CouponIsNullException {
		if (coupon == null)
			throw new CouponIsNullException();
		boolean titleException = coupon.getTitle() == null;
		if (!titleException) {
			coupon.setTitle(trim(coupon.getTitle()));
			titleException = coupon.getTitle().isEmpty();
		}
		boolean descException = coupon.getDescription() == null;
		if (!descException) {
			coupon.setDescription(trim(coupon.getDescription()));
			descException = coupon.getDescription().isEmpty();
		}
		boolean imageException = coupon.getImage() == null;
		if (!imageException) {
			coupon.setImage(removeSpace(coupon.getImage()));
			imageException = coupon.getImage().isEmpty();
		}
		if (coupon.getCategory() == null || titleException || descException || coupon.getStartDate() == null
				|| coupon.getEndDate() == null || imageException) {
			CouponNullValueException exception = new CouponNullValueException();
			if (coupon.getCategory() == null)
				exception.addNull(ExceptionUtil.CATEGORY);
			if (titleException)
				exception.addNull(ExceptionUtil.TITLE);
			if (descException)
				exception.addNull(ExceptionUtil.DESCRIPTION);
			if (coupon.getStartDate() == null)
				exception.addNull(ExceptionUtil.START_DATE);
			if (coupon.getEndDate() == null)
				exception.addNull(ExceptionUtil.END_DATE);
			if (imageException)
				exception.addNull(ExceptionUtil.IMAGE);
			throw exception;
		}
		if (coupon.getAmount() < 0 || coupon.getPrice() < 0 || isPast(coupon.getEndDate())
				|| coupon.getStartDate().after(coupon.getEndDate())) {
			CustomExceptionList exceptionList = new CustomExceptionList();
			if (coupon.getAmount() < 0)
				exceptionList.addException(new CouponModifyNoStockException(coupon.getId()));
			if (coupon.getPrice() < 0)
				exceptionList.addException(new CouponNegativePriceException(coupon.getPrice()));
			if (isPast(coupon.getEndDate()))
				exceptionList.addException(new CouponExpiredModifyException(coupon.getId(), coupon.getEndDate()));
			throw exceptionList;
		}
	}

}
