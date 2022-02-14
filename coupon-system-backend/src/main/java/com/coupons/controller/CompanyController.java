package com.coupons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coupons.exception.CustomException;
import com.coupons.message.request.CouponRequest;
import com.coupons.message.response.ExceptionResponse;
import com.coupons.message.response.SuccessResponse;
import com.coupons.model.Coupon;
import com.coupons.model.user.Company;
import com.coupons.service.ifc.CompanyServiceIfc;
import com.coupons.tokensManager.TokenUtil;
import com.coupons.utility.enums.Category;

/**
 * Controller for the company CRUD actions
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/company")
public class CompanyController implements TokenUtil {
	
	@Autowired
	private CompanyServiceIfc companyService;

	@PostMapping(value = "/add-coupon", produces = "application/json")
	public ResponseEntity<?> addCoupon(@RequestBody CouponRequest couponReq) {
		try {
			return new ResponseEntity<Coupon>(companyService.addCoupon(getUserId(), couponReq.toCoupon()), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/update-coupon", produces = "application/json")
	public ResponseEntity<?> updateCoupon(@RequestBody CouponRequest couponReq) {
		try {
			return new ResponseEntity<Coupon>(companyService.updateCoupon(getUserId(), couponReq.toCoupon()),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/delete-coupon", produces = "application/json")
	public ResponseEntity<?> deleteCoupon(@RequestParam int couponId) {
		try {
			companyService.deleteCoupon(getUserId(), couponId);
			return new ResponseEntity<SuccessResponse>(new SuccessResponse("Coupon deleted succesfully"),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get-coupons", produces = "application/json")
	public ResponseEntity<?> getCompanyCoupons() {
		try {
			return new ResponseEntity<List<Coupon>>(companyService.getCompanyCoupons(getUserId()), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get-coupons-category", produces = "application/json")
	public ResponseEntity<?> getCompanyCoupons(@RequestParam Category category) {
		try {
			return new ResponseEntity<List<Coupon>>(companyService.getCompanyCoupons(getUserId(), category),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/get-coupons-max-price", produces = "application/json")
	public ResponseEntity<?> getCompanyCoupons(@RequestParam Double maxPrice) {
		try {
			return new ResponseEntity<List<Coupon>>(companyService.getCompanyCoupons(getUserId(), maxPrice),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping(value = "/get-details", produces = "application/json")
	public ResponseEntity<?> getCompanyDetails() {
		return new ResponseEntity<Company>(companyService.getCompanyDetails(getUserId()), HttpStatus.OK);
	}

}
