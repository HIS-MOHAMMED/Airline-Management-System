package com.hishamfactory;

public class SoftwareEngineer extends RegularUser{
    SoftwareEngineer(String first_name,String last_name,int age,String tel_number,String address,String role,String software_engineer_pin,Company company){
        super(first_name,last_name,age,tel_number,address,role,software_engineer_pin,company);
        Company.users.add(this);
        Company.employees.add(this);
    }
}
