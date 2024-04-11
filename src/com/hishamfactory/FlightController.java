package com.hishamfactory;

import java.util.Scanner;

public class FlightController {
    Scanner sc = new Scanner(System.in);
    public void showAllFlights() {
        System.out.println(".........................");
        for (Flight flight : Company.flights) {
            System.out.println(flight.toString());
        }
        System.out.println(".........................");
    }

    public void bookFlight() {
        //passenger.addNewPassenger();
        //passengers.add(passenger);
    }

    public Flight getFlightById(String id) {
        for (Flight flight : Company.flights) {
            if (flight.getFlight_id().equals(id)) return flight;
        }
        return null;
    }

    public void showFlightDetails(String id) {
        try {
            Flight flight = getFlightById(id);
            System.out.println(flight.toString());
        } catch (Exception e) {
            System.out.println("There is no flight match this id..");
        }

    }

    public void showFlightPassengers() {
        System.out.println("...........................................");
        for (Passenger passenger : Company .passengers) {
            System.out.println("Name: " + passenger.getFirst_name() + passenger.getLast_name());
            System.out.print("Age" + passenger.getUuid());
        }
        System.out.println("...........................................");
    }
    public  void cancelFlight(String id){
        Flight flight = getFlightById(id);
        Company.flights.remove(flight);
    }
}
