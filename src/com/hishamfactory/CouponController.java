package com.hishamfactory;

public class CouponController {

    public Coupon getCouponByCode(String coupon_code){
        for (Coupon coupon : Company.coupons) {
            if(coupon_code.equals(coupon.getCoupon_code())) return coupon;
        }
        return null;
    }
    public double discountCalculation(double flight_price,int coupon_in_percentage){
        double discount = flight_price /  ((double) 100/coupon_in_percentage);
        return flight_price-discount;
    }
}
