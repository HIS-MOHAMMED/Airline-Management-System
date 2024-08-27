package com.hishamfactory;

import java.util.ArrayList;

public class Passenger extends NormalUser {
    public ArrayList<FlightBooked> passenger_flights = new ArrayList<>();
    Passenger(String first_name,String last_name,String user_name,int age,String tel_number,String address,String passenger_pin,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,passenger_pin,"Passenger",company);
        Company.passengers.add(this);
        Company.users.add(this);
        System.out.println(this.first_name +" "+this.last_name+" passenger account created with ID " + this.getUuid());
    }
    Passenger(String uuid,String first_name,String last_name,String user_name,int age,String tel_number,String address,byte[] passenger_pin,Company company) {
        super(uuid, first_name, last_name, user_name, age, tel_number, address, passenger_pin, "Passenger", company);
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
