package com.hishamfactory;

public class FlightBooked {
    protected Airport departure_airport;
    protected Airport destination_airport;
    protected String departure_time;
    protected String arrival_time;
    protected double ticket_price;
    protected int passenger_seat;

    FlightBooked(Airport departure_airport, Airport destination_airport, String departure_time, String arrival_time, double ticket_price, int passenger_seat){
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
