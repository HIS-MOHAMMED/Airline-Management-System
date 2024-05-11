package com.hishamfactory;

public class Manager extends RootUser{
    Manager(String first_name, String last_name, int age, String tel_number, String address, String role, String director_pin, Company company){
        super(first_name,last_name,age,tel_number,address,role,director_pin,company);
        Company.users.add(this);
        Company.employees.add(this);
    }
}
