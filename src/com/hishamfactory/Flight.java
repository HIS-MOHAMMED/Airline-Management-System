package com.hishamfactory;

import java.util.ArrayList;

public class Flight implements Identifiable{
    protected String flight_code;
    protected Airport departure_airport;
    protected Airport destination_airport;
    protected String departure_time;
    protected String arrival_time;
    protected Plane plane;
    protected double ticket_price;
    private Pilot flight_captain;
    private ArrayList<Passenger> passengers;
    private String[] flight_seats;

    Flight(Airport departure_airport, Airport destination_airport, String departure_time, String arrival_time, Plane plane,Pilot flight_captain,int flight_seats,Company company) {
        this.departure_airport = departure_airport;
        this.destination_airport = destination_airport;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.plane = plane;
        this.flight_captain = flight_captain;
        this.ticket_price = 0.0;
        this.flight_seats = new String[flight_seats];
        this.flight_code =company.getNewUUID();
        passengers = new ArrayList<>();
        System.out.println("From "+this.departure_airport.getAirport_name() +" to " +this.destination_airport.getAirport_name()+" flight booked with code " + this.flight_code);
    }
    Flight(String flight_code,Airport departure_airport, Airport destination_airport, String departure_time, String arrival_time, Plane plane,Pilot flight_captain,String[] flight_seats,ArrayList<Passenger> passengers) {
        this.departure_airport = departure_airport;
        this.destination_airport = destination_airport;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.plane = plane;
        this.flight_captain = flight_captain;
        this.ticket_price = 0.0;
        this.flight_seats = flight_seats;
        this.flight_code = flight_code;
        this.passengers = passengers;
        System.out.println("From "+this.departure_airport.getAirport_name() +" to " +this.destination_airport.getAirport_name()+" flight booked with code " + this.flight_code);
    }

    public Airport getDestination_airport() {
        return destination_airport;
    }

    public void setDestination_airport(Airport destination_airport) {
        this.destination_airport = destination_airport;
    }
    public String getFlight_code() {
        return flight_code;
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
    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
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

    public Pilot getFlight_captain() {
        return flight_captain;
    }

    public void setFlight_captain(Pilot flight_captain) {
        this.flight_captain = flight_captain;
    }

    public void setTicket_price(double ticket_price) {
        if(ticket_price >= 100.0 &&  ticket_price <= 1000.0){
            this.ticket_price = ticket_price;
        }else{
            if(this.ticket_price == 0.0) {
                System.out.println("**************Sorry pricing Max($1000.0 and Min($100.0).Try set it again**************");
                if (this.ticket_price == 0.0) {
                    this.ticket_price = 100.0;
                    System.out.println("****************Ticket price changed to default price($100.0)******************");
                }
            }
        }
    }
    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
    public String[] getFlight_seats() {
        return flight_seats;
    }

    public void setFlight_seats(String[] flight_seats) {
        this.flight_seats = flight_seats;
    }
    public boolean hasAvailableSeat(Flight flight){
        return flight.passengers.size() < flight.getPlane().getPlane_capacity();
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flight_code='" + flight_code + '\'' +
                ", departure_airport=" + departure_airport +
                ", destination_airport=" + destination_airport +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", plane=" + plane +
                ", ticket_price=" + ticket_price +
                ", flight_captain=" + flight_captain +
                ", passengers=" + passengers +
                '}';
    }

    @Override
    public String identify() {
        return this.flight_code;
    }
}
