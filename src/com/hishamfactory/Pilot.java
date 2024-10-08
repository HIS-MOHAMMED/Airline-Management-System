package com.hishamfactory;

import java.io.Serializable;

public class Pilot extends RegularUser implements Serializable {
    private static final long serialVersionUID = 1L;
    Pilot(String first_name,String last_name,String user_name,int age,String tel_number,String address,String role,double pilot_basic_salary,String pilot_pin,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,role,pilot_basic_salary,pilot_pin,company);
        Company.users.add(this);
        Company.employees.add(this);
        Company.pilots.add(this);
    }
    Pilot(String uuid,String first_name,String last_name,String user_name,int age,String tel_number,String address,byte[] pilot_pin,String role,double pilot_basic_salary,Company company){
        super(uuid, first_name,last_name,user_name,age,tel_number,address,pilot_pin,role,pilot_basic_salary,company);
    }
}
