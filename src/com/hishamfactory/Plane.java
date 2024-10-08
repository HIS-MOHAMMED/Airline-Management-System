package com.hishamfactory;

import java.io.Serializable;

public class Plane implements Identifiable, Serializable {
    private static final long serialVersionUID = 1L;
    private String planeUuid;
    private String plane_model;
    private String serial_number;
    private String plane_manufacturer;
    private String plane_year;
    private int plane_capacity;
    private int plane_current_passengers;

    Plane(String plane_model,String serial_number,String plane_manufacturer, String plane_year, int plane_capacity, Company company){
        this.plane_model = plane_model;
        this.serial_number =serial_number;
        this.plane_capacity  = plane_capacity;
        this.plane_manufacturer = plane_manufacturer;
        this.plane_current_passengers = 0 ;
        this.plane_year =plane_year;
        this.planeUuid =company.getNewUUID();
        System.out.println(this.plane_model + "'s plane added with code " + this.planeUuid);
    }
    Plane(String uuid,String plane_model,String serial_number,String plane_manufacturer, String plane_year, int plane_capacity){
        this.plane_model = plane_model;
        this.serial_number =serial_number;
        this.plane_capacity  = plane_capacity;
        this.plane_manufacturer = plane_manufacturer;
        this.plane_current_passengers = 0 ;
        this.plane_year =plane_year;
        this.planeUuid = uuid;
        System.out.println(this.plane_model + "'s plane added with code " + this.planeUuid);
    }
    public String getPlaneUuid() {
        return planeUuid;
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
        this.plane_current_passengers += plane_current_passengers;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "plane_id='" + planeUuid + '\'' +
                ", plane_model='" + plane_model + '\'' +
                ", serial_number='" + serial_number +'\''+
                ", plane_manufacturer='" + plane_manufacturer + '\'' +
                ", plane_year='" + plane_year + '\'' +
                ", plane_capacity=" + plane_capacity +
                ", plane_current_passengers=" + plane_current_passengers +
                '}';
    }
    @Override
    public String identify(){
        return this.planeUuid;
    }
}
