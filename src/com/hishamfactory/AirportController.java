package com.hishamfactory;

import java.util.Scanner;

public class AirportController {
    Scanner sc = new Scanner(System.in);
    public Airport getAirportByID(String id){
        for (Airport airport : Company.airports) {
            if(airport.getAirport_code().equals(id)) return airport;
        }
        return null;
    }
    public void printAllAirports(){
        for (Airport airport : Company.airports) {
            System.out.println(airport.toString());
        }
    }
    public void editAirport(String id){
        Airport airport = getAirportByID(id);
        System.out.print("1.Edit airportName");
        System.out.println();
        System.out.print("2.Edit airportLocation");
        System.out.println();
        System.out.print("3.Edit airport Number of Runways");
        System.out.println();
        System.out.print("4.Edit airport Number of Gates");
        System.out.println();
        System.out.print("5.Edit airport List of Flights");
        System.out.println();
        System.out.print("Which one you want to editing?");
        int option = sc.nextInt();
        switch (option){
            case 1:
                System.out.print("Enter new airport name: ");
                airport.setAirport_name(sc.next());
                System.out.println("Airport name changes..");
                break;
            case 2:
                System.out.print("Enter new airport location: ");
                airport.setAirport_location(sc.next());
                System.out.println("Airport location changed..");
                break;
            case 3:
                System.out.print("Enter airport new number of runways: ");
                airport.setAirport_number_runways(sc.nextInt());
                System.out.println("Airport number of runways changed..");
                break;
            case 4:
                System.out.print("Enter airport new number of gates: ");
                airport.setAirport_number_gates(sc.nextInt());
                System.out.println("Airport number of gates changed..");
                break;
            default:
                System.out.println("You choose wrong number");
                break;
        }
    }
    public void deleteAirport(String id){
        Airport airport = getAirportByID(id);
        Company.airports.remove(airport);
        System.out.println("The airport " + airport.getAirport_name() + " was deleted..");
    }

}
