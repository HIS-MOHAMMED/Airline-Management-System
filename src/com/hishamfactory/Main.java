package com.hishamfactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      /*
      Here where I write testing coding for new classes I created
       */
        Flight flight = new Flight();
        flight.addNewFlight();
        flight.bookFlight();
        flight.bookFlight();
        flight.bookFlight();
        flight.showAllFlights();
        flight.showFlightPassengers();
    }
}
