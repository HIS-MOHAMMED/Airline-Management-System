package com.hishamfactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirportControllerTest {

    Airport istanbulAirport;
    Airport adenAirport;
    AirportController airportsController;

    @BeforeEach
    public void setup(){
        istanbulAirport = new Airport("Istanbul","Turkey",24,100,new Company("Turkish Government"));
        adenAirport = new Airport("Aden","Yemen",44,50,new Company("Yemeni Government"));
        Company.airports.add(istanbulAirport);
        Company.airports.add(adenAirport);
        airportsController = new AirportController();
    }

    @Test
    public void returnedAirportByNameSame(){
        assertSame(istanbulAirport, AirportController.getAirportByName("Istanbul"));
    }
    @Test
    public void returnedAirportByNameNotSame(){
        assertNotSame(adenAirport, AirportController.getAirportByName("Istanbul"));
    }
    @Test
    public void returnedAirportByUuidSame(){
        assertSame(istanbulAirport,AirportController.getAirportByID(istanbulAirport.getAirport_uuid()));
    }
    @Test
    public void returnedAirportByUuidNotSame(){
        assertNotSame(adenAirport,AirportController.getAirportByName(istanbulAirport.getAirport_uuid()));
    }
    @Test
    public void deletedAirportIsTrue(){
        airportsController.deleteAirport(istanbulAirport.getAirport_uuid());
        assertFalse(Company.airports.contains(istanbulAirport));
    }

}
