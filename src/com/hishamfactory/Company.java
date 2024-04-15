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
        sc.nextLine();
        System.out.print("Enter employee password: ");
        String employee_pin = sc.next();
        Employee newEmployee = new Employee(first_name,last_name,age,tel_number,address,role,employee_pin,company);
        employees.add(newEmployee);
        return  newEmployee;
    }

    public void addPassenger(Company company){
        System.out.print("Enter passenger first name: ");
        String first_name = sc.next();
        System.out.print("Enter passenger last name: ");
        String last_name = sc.next();
        System.out.print("Enter passenger age: ");
        int age = sc.nextInt();
        System.out.print("Enter passenger tel_number: ");
        String tel_number = sc.next();
        System.out.print("Enter Flight code: ");
        String flight_code = sc.next();
        System.out.print("Enter passenger password: ");
        String passenger_pin = sc.next();
        sc.nextLine();
        Passenger newPassenger = new Passenger(first_name,last_name,age,tel_number,FlightController.getFlightById(flight_code),passenger_pin,company);
        passengers.add(newPassenger);
    }
    public Employee employeeLogin(String employee_id, String employee_pin){
        for (Employee employee : Company.employees) {
            if(employee.getUuid().compareTo(employee_id) == 0 && employee.validatePin(employee_pin)) return employee;
        }
        return null;
    }
    public Passenger passengerLogin(String passenger_id,String passenger_pin){
        for (Passenger passenger : passengers) {
            if(passenger.getUuid().compareTo(passenger_id) == 0 && passenger.validatePin(passenger_pin)) return passenger;
        }
        return null;
    }
    public  void addPlane(Company company){
        System.out.print("Enter plane model: ");
        String model = sc.next();
        System.out.print("Enter plane manufacturer: ");
        String manufacturer = sc.next();
        System.out.print("Enter year of manufacturer: ");
        String year_manufacturer = sc.next();
        System.out.print("Enter capacity: ");
        int capacity = sc.nextInt();

        Plane newPlane = new Plane(model,manufacturer,year_manufacturer,capacity,company);
        planes.add(newPlane);
    }
    public  void addAirport(Company company){
        System.out.print("Enter airport_name: ");
        String airport_name = sc.next();
        System.out.print("Enter airport_location: ");
        String airport_location = sc.next();
        System.out.print("Enter airport_runways: ");
        int airport_runways = sc.nextInt();
        System.out.print("Enter airport gates: ");
        int airport_gates = sc.nextInt();


        Airport newAirport = new Airport(airport_name,airport_location,airport_runways,airport_gates,company);
        airports.add(newAirport);
    }
    public void addFlight(Company company){
        System.out.print("Enter dep airport code: ");
        Airport dep_airport = AirportController.getAirportByID(sc.next());
        System.out.print("Enter des airport: ");
        Airport des_airport = AirportController.getAirportByID(sc.next());
        System.out.print("Enter dep time: ");
        String dep_time = sc.next();
        System.out.print("Enter arrival time: ");
        String arrival_time = sc.next();
        System.out.print("Enter plane code: ");
        Plane plane = PlaneController.getPlaneById(sc.next());
        System.out.print("Enter ticket price: ");
        double ticket_price = sc.nextDouble();

        Flight newFlight = new Flight(dep_airport,des_airport,dep_time,arrival_time,plane,ticket_price,company);
        flights.add(newFlight);
    }
}
