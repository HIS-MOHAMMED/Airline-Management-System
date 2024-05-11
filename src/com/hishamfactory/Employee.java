package com.hishamfactory;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Employee extends User {

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
        super(first_name,last_name,age,tel_number,address,role,user_pin,company);
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
