package com.hishamfactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Passenger extends  Person {
    Scanner sc = new Scanner(System.in);
    Passenger(String first_name,String last_name,int age,double tel_number,double id){
        super(first_name,last_name,age,tel_number,id);
    }
    public void addNewPassenger(){
    }
    public void getPassengerByName(String first_last_name){}
    public void printAllPassengers(ArrayList<Passenger> passengers){}
    public void editPassengerInfo(double id){}
    public void deletePassenger(double id){}
}
