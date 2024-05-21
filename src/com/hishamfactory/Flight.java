package com.hishamfactory;

import java.util.ArrayList;

public class Flight implements Identifiable{
    private String flight_code;
    public Airport departure_airport;
    public Airport destination_airport;
    private String departure_time;
    private String arrival_time;
    public Plane plane;
    private double ticket_price;
    public ArrayList<Passenger> passengers = new ArrayList<>();

    Flight(Airport departure_airport, Airport destination_airport, String departure_time, String arrival_time, Plane plane,Company company) {
        this.departure_airport = departure_airport;
        this.destination_airport = destination_airport;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.plane = plane;
        this.ticket_price = 0.0;
        this.flight_code =company.getNewUUID();
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

    public void setTicket_price(double ticket_price) {
        if(ticket_price >= 100.0 &&  ticket_price <= 1000.0){
            this.ticket_price = ticket_price;
        }else{
            System.out.println("**************Sorry pricing Max($1000.0 and Min($100.0).Try set it again**************");
            if(this.ticket_price == 0.0) {
                this.ticket_price = 100.0;
                System.out.println("****************Ticket price changed to default price($100.0)******************");
            }
        }
    }
    public boolean hasAvailableSeat(Flight flight){
        return flight.passengers.size() < flight.getPlane().getPlane_capacity();
    }
    @Override
    public String toString() {
        return "Flight{" +
                "flight_id='" + flight_code + '\'' +
                ", departure_airport=" + departure_airport.getAirport_name() +
                ", destination_airport=" + destination_airport.getAirport_name() +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", plane=" + plane.getPlane_id() +
                ", ticket_price=" + ticket_price +
                '}';
    }

    @Override
    public String identify() {
        return this.flight_code;
    }
}
