package com.hishamfactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Person {
    protected String first_name;
    protected String last_name;
    protected int age;
    protected String  tel_number;
    protected String uuid;
    protected byte pinHash[];

    Person(String first_name,String last_name,int age,String tel_number,String person_pin,Company company){
        this.first_name =first_name;
        this.last_name = last_name;
        this.age = age;
        this.tel_number = tel_number;

        try{
            MessageDigest md =MessageDigest.getInstance("SHA-256");
            this.pinHash =md.digest(person_pin.getBytes(StandardCharsets.UTF_8));
        }catch (NoSuchAlgorithmException e){
            System.err.println("error, caught NoSuchAlgorithmException.");
            e.printStackTrace();
            System.exit(0);
        }
        this.uuid = company.getNewUUID();
    }
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public String getUuid() {
        return uuid;
    }

    public byte[] getPinHash() {
        return pinHash;
    }
}
