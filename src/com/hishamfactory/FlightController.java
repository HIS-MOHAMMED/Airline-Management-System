package com.hishamfactory;

import java.util.FormatterClosedException;
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
    public static Flight getFlightById(String id) {
        for (Flight flight : Company.flights) {
            if (flight.getFlight_code().equals(id)) return flight;
        }
        return null;
    }
    public  Flight getFlightById() {
        System.out.print("Enter flight code: ");
        String flight_code = sc.next();
        for (Flight flight : Company.flights) {
            if (flight.getFlight_code().equals(flight_code)) return flight;
        }
        return null;
    }

    public void showFlightInfo() {
        System.out.print("Enter flight code: ");
        String flight_code = sc.next();
        try {
            Flight flight = getFlightById(flight_code);
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
    public  void cancelFlight(){
        System.out.print("Enter flight code: ");
        String flight_code = sc.next();
        Flight flight = getFlightById(flight_code);
        Company.flights.remove(flight);
    }
    public void showFlightMenu(Company company, FlightController controller){
        System.out.println(".................Flight Menu...........................");
        System.out.println("1.Book flight");
        System.out.println("2.Show all flights");
        System.out.println("3.Get flight by Code");
        System.out.println("4.Show flights info");
        System.out.println("5.Show flight passengers");
        System.out.println("6.Cancel flight");
        System.out.print("Enter a choice: ");
        int option = sc.nextInt();
        switch (option){
            case 1:
                company.addFlight(company);
                break;
            case 2:
                controller.showAllFlights();
                break;
            case 3:
                controller.getFlightById();
                break;
            case 4:
                controller.showFlightInfo();
                break;
            case 5:
                controller.showFlightPassengers();
                break;
            case 6:
                controller.cancelFlight();
                break;
        }

    }
}
