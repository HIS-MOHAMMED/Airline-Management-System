package com.hishamfactory;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Passenger extends NormalUser implements Serializable {
    private static final long serialVersionUID = 1L;
    public ArrayList<FlightBooked> passenger_flights = new ArrayList<>();
    Passenger(String first_name,String last_name,String user_name,int age,String tel_number,String address,String passenger_pin,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,passenger_pin,"Passenger",company);
        Company.passengers.add(this);
        Company.users.add(this);
        System.out.println(this.first_name +" "+this.last_name+" passenger account created with ID " + this.getUuid());
    }
    Passenger(String uuid,String first_name,String last_name,String user_name,int age,String tel_number,String address,byte[] passenger_pin,Company company) {
        super(uuid, first_name, last_name, user_name, age, tel_number, address, passenger_pin, "Passenger", company);
        Company.passengers.add(this);
        Company.users.add(this);
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

    /*
    compare passengers by age
     */
   public static  Comparator<Passenger> comparePassengersByAge = new Comparator<Passenger>() {
        @Override
        public int compare(Passenger o1, Passenger o2) {
                return Integer.compare(o1.getAge(),o2.getAge());
        }
    };
}
