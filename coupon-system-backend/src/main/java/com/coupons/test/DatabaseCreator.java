package com.coupons.test;

import java.sql.Date;
import java.util.List;

import com.coupons.exception.CustomExceptionList;
import com.coupons.exception.UserEmailAlreadyExists;
import com.coupons.exception.companyException.CompanyDuplicateValueException;
import com.coupons.exception.companyException.CompanyIsNullException;
import com.coupons.exception.companyException.CompanyNotFoundException;
import com.coupons.exception.companyException.CompanyNullValueException;
import com.coupons.exception.couponException.CouponDuplicateValueException;
import com.coupons.exception.couponException.CouponIsEmptyException;
import com.coupons.exception.couponException.CouponIsNullException;
import com.coupons.exception.couponException.CouponNullValueException;
import com.coupons.exception.customerException.CustomerDuplicateValueException;
import com.coupons.exception.customerException.CustomerIsEmptyException;
import com.coupons.exception.customerException.CustomerIsNullException;
import com.coupons.exception.customerException.CustomerNotFoundException;
import com.coupons.exception.customerException.CustomerNullValueException;
import com.coupons.exception.generalException.AlreadyExistsException;
import com.coupons.model.Coupon;
import com.coupons.model.user.Company;
import com.coupons.model.user.Customer;
import com.coupons.service.ifc.AdminServiceIfc;
import com.coupons.service.ifc.CompanyServiceIfc;
import com.coupons.service.ifc.CustomerServiceIfc;
import com.coupons.utility.enums.Category;

public class DatabaseCreator {

	public static void createDatabase(AdminServiceIfc adminService, CompanyServiceIfc companyService,
			CustomerServiceIfc customerService) throws CompanyNotFoundException, UserEmailAlreadyExists, CustomerNotFoundException {
		try {
			/* Create companies */
			Company[] companies = new Company[10];
			companies[0] = new Company("SpaceX", "spaceX@gmail.com", "1234");
			companies[1] = new Company("Virgin Galactic", "virgin@gmail.com", "1234");
			companies[2] = new Company("Blue Origin", "blue@gmail.com", "1234");
			companies[3] = new Company("Boeing", "boeing@gmail.com", "1234");
			companies[4] = new Company("Axiom Space", "axiom@gmail.com", "1234");
			companies[5] = new Company("Space Perspective", "space@gmail.com", "1234");
			companies[6] = new Company("Rami Levi", "ramilevi@gmail.com", "1234");
			companies[7] = new Company("Daka Ha-90", "daka@gmail.com", "1234");
			companies[8] = new Company("Apple", "apple@gmail.com", "1234");
			companies[9] = new Company("Google", "google@gmail.com", "1234");

			/* Add companies */
			for (int i = 0; i < companies.length; i++) {
				adminService.addCompany(0, companies[i]);
			}

			/* Create coupons */
			Coupon[] coupons = new Coupon[12];
			coupons[0] = new Coupon(Category.BUSINESS, "3 Seasons Business Hotel", "A quick visit to the moon and back | 1 Night",
					Date.valueOf("2020-01-01"), Date.valueOf("2024-01-01"), 120, 120500.0, "https://www.traveldailymedia.com/assets/2018/10/first-hotel-on-the-moon-lunar-hilton-1024x576.jpg");
			coupons[0].setCompany(companies[0]);
			coupons[1] = new Coupon(Category.EXPLORING, "Flight Around Jupiter", "Reach the beyound! Visit Jupiter's orbit | 42 Nights", Date.valueOf("2020-01-01"),
					Date.valueOf("2023-01-01"), 5, 395000.0, "https://images.immediate.co.uk/production/volatile/sites/25/2019/08/Jupiter-Juno-aa01b7c.jpg");
			coupons[1].setCompany(companies[0]);
			coupons[2] = new Coupon(Category.VACATION, "Holden Crater Resort", "Visit Mars' smallest crater! | 11 Nights",
					Date.valueOf("2020-02-08"), Date.valueOf("2023-02-21"), 0, 300000.0, "https://res.cloudinary.com/jerrick/image/upload/v1509387351/njtoso1adtbtzvjyurfx.jpg");
			coupons[2].setCompany(companies[1]);
			coupons[3] = new Coupon(Category.SPORT, "Luna FC VS Saturn UTD", "Most watched football game in the world(S)!", Date.valueOf("2020-01-03"),
					Date.valueOf("2023-01-03"), 20, 32000.0, "https://www.scribalmultiverse.com/wp-content/uploads/2014/06/Picture1.png");
			coupons[3].setCompany(companies[1]);
			coupons[4] = new Coupon(Category.PARTY, "Tomorrowland: Luna", "Featuring DJ's Bennet&Lapid ", Date.valueOf("2020-10-11"),
					Date.valueOf("2023-10-11"), 2, 49000.0, "https://upload.wikimedia.org/wikipedia/commons/8/82/Tomorrowland-2017-2.jpg");
			coupons[4].setCompany(companies[2]);
			coupons[5] = new Coupon(Category.EXTREME, "Neptune Ski trip", "Coldest place in the system! | 241 Nights",
					Date.valueOf("2020-01-01"), Date.valueOf("2023-02-10"), 4, 250000.0, "https://i0.heartyhosting.com/www.snowboarder.com/wp-content/uploads/2018/02/HELMET_2018Winter_Olympics_Clavin43.jpg");
			coupons[5].setCompany(companies[2]);
			coupons[6] = new Coupon(Category.BUSINESS, "MWC 2043", "Solar System's most influential connectivity event ",
					Date.valueOf("2021-01-12"), Date.valueOf("2044-01-12"), 58, 60555.0, "https://i0.wp.com/www.mobileworldlive.com/wp-content/uploads/2021/09/MWC-Barcelona-Logo-RGB_colour-undated.jpg");
			coupons[6].setCompany(companies[3]);
			coupons[7] = new Coupon(Category.EXPLORING, "Into the Unknown", "1-Way ticket into the vastness of space",
					Date.valueOf("2020-01-01"), Date.valueOf("2027-12-01"), 4, 20000.0, "https://cdn.akamai.steamstatic.com/steam/apps/1583190/capsule_616x353.jpg");
			coupons[7].setCompany(companies[3]);
			coupons[8] = new Coupon(Category.RESTAURANT, "Golda Venus City 1KG", "Golda Ice Cream is expanding into space!",
					Date.valueOf("2020-02-05"), Date.valueOf("2023-02-05"), 18, 4900.99, "https://www.secrettelaviv.com/wp-content/uploads/2019/05/53209758_623323678095218_947851036859564032_n.jpg");
			coupons[8].setCompany(companies[4]);
			coupons[9] = new Coupon(Category.ATTRACTION, "Mars Sea Life", "Come visit before we kill more species!",
					Date.valueOf("2020-12-01"), Date.valueOf("2024-12-01"), 54, 12490.0, "https://www.thai.lt/images/thailand/bangkok/sealife/sea-life-bangkok_07.jpg");
			coupons[9].setCompany(companies[4]);
			coupons[10] = new Coupon(Category.SPORT, "Basketball Worlds Cup Final", "Earth VS Jupiter, here we go again! ", Date.valueOf("2020-01-01"),
					Date.valueOf("2023-05-20"), 1320, 4500.00, "https://thumbs.dreamstime.com/b/basketball-game-concept-basketball-ball-floating-dark-starry-space-101250123.jpg");
			coupons[10].setCompany(companies[5]);
			coupons[11] = new Coupon(Category.PARTY, "Adelle 2.0 - Last Concert", "Holo-concert of the great AI singer", Date.valueOf("2020-02-20"),
					Date.valueOf("2030-02-20"), 30, 1000000.0, "https://soundtrackfest.com/wp-content/uploads/2019/11/MN-2019-11-14-Concierto-%E2%80%98Space-Spectacular%E2%80%99-con-la-Manchester-Concert-Orchestra-y-Anthony-Inglis.jpg");
			coupons[11].setCompany(companies[5]);
		

			/* Add coupons */
			for (int i = 0; i < coupons.length; i++) {
				companyService.addCoupon(coupons[i].getCompany().getId(), coupons[i]);
			}

			/* Create customers */
			Customer[] customers = new Customer[10];
			customers[0] = new Customer("Rami", "Younes", "rami@gmail.com", "1234");
			customers[1] = new Customer("James", "Holden", "holden@gmail.com", "1234");
			customers[2] = new Customer("Amos", "Burton", "amos@gmail.com", "1234");
			customers[3] = new Customer("Naomi", "Nagata", "naomi@gmail.com", "1234");
			customers[4] = new Customer("Alex", "Kamal", "alex@gmail.com", "1234");
			customers[5] = new Customer("Bobbi", "Draper", "draper@gmail.com", "1234");
			customers[6] = new Customer("Chrisjen", "Avasarala", "badass@gmail.com", "1234");
			customers[7] = new Customer("Camina", "Drummer", "drummer@gmail.com", "1234");
			customers[8] = new Customer("Marco", "Inaros", "belter@gmail.com", "1234");
			customers[9] = new Customer("Klaes", "Ashford", "ash@gmail.com", "1234");
			

			/* Add customers */
			for (int i = 0; i < customers.length; i++) {
				adminService.addCustomer(0, customers[i]);
			}

			/* Update the added coupons and customers id */
			List<Coupon> couponList = adminService.getCoupons();
			for (int i = 0; i < coupons.length; i++) {
				coupons[i] = couponList.get(i);
			}
			List<Customer> customerList = adminService.getAllCustomers();
			for (int i = 0; i < customers.length; i++) {
				customers[i] = customerList.get(i);
			}

			
		} catch (AlreadyExistsException | CustomerDuplicateValueException | CustomerIsNullException
				| CustomerNullValueException | CustomerIsEmptyException
				| CouponIsEmptyException | CouponNullValueException | CouponIsNullException
				| CouponDuplicateValueException | CompanyNullValueException | CompanyIsNullException
				| CompanyDuplicateValueException | CustomExceptionList e) {
			e.printStackTrace();
		}
	}

}
