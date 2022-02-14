package com.coupons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.coupons.test.Test;

/**
 *Main class.
 *Runs the testAll method, which tests all business logic actions in the system.
 *
 *Starts the DailyJob thread, which runs in the background and deletes expired coupons every midnight.
 *
 *@author Roey Wullman
 **/

@SpringBootApplication
public class CouponApplication {
	
	static boolean test=true;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(CouponApplication.class, args);
		
		context.getBean(Test.class).testAll(test);
	}

}
