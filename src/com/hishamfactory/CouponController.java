package com.hishamfactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CouponController {
    Scanner sc = new Scanner(System.in);
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
    public void showAllCoupons(){
        if(Company.coupons.isEmpty()){
            System.out.println("** Sorry.The Coupons list is empty **");
        }else{
            System.out.println("......................List of Coupons......................");
            for (Coupon coupon: Company.coupons) {
                System.out.println(coupon.toString());
            }
        }
    }
    public void deleteCoupon(Coupon coupon){
        Company.coupons.remove(coupon);
        System.out.println("The coupon has code " + coupon.getCoupon_code() + " was deleted..");
    }
    public boolean showCouponMenu(Company company, CouponController couponController){
        boolean flag = true;
        System.out.println(".....................Coupon Menu..........................");
        System.out.println("1.Add new coupon");
        System.out.println("2.Show all coupons");
        System.out.println("3.Get coupon by ID");
        System.out.println("4.Delete airport");
        System.out.println("5.Quit");
        System.out.print("Enter a choice: ");
        try {
            int option = sc.nextInt();
            sc.nextLine();
            String coupon_code = null;
            if(option == 3 || option == 4) {
                System.out.print("Enter coupon code: ");
                coupon_code = sc.next();
                sc.nextLine();
            }
            switch (option) {
                case 1:
                    company.addCoupon(company);
                    break;
                case 2:
                    showAllCoupons();
                    break;
                case 3:
                    Coupon coupon_1 = getCouponByCode(coupon_code);
                    if(coupon_1 != null) {
                        System.out.println(".........................................................");
                        System.out.println(coupon_1);
                    }else{
                        System.out.println("*** This coupon not exits ***");
                    }
                    break;
                case 4:
                    Coupon coupon_2 = getCouponByCode(coupon_code);
                    if(coupon_2 != null) {
                        deleteCoupon(coupon_2);
                    }else{
                        System.out.println("*** This coupon not exists ***");
                    }
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice");
            }
        }catch (InputMismatchException e) {
            System.out.println("*** Your input mismatch whats excepted, please enter valid input ***");
            sc.nextLine();
        }
        return flag;
    }
}
