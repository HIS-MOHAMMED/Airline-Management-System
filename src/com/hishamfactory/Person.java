package com.hishamfactory;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String first_name;
    protected String last_name;
    protected int age;
    protected String tel_number;
    protected String address;
    protected String status;
    protected String role;
    Person(String first_name,String last_name,int age,String tel_number,String address,String role){
        this.first_name = first_name;
        this.last_name = last_name;
        this.age =age;
        this.tel_number = tel_number;
        this.address = address;
        this.role = role;
        this.status  = "Active";
    }
    Person(String first_name,String last_name,int age,String tel_number,String address){
        this.first_name = first_name;
        this.last_name = last_name;
        this.age =age;
        this.tel_number = tel_number;
        this.address = address;
        this.status  = "Active";
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        int official_work_age = 18;
        if(official_work_age > age) {
            System.out.println("*** Since age less than official age,the default age (18) applied ***");
            this.age = official_work_age;
        }else{
            this.age = age;
        }
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
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
}
