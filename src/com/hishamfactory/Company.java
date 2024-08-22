package com.hishamfactory;

import java.io.*;
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
    public static ArrayList<LoginHistory> logs;

    Random rm = new Random();
    Scanner sc = new Scanner(System.in);

    /**
     * Create new company with empty employee list and so on
     * @param name  the name of the company
     */
    public Company(String name)throws IOException {
        this.name = name;
        String line;
        String[] tokens;
        users = new ArrayList<>();
        uuids = new ArrayList<>();
        permissions_editing_uuids = new ArrayList<>();
        administrators = new ArrayList<>();
        customerServices = new ArrayList<>();
        employees = new ArrayList<>();
        pilots = new ArrayList<>();
        FileReader employeesFile = new FileReader("DataFiles/employees");
        try(BufferedReader reader = new BufferedReader(employeesFile)){
            while((line = reader.readLine())!= null){
                tokens = line.split(";");
                String[] hashedPasswordString = tokens[6].split(", ");
                byte[] hashedPasswordByte = new byte[hashedPasswordString.length];
                for (int i = 0; i < hashedPasswordString.length; i++) {
                    hashedPasswordByte[i] = Byte.parseByte(hashedPasswordString[i]);
                }
                if(tokens[7].equalsIgnoreCase("SuperVisor")) {
                    superVisor = new SuperVisor(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4], tokens[5], hashedPasswordByte,  tokens[7],Double.parseDouble(tokens[8]), getCompany());
                    employees.add(superVisor);
                    users.add(superVisor);
                }
                else if(tokens[7].equals("Manager")){
                    Manager manager = new Manager(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4], tokens[5], hashedPasswordByte, tokens[7], Double.parseDouble(tokens[8]), getCompany());
                    employees.add(manager);
                    users.add(manager);
                }else if(tokens[7].equals("Director")){
                    Director director = new Director(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4], tokens[5], hashedPasswordByte, tokens[7], Double.parseDouble(tokens[8]), getCompany());
                    employees.add(director);
                    users.add(director);
                }else if(tokens[7].equals("Software Engineer")){
                    SoftwareEngineer softwareEngineer = new SoftwareEngineer(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4],tokens[5],hashedPasswordByte, tokens[7],Double.parseDouble(tokens[8]),  getCompany());
                    employees.add(softwareEngineer);
                    users.add(softwareEngineer);
                }else if(tokens[7].equals("Pilot")){
                    Pilot pilot = new Pilot(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4], tokens[5],hashedPasswordByte, tokens[7],Double.parseDouble(tokens[8]), getCompany());
                    pilots.add(pilot);
                    employees.add(pilot);
                    users.add(pilot);
                }
            }
        }
        passengers = new ArrayList<>();
        FileReader usersFile = new FileReader("DataFiles/passengers");
        try(BufferedReader reader = new BufferedReader(usersFile)) {
            while ((line = reader.readLine()) != null) {
                tokens = line.split(";");
                String[] hashedPasswordString = tokens[6].split(", ");
                byte[] hashedPasswordByte = new byte[hashedPasswordString.length];
                for (int i = 0; i < hashedPasswordString.length; i++) {
                    hashedPasswordByte[i] = Byte.parseByte(hashedPasswordString[i]);
                }
                Passenger passenger = new Passenger(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4], tokens[5], hashedPasswordByte, getCompany());
                passengers.add(passenger);
            }
        }
        planes = new ArrayList<>();
        FileReader planesFile = new FileReader("DataFiles/planes");
        try(BufferedReader reader = new BufferedReader(planesFile)){
            while ((line = reader.readLine()) != null) {
                tokens = line.split(";");
                Plane plane = new Plane(tokens[0], tokens[1], tokens[2],tokens[3], Integer.parseInt(tokens[4]), getCompany());
                planes.add(plane);
            }
        }
        airports = new ArrayList<>();
        FileReader airportsFile = new FileReader("DataFiles/airports");
        try(BufferedReader reader = new BufferedReader(airportsFile)){
            while((line = reader.readLine()) != null){
                tokens = line.split(";");
                Airport airport = new Airport(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), getCompany());
                airports.add(airport);
            }
        }
        flights = new ArrayList<>();
        FileReader flightsFile = new FileReader("DataFiles/flights");
        try(BufferedReader reader = new BufferedReader(flightsFile)){
            while((line = reader.readLine()) != null){
                tokens = line.split(";");
                String[] flightSeatsStringToArray = tokens[7].split(",");
                String[] flightPassengersStringToStringArray =  tokens[8].split(",");
                ArrayList<Passenger> flightPassengers = new ArrayList<>();
                for (String passengerUserName : flightPassengersStringToStringArray) {
                    Passenger passenger = PassengersController.getPassengerByUserName(passengerUserName);
                    if(passenger != null){
                        flightPassengers.add(passenger);
                    }

                }
                Flight flight = new Flight(tokens[0],AirportController.getAirportByName(tokens[1]), AirportController.getAirportByName(tokens[2]),tokens[3],tokens[4],PlaneController.getPlaneBySerialNumber(tokens[5]),PilotsController.getPilotByID(tokens[6]),flightSeatsStringToArray,flightPassengers);
                flights.add(flight);
            }
        }
        coupons = new ArrayList<>();
        FileReader couponsFile = new FileReader("DataFiles/coupons");
        try(BufferedReader reader = new BufferedReader(couponsFile)){
            while((line = reader.readLine()) != null){
                tokens = line.split(";");
                Coupon coupon = new Coupon(tokens[0],Integer.parseInt(tokens[1]),getCompany());
                coupons.add(coupon);
            }
        }
        logs = new ArrayList<>();
        FileReader logsFile = new FileReader("DataFiles/logs");
        try(BufferedReader reader = new BufferedReader(logsFile)){
            while ((line = reader.readLine()) != null){
                tokens = line.split(";");
                LoginHistory log =new LoginHistory(tokens[0],UserController.getUserByUserName(tokens[1]));
                logs.add(log);
            }
        }
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
                            employee = new Manager(first_name, last_name,user_name, age, tel_number, address, role,basic_salary, employee_pin, company);
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
        }catch(InputMismatchException e ){
            System.out.println("*** Your input mismatch whats excepted, please enter valid input ***");
            sc.nextLine();
        }
    }

    /**
     * Add new passenger into system database
     * @param company   the company who has the system
     */
    public void addPassenger(Company company){
        boolean flag = true;
        String first_name;
        String last_name;
            while(flag) {
                System.out.println(".................Create New Passenger...................");
                System.out.print("Enter passenger first name: ");
                try {
                first_name = sc.next();
                sc.nextLine();
                System.out.print("Enter passenger last name: ");
                last_name = sc.next();
                sc.nextLine();
                flag = false;
                System.out.print("Enter passenger username: ");
                String user_name = sc.next();
                sc.nextLine();
                if (PassengersController.checkPassengerNotExist(user_name)) {
                    System.out.println("****This user_name had already created****");
                    continue;
                }
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
            }catch (InputMismatchException e){
                    System.out.println("*** Your input mismatch whats excepted, please enter valid input ***");
                    sc.nextLine();
                }
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
                    if (PassengersController.checkPassengerNotExist(first_name + last_name)) {
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
                    flight.getPassengers().add(newPassenger);

                }
            } catch (InputMismatchException e) {
                System.out.println("*** Your input mismatch whats excepted, please enter valid input ***");
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
            if ((employee.getUuid().compareTo(user_name_or_id) == 0 || employee.getUser_name().equalsIgnoreCase(user_name_or_id)) && employee.validatePinByUserPin(user_pin)){
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
            if((passenger.getUuid().compareTo(passenger_username_or_id) == 0 || passenger.getUser_name().equalsIgnoreCase(passenger_username_or_id)) && passenger.validatePinByUserPin(passenger_pin)){
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
            System.out.print("Enter plane model: ");
            String model = sc.next();
            sc.nextLine();
            System.out.print("Enter plane serial number: ");
            String serial_number = sc.next();
            sc.nextLine();
            System.out.print("Enter plane manufacturer: ");
            String manufacturer = sc.nextLine();
            System.out.print("Enter year of manufacturer: ");
            String year_manufacturer = sc.next();
            System.out.print("Enter capacity: ");
            int capacity = sc.nextInt();
            sc.nextLine();

            Plane newPlane = new Plane(model,serial_number, manufacturer, year_manufacturer, capacity, company);
            planes.add(newPlane);
    }

    /**
     * Add new airport to the system of company
     * @param company   the company who has the system
     */
    public void addAirport(Company company){
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
    }

    /**
     * Add new flight to the system of company
     * @param company   the company who has the system
     */
    public void addFlight(Company company){
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
                                System.out.print("Enter number of seats of flight: ");
                                int flight_seats = sc.nextInt();
                                sc.nextLine();

                                Flight newFlight = new Flight(dep_airport, des_airport, dep_time, arrival_time, plane,pilot,flight_seats,company);
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
    }
    public void addPilot(Company company){
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
    }
    public void CreateCoupon(Company company){
            System.out.print("Enter coupon code: ");
            String coupon_code = sc.next();
            sc.nextLine();
            System.out.print("Enter coupon in percentage: ");
            int coupon_in_percentage = sc.nextInt();
            sc.nextLine();

            Coupon coupon = new Coupon(coupon_code, coupon_in_percentage,company);
            Company.coupons.add(coupon);
    }
    public Company getCompany(){
        return this;
    }
    public void storeCopiesFromData() throws IOException{
        File usersFile = new File("DataFiles/users");
        try(FileWriter fileWriter = new FileWriter(usersFile)){
            for (User user : users) {
                String hashedPasswordString = Arrays.toString(user.getPinHash());
                fileWriter.write(user.getFirst_name()+";"+user.getLast_name()+";"+user.getUser_name()+";"+user.getAge()+";"+user.getTel_number()+";"+user.getAddress()+";"+hashedPasswordString.substring(1,hashedPasswordString.length()-1)+";"+user.getRole()+"\n");
            }
        }
        File employeesFile = new File("DataFiles/employees");
        try(FileWriter fileWriter = new FileWriter(employeesFile)){
            for (Employee employee : employees) {
                String hashedPasswordString = Arrays.toString(employee.getPinHash());
                fileWriter.write(employee.getFirst_name()+";"+employee.getLast_name()+";"+employee.getUser_name()+";"+employee.getAge()+";"+employee.getTel_number()+";"+employee.getAddress()+";"+hashedPasswordString.substring(1,hashedPasswordString.length()-1)+";"+employee.getRole()+";"+employee.getBasic_salary()+"\n");
            }
        }
        File passengersFile = new File("DataFiles/passengers");
        try(FileWriter fileWriter = new FileWriter(passengersFile)){
            for (Passenger passenger : passengers) {
                String hashedPasswordString = Arrays.toString(passenger.getPinHash());
                fileWriter.write(passenger.getFirst_name()+";"+passenger.getLast_name()+";"+passenger.getUser_name()+";"+passenger.getAge()+";"+passenger.getTel_number()+";"+passenger.getAddress()+";"+hashedPasswordString.substring(1,hashedPasswordString.length()-1)+";"+passenger.getRole()+"\n");
            }
        }
        File planesFile = new File("DataFiles/planes");
        try(FileWriter fileWriter = new FileWriter(planesFile)){
            for (Plane plane: planes) {
                fileWriter.write(plane.getPlane_model() +";"+plane.getSerial_number()+";"+plane.getPlane_manufacturer()+";"+plane.getPlane_year()+";"+plane.getPlane_capacity()+"\n");
            }
        }
        File flightsFile = new File("DataFiles/flights");
        try(FileWriter fileWriter = new FileWriter(flightsFile)){
            for (Flight flight : flights) {
                ArrayList<Passenger> flightPassengers = flight.getPassengers();
                String[] flightPassengersToStringArray = new String[flightPassengers.size()];
                for (int i = 0; i < flightPassengers.size(); i++) {
                    flightPassengersToStringArray[i] = flightPassengers.get(i).getUser_name();
                }
                String flightPassengersToString = Arrays.toString(flightPassengersToStringArray);
                String flightSeatsArrayToString = Arrays.toString(flight.getFlight_seats());
                fileWriter.write(flight.getFlight_code()+";"+flight.getDeparture_airport().getAirport_name()+";"+flight.getDestination_airport().getAirport_name()+";"+flight.getDeparture_time()+";"+flight.getArrival_time()+";"+flight.getPlane().getSerial_number()+";"+flight.getFlight_captain().getUuid()+";"+ flightSeatsArrayToString.substring(1,flightSeatsArrayToString.length()-1) +";"+flightPassengersToString.substring(1,flightSeatsArrayToString.length()-1)+"\n");
            }
        }
        File airportsFile = new File("DataFiles/airports");
        try(FileWriter fileWriter = new FileWriter(airportsFile)){
            for (Airport airport : airports) {
                fileWriter.write(airport.getAirport_name()+";"+airport.getAirport_location()+";"+airport.getAirport_number_gates()+";"+airport.getAirport_number_runways()+"\n");
            }
        }
        File couponsFile = new File("DataFiles/coupons");
        try(FileWriter fileWriter = new FileWriter(couponsFile)){
            for (Coupon coupon : coupons) {
                fileWriter.write(coupon.getCoupon_code()+";"+coupon.getCoupon_in_percentage()+"\n");
            }
        }
        File logsFile = new File("DataFiles/logs");
        try(FileWriter fileWriter = new FileWriter(logsFile)){
            for (LoginHistory log : logs) {
                fileWriter.write(log.getDate_and_time()+";"+log.getUser().getUser_name()+"\n");
            }
        }
    }
}
