package com.hishamfactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Employee extends  Person {
    private String address;
    private String status;
    private String role;
    Scanner sc = new Scanner(System.in);

    Employee(String first_name,String last_name,int age,String tel_number,String address,String role,String user_pin,Company company){
        super(first_name,last_name,age,tel_number,user_pin,company);
        this.address = address;
        this.role = role;
        this.status = "Active";
        System.out.printf("New Employee %s, %s with ID %s.\n",first_name,last_name,this.uuid);
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", tel_number='" + tel_number + '\'' +
                ", uuid='" + uuid + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
