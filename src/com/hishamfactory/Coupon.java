package com.hishamfactory;

import java.io.Serializable;

public class Coupon implements Serializable {
    private static final long serialVersionUID = 1L;
    private String coupon_uuid;
    private String coupon_code;
    private int coupon_in_percentage;

    public Coupon(String coupon_code,int coupon_in_percentage,Company company){
        this.coupon_uuid = company.getNewUUID();
        this.coupon_code = coupon_code;
        this.coupon_in_percentage = coupon_in_percentage;
        System.out.println("Coupon id: "+ this.coupon_uuid + ",has using code: " + this.getCoupon_code() + " created in percentage " + this.getCoupon_in_percentage()+"%");
    }
    public void setCoupon_code(String coupon_code){
        this.coupon_code = coupon_code;
    }
    public String getCoupon_code(){
        return coupon_code;
    }
    public void setCoupon_in_percentage(int coupon_in_percentage){
        this.coupon_in_percentage = coupon_in_percentage;
    }
    public int getCoupon_in_percentage(){
        return coupon_in_percentage;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "coupon_id='" + coupon_uuid + '\'' +
                ", coupon_code='" + coupon_code + '\'' +
                ", coupon_in_percentage=" + coupon_in_percentage +
                '}';
    }
}
