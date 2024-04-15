package com.hishamfactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Employee extends  Person {
    private String address;
    private String status;
    private String role;

    Employee(String first_name, String last_name, int age, String tel_number, String address, String role, String user_pin, Company company){
        super(first_name,last_name,age,tel_number,user_pin,company);
        this.address = address;
        this.role = role;
        this.status = "Active";
        System.out.println(this.role + " " + this.first_name + ", " + this.last_name + " added with ID "  + this.uuid);
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
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public boolean validatePin(String employee_pin){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return MessageDigest.isEqual(md.digest(employee_pin.getBytes(StandardCharsets.UTF_8)),this.pinHash);
        }catch (NoSuchAlgorithmException e){
            System.out.println("error, caught NoSuchAlgorithmException..");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
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
