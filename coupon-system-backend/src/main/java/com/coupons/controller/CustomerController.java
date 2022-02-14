package com.coupons.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coupons.exception.CustomException;
import com.coupons.message.response.ExceptionResponse;
import com.coupons.model.Coupon;
import com.coupons.model.user.Customer;
import com.coupons.service.ifc.CustomerServiceIfc;
import com.coupons.tokensManager.TokenUtil;
import com.coupons.utility.enums.Category;

/**
 * Controller for the customer CRUD actions
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController implements TokenUtil{

	@Autowired
	private CustomerServiceIfc customerService;

	@PutMapping(value = "/purchase-coupon", produces = "application/json")
	public ResponseEntity<?> purchaseCoupon(@RequestParam int couponId) {
		try {
			return new ResponseEntity<Coupon>(customerService.purchaseCoupon(getUserId(), couponId), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get-coupons", produces = "application/json")
	public ResponseEntity<?> getCustomerCoupons() {
		try {
			return new ResponseEntity<Set<Coupon>>(customerService.getCustomerCoupons(getUserId()), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get-coupons-max-price", produces = "application/json")
	public ResponseEntity<?> getCustomerCoupons(@RequestParam Double maxPrice) {
		try {
			return new ResponseEntity<Set<Coupon>>(customerService.getCustomerCoupons(getUserId(), maxPrice),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get-coupons-category", produces = "application/json")
	public ResponseEntity<?> getCustomerCoupons(@RequestParam Category category) {
		try {
			return new ResponseEntity<Set<Coupon>>(customerService.getCustomerCoupons(getUserId(), category),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get-details", produces = "application/json")
	public ResponseEntity<?> getCustomerDetails() {
		return new ResponseEntity<Customer>(customerService.getCustomerDetails(getUserId()), HttpStatus.OK);
	}

}
