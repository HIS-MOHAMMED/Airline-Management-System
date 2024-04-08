package com.hishamfactory;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class Airport {
    private String airport_id;
    private String airport_name;
    private String airport_location;
    private int airport_number_runways;
    private int airport_number_gates;
    private int airport_list_of_flights;

    ArrayList<Airport> airports = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    Airport(){}
    Airport(String airport_id,String airport_name,String airport_location,int airport_number_runways,int airport_number_gates,int airport_list_of_flights){
        this.airport_id = airport_id;
        this.airport_name = airport_name;
        this.airport_location = airport_location;
        this.airport_number_runways = airport_number_runways;
        this.airport_number_gates= airport_number_gates;
        this.airport_list_of_flights = airport_list_of_flights;
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

    public String getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(String airport_id) {
        this.airport_id = airport_id;
    }

    public int getAirport_list_of_flights() {
        return airport_list_of_flights;
    }

    public void setAirport_list_of_flights(int airport_list_of_flights) {
        this.airport_list_of_flights = airport_list_of_flights;
    }
    public void addNewAirport(){
        System.out.print("Enter airport id: ");
        String airport_id = sc.next();
        System.out.print("Enter airport name:");
        String airport_name = sc.next();
        System.out.print("Enter airport location: ");
        String airport_location = sc.next();
        System.out.print("Enter airport number of runways: ");
        int airport_runways = sc.nextInt();
        System.out.print("Enter airport number gates: ");
        int airport_gates = sc.nextInt();
        System.out.print("Enter airport list of flights: ");
        int airport_list_flights = sc.nextInt();
        System.out.println("......................................... ");
        Airport airport = new Airport(airport_id,airport_name,airport_location,airport_runways,airport_gates,airport_list_flights);
        airports.add(airport);
    }
    public Airport getAirportByID(String id){
        for (Airport airport : airports) {
            if(airport.getAirport_id().equals(id)) return airport;
        }
        return null;
    }
    public void printAllAirports(){
        for (Airport airport : airports) {
            System.out.println(airport.toString());
        }
    }
    public void editAirport(String id){
        Airport airport = getAirportByID(id);
        System.out.print("1.Edit airportId");
        System.out.println();
        System.out.print("2.Edit airportName");
        System.out.println();
        System.out.print("3.Edit airportLocation");
        System.out.println();
        System.out.print("4.Edit airport Number of Runways");
        System.out.println();
        System.out.print("5.Edit airport Number of Gates");
        System.out.println();
        System.out.print("6.Edit airport List of Flights");
        System.out.println();
        System.out.print("Which one you want to editing?");
        int option = sc.nextInt();
        switch (option){
            case 1 :
                System.out.print("Enter new airport id : ");
                airport.setAirport_id(sc.next());
                System.out.println("Airport Id changed..");
                break;
            case 2:
                System.out.print("Enter new airport name: ");
                airport.setAirport_name(sc.next());
                System.out.println("Airport name changes..");
                break;
            case 3:
                System.out.print("Enter new airport location: ");
                airport.setAirport_location(sc.next());
                System.out.println("Airport location changed..");
                break;
            case 4:
                System.out.print("Enter airport new number of runways: ");
                airport.setAirport_number_runways(sc.nextInt());
                System.out.println("Airport number of runways changed..");
                break;
            case 5:
                System.out.print("Enter airport new number of gates: ");
                airport.setAirport_number_gates(sc.nextInt());
                System.out.println("Airport number of gates changed..");
                break;
            case 6:
                System.out.print("Enter airport new list of flights: ");
                airport.setAirport_list_of_flights(sc.nextInt());
                System.out.println("Airport list of flights...");
                break;
            default:
                System.out.println("You choose wrong number");
                break;
        }
    }
    public void deleteAirport(String id){
        Airport airport = getAirportByID(id);
        airports.remove(airport);
        System.out.println("The airport " + airport.getAirport_name() + " was deleted..");
    }
    @Override
    public String toString() {
        return "Airport{" +
                "airport_id='" + airport_id + '\'' +
                ", airport_name='" + airport_name + '\'' +
                ", airport_location='" + airport_location + '\'' +
                ", airport_number_runways=" + airport_number_runways +
                ", airport_number_gates=" + airport_number_gates +
                ", airport_list_of_flights=" + airport_list_of_flights +
                '}';
    }
}