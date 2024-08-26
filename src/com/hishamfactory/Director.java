package com.hishamfactory;

public class Director extends RootUser{
    Director(String first_name, String last_name,String user_name, int age, String tel_number, String address, String role,double basic_salary, String manager_pin, Company company){
        super(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,manager_pin,company);
        this.permissionLevel = 9;
        Company.users.add(this);
        Company.employees.add(this);
    }
    Director(String uuid,String first_name, String last_name,String user_name, int age, String tel_number, String address,byte[] manager_pin, String role,double basic_salary,  Company company){
        super(uuid, first_name,last_name,user_name,age,tel_number,address,manager_pin,role,basic_salary,company);
        this.permissionLevel = 9;
        Company.users.add(this);
        Company.employees.add(this);
    }
}
