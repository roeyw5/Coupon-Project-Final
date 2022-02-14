package com.coupons.test;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coupons.dailyJob.DailyJob;
import com.coupons.exception.CustomException;
import com.coupons.model.Coupon;
import com.coupons.model.user.Admin;
import com.coupons.model.user.Company;
import com.coupons.model.user.Customer;
import com.coupons.repository.user.UserRepository;
import com.coupons.service.ifc.AdminServiceIfc;
import com.coupons.service.ifc.CompanyServiceIfc;
import com.coupons.service.ifc.CustomerServiceIfc;
import com.coupons.utility.enums.Category;

@Component
public class Test {

	@Autowired
	private AdminServiceIfc adminService;
	@Autowired
	private CompanyServiceIfc companyService;
	@Autowired
	private CustomerServiceIfc customerService;
	@Autowired
	UserRepository adminRepository;
	@Autowired
	DailyJob dailyJob;

	
	/* Testing the business logic methods.
	 * Does not effect the database, everything gets deleted at the end.
	 */
	public void testAll(boolean test) {
		if (test) {
			System.out.println("\nStarting the test...\n");
			try {
				/* Creating a database for the testing if it doesn't exist */
				try {
					adminService.getAllCustomers();
				} catch (CustomException e) {
					System.out.println("\nBuilding a temporary test database...\n");
					DatabaseCreator.createDatabase(adminService, companyService, customerService);
				}

				/* Admin Login */
				System.out.println("----------------");
				Admin admin = new Admin("test", "test");
				System.out.println("Admin Tests");
				System.out.println("----------------");
				/* AdminService methods */
				/* AdminService Company methods */
				adminService.addCompany(admin.getId(), new Company("TestCompany", "testedc@gmail.com", "1234"));
				List<Company> companies = adminService.getAllCompanies();
				companies.forEach(System.out::println);
				Company updatedCompany = companies.get(companies.size() - 1);
				updatedCompany.setName("TestCompanyUpdated Inc");
				updatedCompany.setEmail("testedc2@gmail.com");
				updatedCompany.setPassword("12345");
				adminService.updateCompany(admin.getId(), updatedCompany);
				System.out.println(adminService.getOneCompany(updatedCompany.getId()));

				/* AdminService Customer methods */
				System.out.println("");
				adminService.addCustomer(admin.getId(),
						new Customer("Test", "Customer", "tester@gmail.com", "1234"));
				List<Customer> customers = adminService.getAllCustomers();
				customers.forEach(System.out::println);
				Customer updatedCustomer = customers.get(customers.size() - 1);
				updatedCustomer.setFirstName("TestUpdated");
				updatedCustomer.setLastName("CustomerUpdated");
				updatedCustomer.setEmail("testo@gmail.com");
				updatedCustomer.setPassword("12345");
				adminService.updateCustomer(admin.getId(), updatedCustomer);
				System.out.println(adminService.getOneCustomer(updatedCustomer.getId()));
				

				/* CompanyService methods */
				System.out.println("----------------");
				System.out.println("Company Tests");
				System.out.println("----------------");
				companyService.addCoupon(updatedCompany.getId(), new Coupon(Category.BUSINESS, "TestCoupon", "Business is fun",
						new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 7)),
						new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)), 19, 19000.99, "PLACEHOLDER.jpg"));
				List<Coupon> coupons = companyService.getCompanyCoupons(updatedCompany.getId());
				coupons.forEach(System.out::println);
				Coupon updatedCoupon = coupons.get(coupons.size() - 1);
				updatedCoupon.setCategory(Category.RESTAURANT);
				updatedCoupon.setTitle("TestCouponUpdated");
				updatedCoupon.setDescription("Restaurant is more fun");
				updatedCoupon.setStartDate(new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 14)));
				updatedCoupon.setEndDate(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 14)));
				updatedCoupon.setPrice(5.00);
				updatedCoupon.setAmount(2);
				updatedCoupon.setImage("PLACEHOLDER.gif");
				companyService.updateCoupon(updatedCompany.getId(), updatedCoupon);
				System.out.println("Company coupons:");
				companyService.getCompanyCoupons(updatedCompany.getId()).forEach(System.out::println);
				System.out.println("Company coupons that cost less than 500:");
				companyService.getCompanyCoupons(updatedCompany.getId(), 500.0).forEach(System.out::println);
				System.out.println("Company coupons of category restaurant:");
				companyService.getCompanyCoupons(updatedCompany.getId(), Category.RESTAURANT)
						.forEach(System.out::println);
				System.out.println("Company details:");
				System.out.println(companyService.getCompanyDetails(updatedCompany.getId()));

				/* CustomerService methods */
				System.out.println("----------------");
				System.out.println("Customer Tests:");
				System.out.println("----------------");
				customerService.purchaseCoupon(updatedCustomer.getId(), updatedCoupon.getId());
				customerService.getCustomerCoupons(updatedCustomer.getId()).forEach(System.out::println);
				customerService.getCustomerCoupons(updatedCustomer.getId(), 8).forEach(System.out::println);
				customerService.getCustomerCoupons(updatedCustomer.getId(), Category.RESTAURANT)
						.forEach(System.out::println);
				System.out.println(customerService.getCustomerDetails(updatedCustomer.getId()));

				/* Deleting temporary testing DB entities */
				companyService.deleteCoupon(updatedCompany.getId(), updatedCoupon.getId());
				adminService.deleteCustomer(admin.getId(), updatedCustomer.getId());
				adminService.deleteCompany(admin.getId(), updatedCompany.getId());
				
				System.out.println("\nTesting completed successfully.");
			} catch (CustomException e) {
				System.out.println("\nTesting was unsuccessful.");
				System.err.println(e);
			} finally {
				System.out.println("Test ended.\n");
			}
		}
	}
}
