package com.hishamfactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class RootUser extends  FullTimeEmployee {

    RootUser(String first_name , String last_name, int age, String tel_number, String address, String role,double basic_salary,String user_pin, Company company){
        super(first_name,last_name,age,tel_number,address,role,basic_salary,user_pin,company);
        System.out.println(this.getRole()+" "+ this.first_name+","+this.last_name + " has created with ID " + this.getUuid());
    }
}
