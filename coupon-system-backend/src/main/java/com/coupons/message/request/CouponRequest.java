package com.coupons.message.request;

import java.sql.Date;

import com.coupons.model.Coupon;
import com.coupons.utility.enums.Category;

import lombok.Getter;
import lombok.Setter;

/*
 * Coupon cast used for Controller requests
 */

@Getter
@Setter
public class CouponRequest {

	private int id;
	private Category category;
	private Date startDate;
	private Date endDate;
	private String title;
	private String description;
	private int amount;
	private double price;
	private String image;

	
	public Coupon toCoupon() {
		Coupon coupon = new Coupon(category, title, description, startDate, endDate, amount, price, image);
		coupon.setId(id);
		return coupon;
	}
}
