package com.hishamfactory;

public abstract class NormalUser extends  User{
    NormalUser(String first_name,String last_name,int age,String tel_number,String address,String normalUser_pin,Company company){
        super(first_name,last_name,age,tel_number,address,normalUser_pin,company);
    }
}
