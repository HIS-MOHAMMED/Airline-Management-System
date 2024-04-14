package com.hishamfactory;

import java.util.Scanner;

public class Plane {
    private String plane_id;
    private String plane_model;
    private String plane_manufacturer;
    private String plane_year;
    private int plane_capacity;
    private int plane_current_passengers;

    Plane(String plane_model,String plane_manufacturer,String plane_year,int plane_capacity,Company company){
        this.plane_model = plane_model;
        this.plane_capacity  = plane_capacity;
        this.plane_manufacturer = plane_manufacturer;
        this.plane_current_passengers = 0 ;
        this.plane_year =plane_year;
        this.plane_id =company.getNewUUID();
        System.out.println("New plane added with code " + this.plane_id);
    }
    public String getPlane_id() {
        return plane_id;
    }

    public String getPlane_model() {
        return plane_model;
    }

    public void setPlane_model(String plane_model) {
        this.plane_model = plane_model;
    }

    public String getPlane_manufacturer() {
        return plane_manufacturer;
    }

    public void setPlane_manufacturer(String plane_manufacturer) {
        this.plane_manufacturer = plane_manufacturer;
    }

    public String getPlane_year() {
        return plane_year;
    }

    public void setPlane_year(String plane_year) {
        this.plane_year = plane_year;
    }

    public int getPlane_capacity() {
        return plane_capacity;
    }

    public void setPlane_capacity(int plane_capacity) {
        this.plane_capacity = plane_capacity;
    }

    public int getPlane_current_passengers() {
        return plane_current_passengers;
    }
    public void setPlane_current_passengers(int plane_current_passengers) {
        this.plane_current_passengers = plane_current_passengers;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "plane_id='" + plane_id + '\'' +
                ", plane_model='" + plane_model + '\'' +
                ", plane_manufacturer='" + plane_manufacturer + '\'' +
                ", plane_year='" + plane_year + '\'' +
                ", plane_capacity=" + plane_capacity +
                ", plane_current_passengers=" + plane_current_passengers +
                '}';
    }
}
