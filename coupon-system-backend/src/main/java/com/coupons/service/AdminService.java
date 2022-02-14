package com.coupons.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.coupons.repository.CouponRepository;
import com.coupons.repository.user.AdminRepository;
import com.coupons.repository.user.CompanyRepository;
import com.coupons.repository.user.CustomerRepository;
import com.coupons.repository.user.UserRepository;
import com.coupons.service.ifc.AdminServiceIfc;
import com.coupons.utility.exception.ExceptionUtil;
import com.coupons.utility.general.StringClass;
import com.coupons.utility.service.AdminDetails;
import com.coupons.utility.service.validation.CompanyValidation;
import com.coupons.utility.service.validation.CustomerValidation;

/*
 * Handles the business logic of the system.
 */

@Service
public class AdminService implements AdminServiceIfc, CompanyValidation, CustomerValidation,
	 InitializingBean {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private PasswordEncoder encoder;

	
	/**
	 * Log-in as an Admin into the system. Checks if the email and password in the
	 * argument match an Admin table in the database.
	 */
	@Override
	public Optional<Admin> login(String email, String password) {
		Optional<Admin> optionAdmin = adminRepository.findByEmailIgnoreCaseAndPassword(email, password);
		if (!optionAdmin.isPresent())
			return optionAdmin;
		return optionAdmin;
	}

	/**
	 * Adds a new Company to the database, unless the Name or Email parameters
	 * already exist in the database.
	 */
	@Override
	public Company addCompany(int id, Company createdCompany)
			throws CompanyNullValueException, CompanyIsNullException, CompanyDuplicateValueException,
			CompanyAlreadyExistsException, UserEmailAlreadyExists {
		checkCompany(createdCompany);
		if (companyRepository.existsById(createdCompany.getId())) {
			throw new CompanyAlreadyExistsException(createdCompany.getId());
		}
		createdCompany.setPassword(encoder.encode(createdCompany.getPassword()));
		createdCompany = companyRepository.save(createdCompany);
		return createdCompany;
	}

	/**
	 * Updates a Company's parameters (ID and Title/Name are immutable).
	 */
	@Override
	public Company updateCompany(int id, Company updatedCompany) throws CompanyNullValueException,
			CompanyIsNullException, CompanyDuplicateValueException, CompanyNotFoundException, UserEmailAlreadyExists {
		checkCompany(updatedCompany);
		Optional<Company> optionCompany = companyRepository.findById(updatedCompany.getId());
		if (!optionCompany.isPresent()) {
			throw new CompanyNotFoundException(updatedCompany.getId());
		}
		Company oldCompany = optionCompany.get();
		if (!updatedCompany.getPassword().equals(oldCompany.getPassword()))
			updatedCompany.setPassword(encoder.encode(updatedCompany.getPassword()));
		updatedCompany.setCoupons(oldCompany.getCoupons());
		updatedCompany = companyRepository.save(updatedCompany);
		return updatedCompany;
	}

	/**
	 * Deletes a Company from the database. 
	 * Note: Also deletes the Company's Coupons and their purchase history.
	 * Uses a helper method for deletion.
	 */
	@Override
	public void deleteCompany(int adminId, int companyId) throws CompanyNotFoundException {
		Optional<Company> optionCompany = companyRepository.findById(companyId);
		if (!optionCompany.isPresent()) {
			throw new CompanyNotFoundException(companyId);
		}
		companyDeleter(companyId);
	}

	/**
	 * Fetches all Companies from the database.
	 */
	@Override
	public List<Company> getAllCompanies() throws CompanyIsEmptyException {
		List<Company> companies = companyRepository.findAll();
		if (companies.isEmpty())
			throw new CompanyIsEmptyException();
		return companies;
	}
	
	/**
	 * Fetches one Company from the database (based on ID).
	 */
	@Override
	public Company getOneCompany(int companyId) throws CompanyNotFoundException {
		Optional<Company> companyOption = companyRepository.findById(companyId);
		if (companyOption.isPresent())
			return companyOption.get();
		throw new CompanyNotFoundException(companyId);
	}
	
	
	/**
	 * Adds a Customer to the database 
	 * (Cannot share an Email with existing Customer).
	 */
	@Override
	public Customer addCustomer(int id, Customer createdCustomer)
			throws CustomerDuplicateValueException, CustomerIsNullException, CustomerNullValueException,
			CustomerAlreadyExistsException, UserEmailAlreadyExists {
		checkCustomer(createdCustomer);
		if (customerRepository.existsById(createdCustomer.getId())) {
			throw new CustomerAlreadyExistsException(createdCustomer.getId());
		}
		createdCustomer.setPassword(encoder.encode(createdCustomer.getPassword()));
		createdCustomer = customerRepository.save(createdCustomer);
		return createdCustomer;
	}
	
	/**
	 * Updates an existing Customer's data (based on immutable existing ID).
	 */
	@Override
	public Customer updateCustomer(int id, Customer updatedCustomer) throws CustomerDuplicateValueException,
			CustomerIsNullException, CustomerNullValueException, CustomerNotFoundException, UserEmailAlreadyExists {
		checkCustomer(updatedCustomer);
		Optional<Customer> optionCustomer = customerRepository.findById(updatedCustomer.getId());
		if (!optionCustomer.isPresent())
			throw new CustomerNotFoundException(updatedCustomer.getId());
		Customer oldCustomer = optionCustomer.get();
		if (!updatedCustomer.getPassword().equals(oldCustomer.getPassword()))
			updatedCustomer.setPassword(encoder.encode(updatedCustomer.getPassword()));
		updatedCustomer.setCoupons(oldCustomer.getCoupons());
		updatedCustomer = customerRepository.save(updatedCustomer);
		return updatedCustomer;
	}

	/**
	 * Deletes a Customer from the database (based on existing ID). 
	 * Note: Also deletes the Customer's purchase history.
	 */
	@Override
	public void deleteCustomer(int adminId, int customerId) throws CustomerNotFoundException {
		Optional<Customer> optionCustomer = customerRepository.findById(customerId);
		if (!optionCustomer.isPresent())
			throw new CustomerNotFoundException(customerId);
		Customer removedCustomer = optionCustomer.get();
		restoreCoupons(removedCustomer);
		removedCustomer.setCoupons(null);
		customerRepository.save(removedCustomer);
		customerRepository.delete(removedCustomer);
	}
	
	/**
	 * Fetches all Customers' data from the database.
	 */
	@Override
	public List<Customer> getAllCustomers() throws CustomerIsEmptyException {
		List<Customer> customers = customerRepository.findAll();
		if (customers.isEmpty())
			throw new CustomerIsEmptyException();
		return customers;
	}
	
	/**
	 * Fetches a Customer's data from the database (based on ID).
	 */
	@Override
	public Customer getOneCustomer(int customerId) throws CustomerNotFoundException {
		Optional<Customer> customerOption = customerRepository.findById(customerId);
		if (customerOption.isPresent())
			return customerOption.get();
		throw new CustomerNotFoundException(customerId);
	}


	/**
	 * Helper method Get all coupons
	 */
	@Override
	public List<Coupon> getCoupons() throws CouponIsEmptyException {
		List<Coupon> coupons = couponRepository.findAll();
		if (coupons.isEmpty())
			throw new CouponIsEmptyException();
		return coupons;
	}

	
	/**
	 * Helper method, checks if a Company with the same
	 * name or email already exists in the system.
	 */
	private void checkCompany(Company company) throws CompanyNullValueException, CompanyIsNullException,
			CompanyDuplicateValueException, UserEmailAlreadyExists {
		companyNullValidation(company);
		if (companyRepository.findByIdNotAndNameIgnoreCase(company.getId(), company.getName()).isPresent()) {
			CompanyDuplicateValueException exception = new CompanyDuplicateValueException();
			exception.addDuplicate(ExceptionUtil.NAME, company.getName());
			throw exception;
		}
		if (userRepository.findByIdNotAndEmailIgnoreCase(company.getId(), company.getEmail()).isPresent())
			throw new UserEmailAlreadyExists(company.getEmail());
	}

	/**
	 * Helper method, for deleting a Company.
	 * Uses the deleteCompanySynchronizer method, to ensure synchronization.
	 */
	private void companyDeleter(int companyId) {
		List<Coupon> coupons = couponRepository.findByCompanyId(companyId);
		if (coupons.isEmpty())
			companyRepository.deleteById(companyId);
		else
			companyDeleterSynchronizer(coupons, coupons.size(), companyId);
	};

	/**
	 * Helper method, ensures synchronization of the
	 *  coupons when deleting a Company.
	 */
	private void companyDeleterSynchronizer(List<Coupon> coupons, int size, int companyId) {
		synchronized (StringClass.COUPON_ID_SYNC + coupons.get(size - 1).getId()) {
			if (--size != 0) {
				companyDeleterSynchronizer(coupons, size, companyId);
				return;
			}
			couponRepository.findByCompanyId(companyId).forEach(couponStream -> {
				couponStream.setCustomers(null);
				couponRepository.save(couponStream);
			});
			companyRepository.deleteById(companyId);
		}
	}

	/**
	 * Helper method, checks if a Customer with the same
	 * email already exists in the system.
	 */
	private void checkCustomer(Customer customer) throws CustomerIsNullException, CustomerNullValueException,
			CustomerDuplicateValueException, UserEmailAlreadyExists {
		customerNullValidation(customer);
		if (userRepository.findByIdNotAndEmailIgnoreCase(customer.getId(), customer.getEmail()).isPresent()) {
			throw new UserEmailAlreadyExists(customer.getEmail());
		}
	};

	/**
	 * Helper method, restores a coupon into the DB when a customer is deleted.
	 */
	private void restoreCoupons(Customer removedCustomer) {
		for (Coupon coupon : removedCustomer.getCoupons()) {
			synchronized (StringClass.COUPON_ID_SYNC + coupon.getId()) {
				coupon.setAmount(coupon.getAmount() + 1);
				couponRepository.save(coupon);
			}
		}
	}

	/**
	 * Ensures that there is an Admin user in the DB on server startup.
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (adminRepository.findAll().isEmpty()) {
			if (!adminRepository.findByEmailIgnoreCase(AdminDetails.ADMIN_USER.toString()).isPresent())
				adminRepository.save(
						new Admin(AdminDetails.ADMIN_USER.toString(), encoder.encode(AdminDetails.ADMIN_PASS.toString())));
		}
	}

}
