package com.hishamfactory;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class Airport {
    private final String airport_code;
    private String airport_name;
    private String airport_location;
    private int airport_number_runways;
    private int airport_number_gates;

    Scanner sc = new Scanner(System.in);

    Airport(String airport_code,String airport_name,String airport_location,int airport_number_runways,int airport_number_gates){
        this.airport_code = airport_code;
        this.airport_name = airport_name;
        this.airport_location = airport_location;
        this.airport_number_runways = airport_number_runways;
        this.airport_number_gates= airport_number_gates;
    }
    public int getAirport_number_gates() {
        return airport_number_gates;
    }

    public void setAirport_number_gates(int airport_number_gates) {
        this.airport_number_gates = airport_number_gates;
    }

    public String getAirport_name() {
        return airport_name;
    }

    public void setAirport_name(String airport_name) {
        this.airport_name = airport_name;
    }

    public int getAirport_number_runways() {
        return airport_number_runways;
    }

    public void setAirport_number_runways(int airport_number_runways) {
        this.airport_number_runways = airport_number_runways;
    }

    public String getAirport_location() {
        return airport_location;
    }

    public void setAirport_location(String airport_location) {
        this.airport_location = airport_location;
    }

    public String getAirport_code() {
        return airport_code;
    }

//    public void addNewAirport(){
//        System.out.print("Enter airport id: ");
//        String airport_id = sc.next();
//        System.out.print("Enter airport name:");
//        String airport_name = sc.next();
//        System.out.print("Enter airport location: ");
//        String airport_location = sc.next();
//        System.out.print("Enter airport number of runways: ");
//        int airport_runways = sc.nextInt();
//        System.out.print("Enter airport number gates: ");
//        int airport_gates = sc.nextInt();
//        System.out.print("Enter airport list of flights: ");
//        int airport_list_flights = sc.nextInt();
//        System.out.println("......................................... ");
////        Airport airport = new Airport(airport_id,airport_name,airport_location,airport_runways,airport_gates,airport_list_flights);
////        airports.add(airport);
//    }
    @Override
    public String toString() {
        return "Airport{" +
                "airport_id='" + airport_code + '\'' +
                ", airport_name='" + airport_name + '\'' +
                ", airport_location='" + airport_location + '\'' +
                ", airport_number_runways=" + airport_number_runways +
                ", airport_number_gates=" + airport_number_gates +
                '}';
    }
}