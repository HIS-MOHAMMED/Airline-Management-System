package com.hishamfactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Employee extends  Person {
    private String address;
    private String status;
    private String role;

    /**
     * Create new employee regardless of role
     * @param first_name    the first name of admin
     * @param last_name     the last name of admin
     * @param age           the age of admin
     * @param tel_number    the tel number of admin
     * @param address       the address of admin
     * @param role          the role of admin on company
     * @param user_pin      the password of admin on system
     * @param company       the company who has the system
     */
    Employee(String first_name, String last_name, int age, String tel_number, String address, String role, String user_pin, Company company){
        super(first_name,last_name,age,tel_number,user_pin,company);
        this.address = address;
        this.role = role;
        this.status = "Active";
        System.out.println(this.role + " " + this.first_name + ", " + this.last_name + " added with ID "  + this.uuid);
    }

    /**
     * Create new admin when the system is new and first employee must be admin
     * @param first_name    the first name of employee
     * @param last_name     the last name of employee
     * @param age           the age of employee
     * @param tel_number    the tel number fo employee
     * @param address       the address of employee
     * @param role          the role of employee
     * @param user_pin      the password of employee
     * @param isAdmin       this employee is admin or not
     * @param company       the company who has the system
     */
    Employee(String first_name, String last_name, int age, String tel_number, String address, String role, String user_pin,boolean isAdmin, Company company) {
        super(first_name, last_name, age, tel_number, user_pin, company);
        this.address = address;
        this.role = role;
        this.status = "Active";
        if (isAdmin){
            System.out.println(this.role + " " + this.first_name + ", " + this.last_name + " added with ID " + this.uuid);
    }
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

    /**
     *Check whether a given password matches the employee password or not
     * @param employee_pin  the password of the employee
     * @return              whether the password is valid or not
     */
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
