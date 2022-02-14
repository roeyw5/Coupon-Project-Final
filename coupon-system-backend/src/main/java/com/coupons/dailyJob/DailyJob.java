package com.coupons.dailyJob;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coupons.model.Coupon;
import com.coupons.repository.CouponRepository;
import com.coupons.utility.general.StringClass;
import com.coupons.utility.general.TerminationWaitTime;
import com.coupons.utility.general.TimeComparisonUtil;
import com.coupons.utility.general.TimeZoneUtil;
import com.coupons.utility.service.CouponDeleter;

/*
 * A daily clean up job that deletes expired coupons from all appropriate
 * tables. 
 */

@Component
public final class DailyJob implements Runnable, TimeComparisonUtil, CouponDeleter, InitializingBean {

	@Autowired
	private CouponRepository couponRepository;
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private boolean quit = false;

	/*
	 * Starts the thread. If it's not already running, the thread will start
	 * at midnight, every 24 hours.
	 */
	public boolean start() {
		if (!quit) {
			quit = true;
			ZonedDateTime now = ZonedDateTime.now(ZoneId.of(TimeZoneUtil.ISRAEL.toString()));
			ZonedDateTime nextRun = now.withHour(0).withMinute(0).withSecond(0);
			if (now.compareTo(nextRun) > 0)
				nextRun = nextRun.plusDays(1);
			Duration duration = Duration.between(now, nextRun);
			scheduler.scheduleAtFixedRate(this, duration.getSeconds(), TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
			return true;
		}
		return false;
	}

	/*
	 * Stops the daily cleanup thread
	 */
	public boolean stop() {
		if (quit) {
			try {
				scheduler.shutdown();
				if (!scheduler.awaitTermination(TerminationWaitTime.TIME.toInt(), TimeUnit.MINUTES)) {
					return false;
				}
				quit = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/*
	 * Calls the helper method that deletes expired coupons.
	 */
	@Override
	public void run() {
		deleteExpiredCoupons();
	}

	/*
	 * Helper method that deletes expired coupons. Removes coupons with end_dates
	 * before current day from all appropriate tables.
	 */
	private void deleteExpiredCoupons() {
		List<Coupon> coupons = couponRepository.findAll();
		coupons.forEach(coupon -> {
			synchronized (StringClass.COUPON_ID_SYNC + coupon.getId()) {
				Optional<Coupon> foundCoupon = couponRepository.findById(coupon.getId());
				if (foundCoupon.isPresent() && isPast(coupon.getEndDate())) {
					Coupon removedCoupon = foundCoupon.get();
					deleteCoupon(removedCoupon, couponRepository);
					System.err.println("Expired coupon \"" + coupon.getTitle() + "\" deleted");
				}
			}
		});
	}

	/*
	 * Starts the cleanup thread when server starts.
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		
		System.err.println("Starting the daily cleanup thread...");
		if (!start()) {
			System.err.println("Daily cleanup thread has failed to start.");
		}
		deleteExpiredCoupons();
	}

}
