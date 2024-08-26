package com.hishamfactory;

public abstract class NormalUser extends  User{
    NormalUser(String first_name,String last_name,String user_name,int age,String tel_number,String address,String normalUser_pin,String role,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,normalUser_pin,role,company);
        this.hasNewMessages("Welcome "+this.getFirst_name()+",thank you to choose us.Be free to ask help.");
    }
    NormalUser(String uuid,String first_name,String last_name,String user_name,int age,String tel_number,String address,byte[] normalUser_pin,String role,Company company){
        super(uuid,first_name,last_name,user_name,age,tel_number,address,normalUser_pin,role,company);
        this.hasNewMessages("Welcome "+this.getFirst_name()+",thank you to choose us.Be free to ask help.");
    }
}
