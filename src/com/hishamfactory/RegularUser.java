package com.hishamfactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class RegularUser extends  FullTimeEmployee {
    RegularUser(String first_name,String last_name,String user_name,int age,String tel_number,String address, String role,double regular_basic_salary,String regularUser_pin,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,role,regular_basic_salary,regularUser_pin,company);
        System.out.println(this.getRole()+" "+ this.first_name+","+this.last_name + " has created with ID " + this.getUuid());
        this.hasNewMessages("Welcome " + this.getFirst_name()+",to build things change the world.");
    }
}
