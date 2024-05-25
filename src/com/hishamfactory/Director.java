package com.hishamfactory;

public class Director extends RootUser{
    Director(String first_name, String last_name,String user_name, int age, String tel_number, String address, String role,double basic_salary, String manager_pin, Company company){
        super(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,manager_pin,company);
        Company.users.add(this);
        Company.employees.add(this);
    }
}
