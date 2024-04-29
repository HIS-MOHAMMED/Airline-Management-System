package com.hishamfactory;

import java.util.FormatterClosedException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FlightController {
    Scanner sc = new Scanner(System.in);

    public void showAllFlights() {
        System.out.println(".......................List of Flights..........................");
        for (Flight flight : Company.flights) {
            System.out.println(flight.toString());
        }
    }
    public static Flight getFlightById(String id) {
        for (Flight flight : Company.flights) {
            if (flight.getFlight_code().equals(id)) return flight;
        }
        return null;
    }

    public void showFlightInfo(String flight_code) {
        try {
            Flight flight = getFlightById(flight_code);
            System.out.println(flight.toString());
        }catch (NullPointerException e){
            System.out.println("*** This flight not exits ***");
            sc.next();
        }
    }
    public void showFlightPassengers(String flight_code) {
        Flight flight = getFlightById(flight_code);
        if(flight != null) {
            System.out.println(".................Passengers of flight..........................");
            for (Passenger passenger :flight.passengers) {
                System.out.println(passenger.toString());
            }
        }else {
            System.out.println("*** This flight not exits ***");
        }
    }
    public  void cancelFlight(String flight_code){
        Flight flight = getFlightById(flight_code);
        if(flight != null){
            Company.flights.remove(flight);
        }else{
            System.out.println("*** This flight not exists ***");
        }
    }
    public boolean showFlightMenu(Company company, FlightController controller) {
        boolean flag = true;
        try {
            System.out.println(".................Flight Menu..............................");
            System.out.println("1.Book flight");
            System.out.println("2.Show all flights");
            System.out.println("3.Show flight passengers");
            System.out.println("4.Show flight info");
            System.out.println("5.Cancel flight");
            System.out.println("6.Quit");
            System.out.print("Enter a choice: ");
            int option = sc.nextInt();
            String flight_code = null;
            if(option >= 3 && option <= 5){
                System.out.print("Enter flight code: ");
                flight_code = sc.next();
            }
            switch (option) {
                case 1:
                    company.addFlight(company);
                    break;
                case 2:
                    controller.showAllFlights();
                    break;
                case 3:
                    controller.showFlightPassengers(flight_code);
                    break;
                case 4:
                    controller.showFlightInfo(flight_code);
                    break;
                case 5:
                    controller.cancelFlight(flight_code);
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("*** Please enter a valid choice ***");
            }
        } catch (NullPointerException e) {
            System.out.println("*** This flight not exists ***");
        } catch (InputMismatchException e) {
            System.out.println("*** Please enter a valid choice ***");
            sc.next();
        }
        return flag;
    }
}
