package com.coupons.message.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/*
 * Throws custom exceptions, for unsuccessful responses.
 */

@Getter
@Setter
@AllArgsConstructor
/*
 * "Exception List:" "CustomExceptionList - LST-000.000"
 * 
 * "companyException:" "CompanyAlreadyExistsException - CMP-001.001"
 * "CompanyDuplicateValueException - CMP-001.002"
 * "CompanyIsEmptyException - CMP-001.003"
 * "CompanyIsNullException - CMP-001.004"
 * "CompanyNotFoundException - CMP-001.005"
 * "CompanyNullValueException - CMP-001.006"
 * 
 * "couponException:" "CouponAlreadyExistsException - CPN-002.001"
 * "CouponDuplicateValueException - CPN-002.002"
 * "CouponExpiredModifyException - CPN-002.003"
 * "CouponExpiredPurchaseException - CPN-002.004"
 * "CouponIsEmptyException - CPN-002.005" "CouponIsNullException - CPN-002.006"
 * "CouponModifyNoStockException - CPN-002.007"
 * "CouponNegativePriceException - CPN-002.008"
 * "CouponNotFoundException - CPN-002.009"
 * "CouponNullValueException - CPN-002.010" "CouponOwnedException - CPN-002.011"
 * "CouponPurchaseNoStockException - CPN-002.012"
 * 
 * "customerException:" "CustomerAlreadyExistsException - CST-003.001"
 * "CustomerDuplicateValueException- CST-003.002"
 * "CustomerIsEmptyException - CST-003.003"
 * "CustomerIsNullException - CST-003.004"
 * "CustomerNotFoundException - CST-003.005"
 * "CustomerNullValueException - CST-003.006"
 * 
 * "loginException:" "InvalidLoginException - LGN-005.001"
 * "NullLoginException - LGN-005.002" "MismatchLoginException - LGN-005.003"
 * 
 * "UserEmailAlreadyExists - USR-006.001"
 */
public class ExceptionResponse {

	private String response;
	private String errorCode;

}