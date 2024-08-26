package com.hishamfactory;

public class Pilot extends RegularUser{
    Pilot(String first_name,String last_name,String user_name,int age,String tel_number,String address,String role,double pilot_basic_salary,String pilot_pin,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,role,pilot_basic_salary,pilot_pin,company);
        Company.users.add(this);
        Company.employees.add(this);
        Company.pilots.add(this);
    }
    Pilot(String first_name,String last_name,String user_name,int age,String tel_number,String address,byte[] pilot_pin,String role,double pilot_basic_salary,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,pilot_pin,role,pilot_basic_salary,company);
    }
}
