package com.hishamfactory;

public class Manager extends RootUser{
    Manager(String first_name, String last_name,String user_name, int age, String tel_number, String address, String role,double basic_salary, String director_pin, Company company){
        super(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,director_pin,company);
        this.permissionLevel = 8;
        Company.users.add(this);
        Company.employees.add(this);
    }
    Manager(String uuid,String first_name, String last_name,String user_name, int age, String tel_number, String address,byte[] director_pin, String role,double basic_salary, Company company){
        super(uuid,first_name,last_name,user_name,age,tel_number,address,director_pin,role,basic_salary,company);
        this.permissionLevel = 8;
    }
}
