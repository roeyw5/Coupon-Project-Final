package com.coupons.service.ifc;

import java.util.List;
import java.util.Optional;

import com.coupons.exception.UserEmailAlreadyExists;
import com.coupons.exception.companyException.CompanyAlreadyExistsException;
import com.coupons.exception.companyException.CompanyDuplicateValueException;
import com.coupons.exception.companyException.CompanyIsEmptyException;
import com.coupons.exception.companyException.CompanyIsNullException;
import com.coupons.exception.companyException.CompanyNotFoundException;
import com.coupons.exception.companyException.CompanyNullValueException;
import com.coupons.exception.couponException.CouponIsEmptyException;
import com.coupons.exception.customerException.CustomerAlreadyExistsException;
import com.coupons.exception.customerException.CustomerDuplicateValueException;
import com.coupons.exception.customerException.CustomerIsEmptyException;
import com.coupons.exception.customerException.CustomerIsNullException;
import com.coupons.exception.customerException.CustomerNotFoundException;
import com.coupons.exception.customerException.CustomerNullValueException;
import com.coupons.model.Coupon;
import com.coupons.model.user.Admin;
import com.coupons.model.user.Company;
import com.coupons.model.user.Customer;

/*
 * Interface with the methods for AdminService,
 * to take care of the business logic in the system.
 */

public interface AdminServiceIfc {

	public Optional<Admin> login(String email, String password);

	public Company addCompany(int adminId, Company company) throws CompanyNullValueException, CompanyIsNullException,
			CompanyDuplicateValueException, CompanyAlreadyExistsException, UserEmailAlreadyExists;

	public Company updateCompany(int adminId, Company company) throws CompanyNullValueException, CompanyIsNullException,
			CompanyDuplicateValueException, CompanyNotFoundException, UserEmailAlreadyExists;

	public void deleteCompany(int adminId, int companyId) throws CompanyNotFoundException;

	public List<Company> getAllCompanies() throws CompanyIsEmptyException;

	public Company getOneCompany(int companyId) throws CompanyNotFoundException;

	public Customer addCustomer(int adminId, Customer customer)
			throws CustomerAlreadyExistsException, CustomerDuplicateValueException, CustomerIsNullException,
			CustomerNullValueException, UserEmailAlreadyExists;

	public Customer updateCustomer(int adminId, Customer customer) throws CustomerDuplicateValueException,
			CustomerIsNullException, CustomerNullValueException, CustomerNotFoundException, UserEmailAlreadyExists;

	public void deleteCustomer(int adminId, int customerId) throws CustomerNotFoundException;

	public List<Customer> getAllCustomers() throws CustomerIsEmptyException;

	public Customer getOneCustomer(int customerId) throws CustomerNotFoundException;

	public List<Coupon> getCoupons() throws CouponIsEmptyException;

}
