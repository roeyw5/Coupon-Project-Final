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
import com.coupons.message.request.user.CompanyRequest;
import com.coupons.message.request.user.CustomerRequest;
import com.coupons.message.response.ExceptionResponse;
import com.coupons.message.response.SuccessResponse;
import com.coupons.model.Coupon;
import com.coupons.model.user.Company;
import com.coupons.model.user.Customer;
import com.coupons.service.ifc.AdminServiceIfc;
import com.coupons.tokensManager.TokenUtil;

/**
 * Controller for the admin CRUD actions
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController implements TokenUtil {

	@Autowired
	private AdminServiceIfc adminService;

	@PostMapping(value = "/add-company", produces = "application/json")
	public ResponseEntity<?> addCompany(@RequestBody CompanyRequest companyReq) {
		try {
			return new ResponseEntity<Company>(adminService.addCompany(getUserId(), companyReq.toCompany()),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/update-company", produces = "application/json")
	public ResponseEntity<?> updateCompany(@RequestBody CompanyRequest companyReq) {
		try {
			return new ResponseEntity<Company>(adminService.updateCompany(getUserId(), companyReq.toCompany()),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value = "/delete-company", produces = "application/json")
	public ResponseEntity<?> deleteCompany(@RequestParam Integer companyId) {
		try {
			adminService.deleteCompany(getUserId(), companyId);
			return new ResponseEntity<SuccessResponse>(new SuccessResponse("Coupon deleted succesfully"),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/get-companies", produces = "application/json")
	public ResponseEntity<?> getAllCompanies() {
		try {
			return new ResponseEntity<List<Company>>(adminService.getAllCompanies(), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get-company", produces = "application/json")
	public ResponseEntity<?> getOneCompany(@RequestParam Integer id) {
		try {
			return new ResponseEntity<Company>(adminService.getOneCompany(id), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}


	@PostMapping(value = "/add-customer", produces = "application/json")
	public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest customerReq) {
		try {
			return new ResponseEntity<Customer>(adminService.addCustomer(getUserId(), customerReq.toCustomer()),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/update-customer", produces = "application/json")
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerRequest customerReq) {
		try {
			return new ResponseEntity<Customer>(adminService.updateCustomer(getUserId(), customerReq.toCustomer()),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value = "/delete-customer", produces = "application/json")
	public ResponseEntity<?> deleteCustomer(@RequestParam Integer customerId) {
		try {
			adminService.deleteCustomer(getUserId(), customerId);
			return new ResponseEntity<SuccessResponse>(new SuccessResponse("Coupon deleted succesfully"),
					HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get-customers", produces = "application/json")
	public ResponseEntity<?> getAllCustomers() {
		try {
			return new ResponseEntity<List<Customer>>(adminService.getAllCustomers(), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/get-customer", produces = "application/json")
	public ResponseEntity<?> getOneCustomer(@RequestParam Integer id) {
		try {
			return new ResponseEntity<Customer>(adminService.getOneCustomer(id), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping(value = "/get-coupons", produces = "application/json")
	public ResponseEntity<?> getCoupons() {
		try {
			return new ResponseEntity<List<Coupon>>(adminService.getCoupons(), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.toString(), e.getErrorCode()),
					HttpStatus.BAD_REQUEST);
		}
	}

}
