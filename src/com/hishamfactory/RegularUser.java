package com.hishamfactory;


import java.io.Serializable;

public abstract class RegularUser extends  FullTimeEmployee implements Serializable {
    private static final long serialVersionUID = 1L;
    RegularUser(String first_name,String last_name,String user_name,int age,String tel_number,String address, String role,double regular_basic_salary,String regularUser_pin,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,role,regular_basic_salary,regularUser_pin,company);
        System.out.println(this.getRole()+" "+ this.first_name+","+this.last_name + " has created with ID " + this.getUuid());
        this.hasNewMessages("Welcome " + this.getFirst_name()+",to build things change the world.");
    }
    RegularUser(String uuid,String first_name,String last_name,String user_name,int age,String tel_number,String address,byte[] regularUser_pin, String role,double regular_basic_salary,Company company){
        super(uuid,first_name,last_name,user_name,age,tel_number,address,regularUser_pin,role,regular_basic_salary,company);
        System.out.println(this.getRole()+" "+ this.first_name+","+this.last_name + " has created with ID " + this.getUuid());
        this.hasNewMessages("Welcome " + this.getFirst_name()+",to build things change the world.");
    }
}
