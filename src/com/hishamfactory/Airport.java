package com.hishamfactory;
import java.io.Serializable;
import java.util.Scanner;

public class Airport implements Identifiable, Serializable {
    private static final long serialVersionUID = 1L;
    private  String airport_uuid;
    private String airport_name;
    private String airport_location;
    private int airport_number_runways;
    private int airport_number_gates;

    transient Scanner sc = new Scanner(System.in);

    /**
     * Create new airport
     * @param airport_name              the name of the airport
     * @param airport_location          the location of the airport
     * @param airport_number_runways    the number of runways of the airport
     * @param airport_number_gates      the number of gates of the airport
     * @param company                   the company who has the system
     */
    Airport(String airport_name,String airport_location,int airport_number_runways,int airport_number_gates,Company company){
        this.airport_name = airport_name;
        this.airport_location = airport_location;
        this.airport_number_runways = airport_number_runways;
        this.airport_number_gates= airport_number_gates;
        this.airport_uuid = company.getNewUUID();
        System.out.println(this.airport_name +" airport added with code " + this.airport_uuid);
    }
    Airport(String uuid,String airport_name,String airport_location,int airport_number_runways,int airport_number_gates){
        this.airport_name = airport_name;
        this.airport_location = airport_location;
        this.airport_number_runways = airport_number_runways;
        this.airport_number_gates= airport_number_gates;
        this.airport_uuid = uuid;
        System.out.println(this.airport_name +" airport added with code " + this.airport_uuid);
    }
    public int getAirport_number_gates() {
        return airport_number_gates;
    }

    public void setAirport_number_gates(int airport_number_gates) {
        this.airport_number_gates = airport_number_gates;
    }

    public String getAirport_name() {
        return airport_name;
    }

    public void setAirport_name(String airport_name) {
        this.airport_name = airport_name;
    }

    public int getAirport_number_runways() {
        return airport_number_runways;
    }

    public void setAirport_number_runways(int airport_number_runways) {
        this.airport_number_runways = airport_number_runways;
    }

    public String getAirport_location() {
        return airport_location;
    }

    public void setAirport_location(String airport_location) {
        this.airport_location = airport_location;
    }

    public String getAirport_uuid() {
        return airport_uuid;
    }
    @Override
    public String toString() {
        return "Airport{" +
                "airport_id='" + airport_uuid + '\'' +
                ", airport_name='" + airport_name + '\'' +
                ", airport_location='" + airport_location + '\'' +
                ", airport_number_runways=" + airport_number_runways +
                ", airport_number_gates=" + airport_number_gates +
                '}';
    }

    @Override
    public String identify() {
        return this.airport_uuid;
    }
}