package com.hishamfactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Flight {
    private String flight_id;
    public Airport airports;
    public Airport departure_airport;
    public Airport destination_airport;
    private String departure_time;
    private String arrival_time;
    public Plane plane;
    public Plane planes;
    private double ticket_price;

    ArrayList<Flight> flights = new ArrayList<>();
    ArrayList<Passenger> passengers = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    Flight() {
        planes = new Plane();
    }

    Flight(Airport departure_airport, Airport destination_airport, String departure_time, String arrival_time, Plane plane, double ticket_price) {
        this.departure_airport = departure_airport;
        this.destination_airport = destination_airport;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.plane = plane;
        this.ticket_price = ticket_price;
    }

    public Airport getDestination_airport() {
        return destination_airport;
    }

    public void setDestination_airport(Airport destination_airport) {
        this.destination_airport = destination_airport;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    public Airport getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(Airport departure_airport) {
        this.departure_airport = departure_airport;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Plane getAircraft_type() {
        return plane;
    }

    public void setAircraft_type(Plane plane) {
        this.plane = plane;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public void addNewFlight() {
        System.out.print("Enter flight id: ");
        String flight_id = sc.next();

        airports.printAllAirports();
        System.out.print("Choose departure id airport: ");
        this.departure_airport = airports.getAirportByID(sc.next());

        airports.printAllAirports();
        System.out.print("Choose destination id airport: ");
        destination_airport = airports.getAirportByID(sc.next());

        System.out.print("Enter departure time(DD/MM/YYYY): ");
        String dep_time = sc.next();

        System.out.print("Enter destination time(DD/MM/YYYY): ");
        String des_time = sc.next();

        planes.printAllPlanes();
        System.out.print("Choose id plane: ");
        plane = planes.getPlaneById(sc.next());

        System.out.print("Enter ticket price: ");
        double ticket_price = sc.nextDouble();

//        Flight newFlight = new Flight(flight_id, departure_airport, destination_airport, departure_time, des_time, plane, ticket_price);
//        flights.add(newFlight);
    }

    public void showAllFlights() {
        System.out.println(".........................");
        for (Flight flight : flights) {
            System.out.println(flight.toString());
        }
        System.out.println(".........................");
    }

    public void bookFlight() {
        //passenger.addNewPassenger();
        //passengers.add(passenger);
    }

    public Flight getFlightById(String id) {
        for (Flight flight : flights) {
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
        for (Passenger passenger : this.passengers) {
            System.out.println("Name: " + passenger.getFirst_name() + passenger.getLast_name());
            System.out.print("Age" + passenger.getUuid());
        }
        System.out.println("...........................................");
    }
    public  void cancelFlight(String id){
        Flight flight = getFlightById(id);
        flights.remove(flight);
    }
    @Override
    public String toString() {
        return "Flight{" +
                "flight_id='" + flight_id + '\'' +
                ", departure_airport=" + departure_airport.getAirport_name() +
                ", destination_airport=" + destination_airport.getAirport_name() +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", plane=" + plane.getPlane_id() +
                ", ticket_price=" + ticket_price +
                '}';
    }
}
