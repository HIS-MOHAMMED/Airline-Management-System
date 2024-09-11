package com.hishamfactory;

import java.io.Serializable;
import java.util.ArrayList;

public class Coupon implements Serializable {
    private static final long serialVersionUID = 1L;
    private String coupon_id;
    private String coupon_code;
    private int coupon_in_percentage;

    public Coupon(String coupon_code,int coupon_in_percentage,Company company){
        this.coupon_id = company.getNewUUID();
        this.coupon_code = coupon_code;
        this.coupon_in_percentage = coupon_in_percentage;
        System.out.println("Coupon id: "+ this.coupon_id + ",has using code: " + this.getCoupon_code() + " created in percentage " + this.getCoupon_in_percentage()+"%");
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
}
