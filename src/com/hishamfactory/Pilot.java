package com.hishamfactory;

public class Pilot extends RegularUser{
    Pilot(String first_name,String last_name,int age,String tel_number,String address,String role,double pilot_basic_salary,String pilot_pin,Company company){
        super(first_name,last_name,age,tel_number,address,role,pilot_basic_salary,pilot_pin,company);
        Company.users.add(this);
        Company.employees.add(this);
        Company.pilots.add(this);
    }
}
