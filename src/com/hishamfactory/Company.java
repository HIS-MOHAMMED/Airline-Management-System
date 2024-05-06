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
    public static ArrayList<Coupon> coupons;

    Random rm = new Random();
    Scanner sc = new Scanner(System.in);

    /**
     * Create new company with empty employee list and so on
     * @param name  the name of the company
     */
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
        coupons = new ArrayList<>();
        System.out.println("You are working on "+this.name + " company");
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    /**
     *Generate new universally unique ID for any object on company
     * @return  the uuid
     */
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

    /**
     * Add new employee to the system
     * @param company   the company who new employee works for
     */
    public void addEmployee(Company company){
        try {
                System.out.print("Enter employee first name: ");
                String first_name = sc.next();
                sc.nextLine();
                System.out.print("Enter employee last name: ");
                String last_name = sc.next();
                sc.nextLine();
                System.out.print("Enter employee age: ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter employee tel_number: ");
                String tel_number = sc.next();
                sc.nextLine();
                System.out.print("Enter employee address: ");
                String address = sc.nextLine();
                System.out.print("Enter employee role: ");
                String role = sc.nextLine();
                System.out.print("Enter employee password: ");
                String employee_pin = sc.next();
                sc.nextLine();
                boolean  isAdmin = setUserRules();
                boolean permission_access = false;
                Employee newEmployee;
                if(isAdmin){
                    System.out.print("Has editing permissions: ");
                    permission_access = sc.nextBoolean();
                    sc.nextLine();
                    newEmployee = new Employee(first_name, last_name, age, tel_number, address, role, employee_pin,isAdmin, company);
                    Company.administrators.add(newEmployee);
                    hasPermission(permission_access,newEmployee);
                    employees.add(newEmployee);
                }else if(!Company.administrators.isEmpty()){
                    newEmployee = new Employee(first_name, last_name, age, tel_number, address, role, employee_pin, company);
                    Company.customerServices.add(newEmployee);
                    employees.add(newEmployee);
                }

        }catch(NoSuchElementException e ){
            System.out.println("Input not found. Please enter text without spaces");
            sc.nextLine();
        }
    }

    /**
     * Add new passenger manually by employee
     * @param company   the company who has the system
     */
    public void addPassenger(Company company){
        boolean flag = true;
        String first_name;
        String last_name;
        try {
            while(flag) {
                System.out.println(".................Create New Passenger...................");
                System.out.print("Enter passenger first name: ");
                first_name = sc.next();
                sc.nextLine();
                System.out.print("Enter passenger last name: ");
                last_name = sc.next();
                sc.nextLine();
                if (!PassengersController.checkPassengerNotExist(first_name + " " + last_name)) {
                    System.out.println("****This name had already created****");
                    continue;
                }
                flag = false;
                System.out.print("Enter passenger age: ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter passenger tel_number: ");
                String tel_number = sc.next();
                sc.nextLine();
                System.out.print("Enter passenger password: ");
                String passenger_pin = sc.next();
                sc.nextLine();

                Passenger newPassenger = new Passenger(first_name, last_name, age, tel_number, passenger_pin, company);
                passengers.add(newPassenger);
            }
        }catch(NoSuchElementException e){
            System.out.println("Input not found. Please enter text without spaces");
            sc.nextLine();
        }catch (NullPointerException e){
            System.out.println("*** The Flight code is wrong ***");
            sc.nextLine();
        }
    }

    /**
     * Add passenger on the specific flight
     * @param company   the company who has this system
     * @param flight    the flight who add to it the new passenger
     */
    public void addPassenger(Company company,Flight flight){
        if(flight.hasAvailableSeat(flight)) {
            boolean flag = true;
            String first_name;
            String last_name;
            try {
                while (flag) {
                    System.out.print("Enter passenger first name: ");
                    first_name = sc.next();
                    sc.nextLine();
                    System.out.print("Enter passenger last name: ");
                    last_name = sc.next();
                    sc.nextLine();
                    if (!PassengersController.checkPassengerNotExist(first_name + " " + last_name)) {
                        System.out.println("****This name had already created****");
                        continue;
                    }
                    flag = false;
                    System.out.print("Enter passenger age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter passenger tel_number: ");
                    String tel_number = sc.next();
                    sc.nextLine();
                    System.out.print("Enter passenger password: ");
                    String passenger_pin = sc.next();
                    sc.nextLine();

                    Passenger newPassenger = new Passenger(first_name, last_name, age, tel_number, passenger_pin, company);
                    passengers.add(newPassenger);
                    flight.passengers.add(newPassenger);

                }
            } catch (NoSuchElementException e) {
                System.out.println("Input not found. Please enter text without spaces");
                sc.nextLine();
            } catch (NullPointerException e) {
                System.out.println("*** The Flight code is wrong ***");
                sc.nextLine();
            }
        }else{
            System.out.println("*** Sorry this flight is full ***");
        }
    }

    /**
     * Get the employee object associated with a particular UUID and pin, if they are valid
     * @param employee_id   the UUID of the employee to log in
     * @param employee_pin  the password of the employee
     * @return              the employee if the log is successful, or null if it's not
     */
    public Employee employeeLogin(String employee_id, String employee_pin) {
        for (Employee employee : Company.employees) {
            if (employee.getUuid().compareTo(employee_id) == 0 && employee.validatePin(employee_pin)) return employee;
        }
        return null;
    }

    /**
     *Get the passenger object associated with a particular UUID and pin, if they are valid
     * @param passenger_id  the UUID of the passenger to log in
     * @param passenger_pin the password of the employee
     * @return              the employee if the log is successful, or null if it's not
     */
    public Passenger passengerLogin(String passenger_id,String passenger_pin){
        for (Passenger passenger : passengers) {
            if(passenger.getUuid().compareTo(passenger_id) == 0 && passenger.validatePin(passenger_pin)) return passenger;
        }
        return null;
    }

    /**
     * Add new plane to the company's plane fleet
     * @param company   the company who has the system
     */
    public void addPlane(Company company){
        try {
            System.out.print("Enter plane model: ");
            String model = sc.next();
            sc.nextLine();
            System.out.print("Enter plane manufacturer: ");
            String manufacturer = sc.nextLine();
            System.out.print("Enter year of manufacturer: ");
            String year_manufacturer = sc.next();
            System.out.print("Enter capacity: ");
            int capacity = sc.nextInt();
            sc.nextLine();

            Plane newPlane = new Plane(model, manufacturer, year_manufacturer, capacity, company);
            planes.add(newPlane);
        }catch (InputMismatchException e){
            System.out.println("*** Please enter capacity as integer ***");
            sc.nextLine();
        }
    }

    /**
     * Add new airport to the system of company
     * @param company   the company who has the system
     */
    public void addAirport(Company company){
        try {
            System.out.print("Enter airport_name: ");
            String airport_name = sc.next();
            sc.nextLine();
            System.out.print("Enter airport_location: ");
            String airport_location = sc.nextLine();
            System.out.print("Enter airport_runways: ");
            int airport_runways = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter airport gates: ");
            int airport_gates = sc.nextInt();
            sc.nextLine();

            Airport newAirport = new Airport(airport_name, airport_location, airport_runways, airport_gates, company);
            airports.add(newAirport);
        }catch (NoSuchElementException e){
            System.out.println("Input not found. Please enter text without spaces");
            sc.nextLine();
        }
    }

    /**
     * Add new flight to the system of company
     * @param company   the company who has the system
     */
    public void addFlight(Company company){
        try {
            System.out.println("..........................Available Airports..............................");
            for (Airport airport : Company.airports) {
                System.out.println(airport.toString());
            }
            System.out.println("..........................................................................");
            System.out.print("Enter departure airport name: ");
            Airport dep_airport = AirportController.getAirportByName(sc.next());
            sc.nextLine();
            if(dep_airport != null) {
                System.out.print("Enter destination airport name: ");
                Airport des_airport = AirportController.getAirportByName(sc.next());
                sc.nextLine();
                if(des_airport != null) {
                    System.out.print("Enter dep time: ");
                    String dep_time = sc.next();
                    sc.nextLine();
                    System.out.print("Enter arrival time: ");
                    String arrival_time = sc.next();
                    sc.nextLine();
                    System.out.println("............................Available Planes................................");
                    for (Plane plane : Company.planes) {
                        System.out.println(plane.toString());
                    }
                    System.out.println("...........................................................................");
                    System.out.print("Enter plane code: ");
                    Plane plane = PlaneController.getPlaneById(sc.next());
                    sc.nextLine();
                    if(plane != null) {
                        System.out.print("Enter ticket price: ");
                        double ticket_price = sc.nextDouble();
                        sc.nextLine();

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
            sc.nextLine();
        }
    }
    public void CreateCoupon(Company company){
        try {
            System.out.print("Enter coupon code: ");
            String coupon_code = sc.next();
            sc.nextLine();
            System.out.print("Enter coupon in percentage: ");
            int coupon_in_percentage = sc.nextInt();
            sc.nextLine();

            Coupon coupon = new Coupon(coupon_code, coupon_in_percentage);
            Company.coupons.add(coupon);
        }catch (NoSuchElementException e){
            System.out.println("Input not found.Please enter text without spaces");
            sc.nextLine();
        }
    }

    /**
     * Check if the passed employee has permission to  whole system or nto
     * @param permission_access     the employee has permission or not
     * @param employee              the employee who used the system currently
     */
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

    /**
     * Set roll of the employee is admin or normal employee
     * @return      it is admin or not
     */
    public boolean setUserRules(){
        while(true){
            System.out.print("Is 1.Administrator or 2.CostumerService: ");
            int choice = sc.nextInt();
            sc.nextLine();
            if(choice == 1){
                return true;
            }else if(choice == 2){
                return false;
            }else{
                System.out.println("*** Please enter a valid choice ***");
                sc.nextLine();
            }
        }
    }
}
