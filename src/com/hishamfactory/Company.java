package com.hishamfactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Company {
    private String name;
    public static ArrayList<Employee> employees;
    public static ArrayList<Passenger> passengers;
    public static ArrayList<Plane> planes;
    public static ArrayList<Airport> airports;
    public static ArrayList<Flight> flights;
    public static ArrayList<String> uuids;

    Random rm = new Random();
    Scanner sc = new Scanner(System.in);

    public Company(String name){
        this.name = name;
        employees = new ArrayList<>();
        passengers = new ArrayList<>();
        planes = new ArrayList<>();
        airports = new ArrayList<>();
        flights = new ArrayList<>();
        uuids = new ArrayList<>();
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNewUUID(){
        String uuid;
        int len = 10;
        boolean uniqueUuid;
        do{
            uuid = "";
            //generate the number
            for(int i = 0; i < len; i++){
                uuid += (Integer)rm.nextInt(10);
            }

            uniqueUuid = false;
            for (String id : uuids) {
                if(uuid.compareTo(id) == 0){
                    uniqueUuid = true;
                    break;
                }
            }
        }while (uniqueUuid);
        uuids.add(uuid);
        return uuid;
    }

    public Employee addEmployee(Company company){
        System.out.print("Enter employee first name: ");
        String first_name = sc.next();
        System.out.print("Enter employee last name: ");
        String last_name = sc.next();
        System.out.print("Enter employee age: ");
        int age = sc.nextInt();
        System.out.print("Enter employee tel_number: ");
        String tel_number = sc.next();
        System.out.print("Enter employee address: ");
        String address = sc.next();
        System.out.print("Enter employee role: ");
        String role = sc.next();
        System.out.print("Enter employee pin: ");
        String employee_pin = sc.next();
        Employee newEmployee = new Employee(first_name,last_name,age,tel_number,address,role,employee_pin,company);
        employees.add(newEmployee);
        return newEmployee;
    }

    public  Passenger addPassenger(String first_name,String last_name,int age,String tel_number,String passenger_pin,Company company){
        Passenger newPassenger = new Passenger(first_name,last_name,age,tel_number,passenger_pin,company);
        passengers.add(newPassenger);
        return newPassenger;
    }
    public Employee employeeLogin(String employee_id, String employee_pin){
        for (Employee employee : Company.employees) {
            if(employee.getUuid().compareTo(employee_id) == 0 && employee.validatePin(employee_pin)) return employee;
        }
        return null;
    }
    public  Plane addPlane(String model,String manufacturer,String year_manufacturer,int capacity){
        Plane newPlane = new Plane(model,manufacturer,year_manufacturer,capacity);
        planes.add(newPlane);
        return newPlane;
    }
    public  Airport addAirport(String airport_code,String airport_name,String airport_location,int airport_runways,int airport_gates){
        Airport newAirport = new Airport(airport_code,airport_name,airport_location,airport_runways,airport_gates);
        airports.add(newAirport);
        return newAirport;
    }
    public  Flight addFlight(Airport dep_airport,Airport des_airport,String dep_time,String arrival_time,Plane plane,double ticket_price){
        Flight newFlight = new Flight(dep_airport,des_airport,dep_time,arrival_time,plane,ticket_price);
        flights.add(newFlight);
        return newFlight;
    }
}
