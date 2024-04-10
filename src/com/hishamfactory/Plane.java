package com.hishamfactory;

import java.util.Scanner;

public class Plane {
    private String plane_id;
    private String plane_model;
    private String plane_manufacturer;
    private String plane_year;
    private int plane_capacity;
    private int plane_current_passengers;

    Scanner sc = new Scanner(System.in);
    Plane(){
    }
    Plane(String plane_model,String plane_manufacturer,String plane_year,int plane_capacity){
        this.plane_model = plane_model;
        this.plane_capacity  = plane_capacity;
        this.plane_manufacturer = plane_manufacturer;
        this.plane_current_passengers = 0 ;
        this.plane_year =plane_year;
    }
    public String getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(String plane_id) {
        this.plane_id = plane_id;
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
    public void addNewPlane(){
        System.out.print("Enter plane id: ");
        String id = sc.next();
        System.out.print("Enter plane model: ");
        String model = sc.next();
        System.out.print("Enter plane manufacturer: ");
        String manufacturer = sc.next();
        System.out.print("Enter plane year manufacturerd: ");
        String plane_year_manufacturerd = sc.next();
        System.out.print("Enter plane capacity: ");
        int capacity = sc.nextInt();
        System.out.print("Enter plane type: ");
        String plane_type = sc.next();
        System.out.println("...............................");

    }
    public Plane getPlaneById(String id){
        for(Plane plane:Company.planes){
            if(id.equals(plane.getPlane_id())) return plane;
        }
        return null;
    }
    public void printAllPlanes(){
        System.out.println(".........................");
        for(Plane plane: Company.planes){
            System.out.println(plane.getPlane_id());
        }
        System.out.println(".........................");
    }
    public void editPlane(){
        System.out.print("Write plane Id: ");
        Plane plane = getPlaneById(sc.next());
        try {
                System.out.println("1.Edit Id");
                System.out.println("2.Edit Model");
                System.out.println("3.Edit Manufacturer");
                System.out.println("4.Edit Capacity");
                System.out.println("5.Edit Current Passengers: ");
                System.out.println("6.Quit");
                System.out.print("select option: ");
                int option = sc.nextInt();
                switch (option){
                    case  1:
                        System.out.print("Enter new Id: ");
                        plane.setPlane_id(sc.next());
                        System.out.println("Id Changed");
                        break;
                    case 2:
                        System.out.print("Enter new Model: ");
                        plane.setPlane_model(sc.next());
                        System.out.println("Model changed");
                        break;

                    case 3:
                        System.out.print("Enter new Manufacturer: ");
                        plane.setPlane_manufacturer(sc.next());
                        System.out.println("Manufacturer changed");
                        break;

                    case 4:
                        System.out.print("Enter new Capacity: ");
                        plane.setPlane_capacity(sc.nextInt());
                        System.out.println("Id changed");
                        break;
                }

        }catch (Exception e){
            System.out.println("The given value is null.check it");
        }
    }
    public void deletePlane(String id){
        Plane plane = getPlaneById(id);
        Company.planes.remove(plane);
        System.out.println("Plane has "+ plane.getPlane_id() + " was deleted...");
    }
}
