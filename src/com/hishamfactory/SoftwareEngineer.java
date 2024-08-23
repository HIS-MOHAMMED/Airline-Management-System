package com.hishamfactory;

public class SoftwareEngineer extends RegularUser{
    SoftwareEngineer(String first_name,String last_name,String user_name,int age,String tel_number,String address,String role,double basic_salary,String software_engineer_pin,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,software_engineer_pin,company);
        Company.users.add(this);
        Company.employees.add(this);
    }
    SoftwareEngineer(String first_name,String last_name,String user_name,int age,String tel_number,String address,byte[] software_engineer_pin,String role,double basic_salary,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,software_engineer_pin,role,basic_salary,company);
        Company.users.add(this);
        Company.employees.add(this);
    }
}
