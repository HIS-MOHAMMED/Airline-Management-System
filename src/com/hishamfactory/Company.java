package com.hishamfactory;

import java.util.*;

public class Company {
    private String name;
    public static ArrayList<Employee> employees;
    public static ArrayList<Passenger> passengers;
    public static ArrayList<Plane> planes;
    public static ArrayList<Airport> airports;
    public static ArrayList<Flight> flights;
    public static ArrayList<String> uuids;
    public static ArrayList<String> permissions_uuids;
    private String uuid;
    public static ArrayList<Employee> administrators;
    public static ArrayList<Employee> customerServices;

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
        permissions_uuids = new ArrayList<>();
        administrators = new ArrayList<>();
        customerServices = new ArrayList<>();
        System.out.println("You are working on "+this.name + " company");
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
        this.uuid = uuid;
        return uuid;
    }

    public void addEmployee(Company company){
        try {
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
                boolean  isAdmin = setUserRules();
                boolean permission_access = false;
                Employee newEmployee;
                if(Company.administrators.isEmpty()){
                    System.out.print("Has editing permissions: ");
                    permission_access = sc.nextBoolean();
                    newEmployee = new Employee(first_name, last_name, age, tel_number, address, role, employee_pin,isAdmin, company);
                }else{
                    newEmployee = new Employee(first_name, last_name, age, tel_number, address, role, employee_pin, company);
                }
                if(isAdmin){
                    Company.administrators.add(newEmployee);
                    hasPermission(permission_access,newEmployee);
                    employees.add(newEmployee);
                }else if(!Company.administrators.isEmpty()){
                    Company.customerServices.add(newEmployee);
                    employees.add(newEmployee);
                }

        }catch(NoSuchElementException e ){
            System.out.println("Input not found. Please enter text without spaces");
            sc.next();
        }
    }
    public void addPassenger(Company company){
        boolean flag = true;
        String first_name;
        String last_name;
        try {
            while(flag) {
                System.out.print("Enter passenger first name: ");
                first_name = sc.next();
                System.out.print("Enter passenger last name: ");
                last_name = sc.next();
                if (!PassengersController.checkPassengerNotExist(first_name + " " + last_name)) {
                    System.out.println("****This name had already created****");
                    continue;
                }
                flag = false;
                System.out.print("Enter passenger age: ");
                int age = sc.nextInt();
                System.out.print("Enter passenger tel_number: ");
                String tel_number = sc.next();
                System.out.print("Enter Flight code: ");
                String flight_code = sc.next();
                System.out.print("Enter passenger password: ");
                String passenger_pin = sc.next();
                sc.nextLine();
                System.out.print("has editing permissions: ");
                boolean permission_access = sc.nextBoolean();
                hasPermission(permission_access);

                Passenger newPassenger = new Passenger(first_name, last_name, age, tel_number, FlightController.getFlightById(flight_code), passenger_pin, company);
                passengers.add(newPassenger);
            }
        }catch(NoSuchElementException e){
            System.out.println("Input not found. Please enter text without spaces");
            sc.next();
        }catch (NullPointerException e){
            System.out.println("*** The Flight code is wrong ***");
        }
    }
    public void addPassenger(Company company,Flight flight){
        boolean flag = true;
        String first_name;
        String last_name;
        try {
            while(flag) {
                System.out.print("Enter passenger first name: ");
                first_name = sc.next();
                System.out.print("Enter passenger last name: ");
                last_name = sc.next();
                if (!PassengersController.checkPassengerNotExist(first_name + " " + last_name)) {
                    System.out.println("****This name had already created****");
                    continue;
                }
                flag = false;
                System.out.print("Enter passenger age: ");
                int age = sc.nextInt();
                System.out.print("Enter passenger tel_number: ");
                String tel_number = sc.next();
                System.out.print("Enter passenger password: ");
                String passenger_pin = sc.next();
                sc.nextLine();
                System.out.print("has editing permissions: ");
                boolean permission_access = sc.nextBoolean();
                hasPermission(permission_access);

                Passenger newPassenger = new Passenger(first_name, last_name, age, tel_number, flight, passenger_pin, company);
                passengers.add(newPassenger);
            }
        }catch(NoSuchElementException e){
            System.out.println("Input not found. Please enter text without spaces");
            sc.next();
        }catch (NullPointerException e){
            System.out.println("*** The Flight code is wrong ***");
        }
    }
    public Employee employeeLogin(String employee_id, String employee_pin) {
        for (Employee employee : Company.employees) {
            if (employee.getUuid().compareTo(employee_id) == 0 && employee.validatePin(employee_pin)) return employee;
        }
        return null;
    }
    public Passenger passengerLogin(String passenger_id,String passenger_pin){
        for (Passenger passenger : passengers) {
            if(passenger.getUuid().compareTo(passenger_id) == 0 && passenger.validatePin(passenger_pin)) return passenger;
        }
        return null;
    }
    public void addPlane(Company company){
        try {
            System.out.print("Enter plane model: ");
            String model = sc.next();
            System.out.print("Enter plane manufacturer: ");
            String manufacturer = sc.next();
            System.out.print("Enter year of manufacturer: ");
            String year_manufacturer = sc.next();
            System.out.print("Enter capacity: ");
            int capacity = sc.nextInt();

            Plane newPlane = new Plane(model, manufacturer, year_manufacturer, capacity, company);
            planes.add(newPlane);
        }catch (InputMismatchException e){
            System.out.println("*** Please enter capacity as integer ***");
            sc.next();
        }
    }
    public void addAirport(Company company){
        try {
            System.out.print("Enter airport_name: ");
            String airport_name = sc.next();
            System.out.print("Enter airport_location: ");
            String airport_location = sc.next();
            System.out.print("Enter airport_runways: ");
            int airport_runways = sc.nextInt();
            System.out.print("Enter airport gates: ");
            int airport_gates = sc.nextInt();


            Airport newAirport = new Airport(airport_name, airport_location, airport_runways, airport_gates, company);
            airports.add(newAirport);
        }catch (NoSuchElementException e){
            System.out.println("Input not found. Please enter text without spaces");
        }
    }
    public void addFlight(Company company){
        try {
            System.out.println("..........................Available Airports..............................");
            for (Airport airport : Company.airports) {
                System.out.println(airport.toString());
            }
            System.out.println("..........................................................................");
            System.out.print("Enter dep airport code: ");
            Airport dep_airport = AirportController.getAirportByID(sc.next());
            if(dep_airport != null) {
                System.out.print("Enter des airport: ");
                Airport des_airport = AirportController.getAirportByID(sc.next());
                if(des_airport != null) {
                    System.out.print("Enter dep time: ");
                    String dep_time = sc.next();
                    System.out.print("Enter arrival time: ");
                    String arrival_time = sc.next();
                    System.out.println("............................Available Planes................................");
                    for (Plane plane : Company.planes) {
                        System.out.println(plane.toString());
                    }
                    System.out.println("...........................................................................");
                    System.out.print("Enter plane code: ");
                    Plane plane = PlaneController.getPlaneById(sc.next());
                    if(plane != null) {
                        System.out.print("Enter ticket price: ");
                        double ticket_price = sc.nextDouble();

                        Flight newFlight = new Flight(dep_airport, des_airport, dep_time, arrival_time, plane, ticket_price, company);
                        flights.add(newFlight);
                    }else{
                        System.out.println("*** This flight not exits ***");
                    }
                }else{
                    System.out.println("*** The des airport not exits ***");
                }
            }else {
                System.out.println("*** The dep airport not exits ***");
            }
        }catch (NoSuchElementException e){
            System.out.println("Input not found. Please enter text without spaces");
        }
    }
    public void hasPermission(boolean permission_access,Employee employee){
        if(permission_access) {
            permissions_uuids.add(employee.getUuid());
        }
    }
    public void hasPermission(boolean permission_access){
        if(permission_access) {
            permissions_uuids.add(this.uuid);
        }
    }
    public boolean setUserRules(){
        while(true){
            System.out.print("Is 1.Administrator or 2.CostumerService: ");
            int choice = sc.nextInt();
            if(choice == 1){
                return true;
            }else if(choice == 2){
                return false;
            }else{
                System.out.println("*** Please enter a valid choice ***");
                sc.next();
            }
        }
    }
}
