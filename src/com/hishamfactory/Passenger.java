package com.hishamfactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Passenger extends  Person {
    public ArrayList<Flight> passenger_flights = new ArrayList<>();
    Passenger(String first_name,String last_name,int age,String tel_number,String passenger_pin,Company company){
        super(first_name,last_name,age,tel_number,passenger_pin,company);
        Company.passengers.add(this);
        System.out.println(this.first_name +" "+this.last_name+" passenger account created with ID " + this.getUuid());
    }

    /**
     * Check whether a given password matches the passenger password or not
     * @param passenger_pin     the password of passenger
     * @return                  whether the password is valid or not
     */
    public boolean validatePin(String passenger_pin){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return MessageDigest.isEqual(md.digest(passenger_pin.getBytes(StandardCharsets.UTF_8)),this.pinHash);
        }catch (NoSuchAlgorithmException e){
            System.out.println("error, caught NoSuchAlgorithmException..");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }
    @Override
    public String toString() {
        return "Passenger{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", tel_number='" + tel_number + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
