package com.hishamfactory;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public abstract class Employee extends User {
    protected double basic_salary;
    protected double tax;
    protected double net_salary;
    protected int permissionLevel = 0;
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
    Employee(String first_name, String last_name,String  user_name, int age, String tel_number, String address, String role,double basic_salary, String user_pin, Company company){
        super(first_name,last_name,user_name,age,tel_number,address,role,user_pin,company);
        this.basic_salary = basic_salary;
    }
    public abstract double calculateSalary();

    public double getBasic_salary() {
        return basic_salary;
    }

    public double getTax() {
        return tax;
    }

    public double getNet_salary() {
        return net_salary;
    }
    public void changePermissionLevelOfEmployee(Employee employee,int new_level) {
            employee.permissionLevel = new_level;
            System.out.println("The level of permission of " + employee.getFirst_name() +","+employee.getLast_name() + " had changes.");
    }
    @Override
    public String toString() {
        return "Employee{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", tel_number='" + tel_number + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
