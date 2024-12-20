package com.hishamfactory;

import java.io.Serializable;

public class FlightBooked implements Serializable {
    protected String flight_uuid;
    protected Airport departure_airport;
    protected Airport destination_airport;
    protected String departure_time;
    protected String arrival_time;
    protected double ticket_price;
    protected int passenger_seat;

    FlightBooked(String flight_uuid,Airport departure_airport, Airport destination_airport, String departure_time, String arrival_time, double ticket_price, int passenger_seat){
        this.flight_uuid = flight_uuid;
        this.departure_airport = departure_airport;
        this.destination_airport = destination_airport;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.ticket_price  = ticket_price;
        this.passenger_seat = passenger_seat;
    }

    public Airport getDestination_airport() {
        return destination_airport;
    }

    public void setDestination_airport(Airport destination_airport) {
        this.destination_airport = destination_airport;
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

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public int getPassenger_seat() {
        return passenger_seat;
    }

    public void setPassenger_seat(int passenger_seat) {
        this.passenger_seat = passenger_seat;
    }

    public Airport getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(Airport departure_airport) {
        this.departure_airport = departure_airport;
    }

    public String getFlight_uuid() {
        return flight_uuid;
    }

    public void setFlight_uuid(String flight_uuid) {
        this.flight_uuid = flight_uuid;
    }

    @Override
    public String toString() {
        return "BookedFlight{" +
                "departure_airport=" + departure_airport +
                ", destination_airport=" + destination_airport +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", ticket_price=" + ticket_price +
                ", passenger_seat=" + passenger_seat +
                '}';
    }
}
