package com.hishamfactory;

import java.util.*;

public class Company {
    private String name;
    private String uuid;
    public static ArrayList<User> users;
    public static ArrayList<Employee> employees;
    public static SuperVisor superVisor;
    public static ArrayList<Plane> planes;
    public static ArrayList<Airport> airports;
    public static ArrayList<Flight> flights;
    public static ArrayList<String> uuids;
    public static ArrayList<String> permissions_editing_uuids;
    public static ArrayList<User> administrators;
    public static ArrayList<Employee> customerServices;
    public static ArrayList<Pilot> pilots;
    public static ArrayList<Passenger> passengers;
    public static ArrayList<Coupon> coupons;

    Random rm = new Random();
    Scanner sc = new Scanner(System.in);

    /**
     * Create new company with empty employee list and so on
     * @param name  the name of the company
     */
    public Company(String name){
        this.name = name;
        users = new ArrayList<>();
        employees = new ArrayList<>();
        passengers = new ArrayList<>();
        planes = new ArrayList<>();
        airports = new ArrayList<>();
        flights = new ArrayList<>();
        uuids = new ArrayList<>();
        permissions_editing_uuids = new ArrayList<>();
        administrators = new ArrayList<>();
        customerServices = new ArrayList<>();
        pilots = new ArrayList<>();
        coupons = new ArrayList<>();
        this.uuid = getNewUUID();
        System.out.println("You are working on "+this.name + " company with ID " + this.uuid);
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
     * @param user      the user who use system right not
     */
    public void addEmployee(Company company,User user){
        Employee employee = null;
        try {
            System.out.print("Enter employee first name: ");
            String first_name = sc.next();
            sc.nextLine();
            System.out.print("Enter employee last name: ");
            String last_name = sc.next();
            sc.nextLine();
            System.out.print("Enter employee username: ");
            String user_name = sc.next();
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
            System.out.print("Enter employee basic salary: ");
            double basic_salary = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter employee password: ");
            String employee_pin = sc.next();
            sc.nextLine();
            if(superVisor == null){
                Company.superVisor =  new SuperVisor(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,employee_pin,company);
            }else {
                if (user.getClass().equals(SuperVisor.class)) {
                    System.out.println("1.Manager           2.Director ");
                    System.out.println("3.Software Engineer         4.Pilot");
                    System.out.print("Enter the choice: ");
                    int option = sc.nextInt();
                    sc.nextLine();
                    switch (option) {
                        case 1:
                            employee = new Manager(first_name, last_name,user_name, age, address, address, role,basic_salary, employee_pin, company);
                            break;
                        case 2:
                            employee = new Director(first_name, last_name,user_name,age, tel_number, address, role,basic_salary, employee_pin, company);
                            break;
                        case 3:
                            new SoftwareEngineer(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,employee_pin,company);
                            break;
                        case 4:
                            new Pilot(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,employee_pin,company);
                            break;
                    }
                }
                if (user.getClass().equals(Manager.class)) {
                    System.out.println("1.Director       2.Software Engineer         3.Pilot");
                    System.out.print("Enter the choice: ");
                    int option = sc.nextInt();
                    sc.nextLine();
                    switch (option) {
                        case 1:
                            employee = new Director(first_name, last_name,user_name, age, tel_number, address, role,basic_salary, employee_pin, company);
                            break;
                        case 2:
                            new SoftwareEngineer(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,employee_pin,company);
                            break;
                        case 3:
                            new Pilot(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,employee_pin,company);
                            break;
                    }
                }
                if (user.getClass().equals(Director.class)) {
                    System.out.println("1.Software Engineer         2.Pilot");
                    System.out.print("Enter the choice: ");
                    int option = sc.nextInt();
                    sc.nextLine();
                    switch (option) {
                        case 1:
                            new SoftwareEngineer(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,employee_pin,company);
                            break;
                        case 2:
                            new Pilot(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,employee_pin,company);
                            break;
                    }
                }
            }
        }catch(NoSuchElementException e ){
            System.out.println("Input not found. Please enter text without spaces");
            sc.nextLine();
        }
        System.out.print("has editing permission: ");
        boolean canEditing = sc.nextBoolean();
        sc.nextLine();
        if(canEditing && employee != null){
            permissions_editing_uuids.add(employee.getUuid());
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
                System.out.print("Enter passenger username: ");
                String user_name = sc.next();
                sc.nextLine();
                System.out.print("Enter passenger age: ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter passenger tel_number: ");
                String tel_number = sc.next();
                sc.nextLine();
                System.out.print("Enter passenger address: ");
                String address = sc.nextLine();
                System.out.print("Enter passenger password: ");
                String passenger_pin = sc.next();
                sc.nextLine();

                new Passenger(first_name, last_name,user_name, age, tel_number,address, passenger_pin, company);
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
                    System.out.print("Enter passenger username: ");
                    String user_name = sc.next();
                    sc.nextLine();
                    System.out.print("Enter passenger age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter passenger tel_number: ");
                    String tel_number = sc.next();
                    sc.nextLine();
                    System.out.print("Enter passenger address: ");
                    String address = sc.nextLine();
                    System.out.print("Enter passenger password: ");
                    String passenger_pin = sc.next();
                    sc.nextLine();

                    Passenger newPassenger = new Passenger(first_name, last_name,user_name, age, tel_number,address, passenger_pin, company);
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
     * @param user_name_or_id       the UUID of the employee to log in
     * @param user_pin  the password of the employee
     * @return              the employee if the log is successful, or null if it's not
     */
    public Employee employeeLoginByUserNameOrId(String user_name_or_id, String user_pin) {
        for (Employee employee: employees) {
            if ((employee.getUuid().compareTo(user_name_or_id) == 0 || employee.getUser_name().equalsIgnoreCase(user_name_or_id)) && employee.validatePin(user_pin)){
                return employee;
            }
        }
        return null;
    }

    /**
     *Get the passenger object associated with a particular UUID and pin, if they are valid
     * @param passenger_username_or_id  the UUID of the passenger to log in
     * @param passenger_pin the password of the employee
     * @return              the employee if the log is successful, or null if it's not
     */
    public Passenger passengerLoginByUserNameOrID(String passenger_username_or_id, String passenger_pin){
        for (Passenger passenger : passengers) {
            if((passenger.getUuid().compareTo(passenger_username_or_id) == 0 || passenger.getUser_name().equalsIgnoreCase(passenger_username_or_id)) && passenger.validatePin(passenger_pin)){
                return passenger;
            }
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
                    int plane_in_order= 1;
                    for (Plane plane : Company.planes) {
                        System.out.println(plane_in_order+"." +plane.toString());
                        plane_in_order++;
                    }
                    System.out.println("...........................................................................");
                        System.out.print("Enter number of plane in order or 0 to exit: ");
                        int plane_index = sc.nextInt();
                        sc.nextLine();
                        if (!(plane_index <= 0 ) && plane_index <= Company.planes.size()) {
                            Plane plane = Company.planes.get(plane_index-1);
                            System.out.println("...........................Available Pilots.........................");
                            int pilot_in_order = 1;
                            for (Pilot pilot : Company.pilots) {
                                System.out.println(pilot_in_order +"."+pilot.toString());
                                pilot_in_order++;
                            }
                            System.out.println("....................................................................");
                            System.out.print("Enter number of pilot in order or 0 to exit: ");
                            int pilot_index = sc.nextInt();
                            sc.nextLine();
                            if(!(pilot_index <= 0) && pilot_index <= Company.pilots.size()){
                                Pilot pilot = Company.pilots.get(pilot_index-1);
                                System.out.print("Enter ticket price: ");
                                double ticket_price = sc.nextDouble();
                                sc.nextLine();

                                Flight newFlight = new Flight(dep_airport, des_airport, dep_time, arrival_time, plane,pilot,company);
                                newFlight.setTicket_price(ticket_price);
                                flights.add(newFlight);
                            }else {
                                System.out.println("*** Input not found.Please enter a valid choice from list ***");
                            }
                        } else {
                            System.out.println("*** Input not found.Please enter a valid choice from list ***");
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
    public void addPilot(Company company){
        try {
            System.out.print("Enter pilot first name: ");
            String first_name = sc.next();
            sc.nextLine();

            System.out.print("Enter pilot last name: ");
            String last_name = sc.next();
            sc.nextLine();

            System.out.print("Enter pilot user name: ");
            String user_name = sc.next();
            sc.nextLine();

            System.out.print("Enter pilot age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter pilot tel number: ");
            String tel_number = sc.next();
            sc.nextLine();

            System.out.print("Enter pilot address: ");
            String address = sc.nextLine();

            System.out.print("Enter pilot role: ");
            String role = sc.nextLine();

            System.out.print("Enter pilot basic salary: ");
            double basic_salary = sc.nextDouble();
            sc.nextLine();

            System.out.print("Enter pilot password: ");
            String pilot_pin = sc.next();
            sc.nextLine();

            new Pilot(first_name, last_name,user_name, age, tel_number, address, role, basic_salary, pilot_pin, company);
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
}
