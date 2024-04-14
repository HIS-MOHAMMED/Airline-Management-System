package com.hishamfactory;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Scanner;

public class Passenger extends  Person {
    Passenger(String first_name,String last_name,int age,String tel_number,Flight flight,String passenger_pin,Company company){
        super(first_name,last_name,age,tel_number,passenger_pin,company);
        flight.passengers.add(this);
        System.out.println("New passenger added with ID " + this.getUuid());
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
