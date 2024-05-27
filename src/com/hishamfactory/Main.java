package com.hishamfactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Company company = new Company("Turkish Airline");
	    boolean isNew = true;
        do {
            System.out.println("Welcome to Airline System Management");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            if(Company.superVisor == null) {
                while (isNew) {
                    System.out.println("**As first employee you must to create SuperVisor role**");
                    company.addEmployee(company, null);
                    if (Company.superVisor != null) {
                        isNew = false;
                    }
                }
            }else{
                showLoginMenu(company, sc);
            }
        } while (true);
    }

    /**
     * Show login menu to check has access to use system or not
     * @param company   the company who has the system
     * @param sc        the scanner object to user input
     */
    public static void showLoginMenu(Company company, Scanner sc) {
        User isAuthenticate = null;
        boolean flag;
        do {
            try {
                flag = true;
                int user;
                String user_name_or_id;
                String user_pin;
                System.out.println(".................User Type............................");
                System.out.println("1.Employee                  2.Passenger");
                System.out.print("Enter a choice: ");
                user = sc.nextInt();
                sc.nextLine();
                if (user == 1) {
                    System.out.println("................Login Menu............................");
                    System.out.print("Enter  ID or Username: ");
                    user_name_or_id = sc.next();
                    sc.nextLine();
                    System.out.print("Enter  password: ");
                    user_pin = sc.next();
                    sc.nextLine();
                    isAuthenticate = company.employeeLoginByUserNameOrId(user_name_or_id, user_pin);
                    if (isAuthenticate == null) {
                        System.out.println("ID,Username or password incorrect.please try again");
                    } else {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh-mm-ss");
                        String date = simpleDateFormat.format(new Date());
                        new LoginHistory(date, isAuthenticate);
                        if(isAuthenticate.isNewMessages) {
                            System.out.println("........................New Messages.....................");
                            for (String message : isAuthenticate.messages) {
                                System.out.println(message);
                            }
                            isAuthenticate.messages = null;
                            isAuthenticate.isNewMessages = false;
                        }
                        while (flag) {
                            flag = showHomeMenu(company, isAuthenticate, sc);
                        }
                    }
                } else if (user == 2) {
                    while (true) {
                        System.out.println(".................Welcome Menu..........................");
                        System.out.println("1.Create new account            2.Login");
                        System.out.print("Enter a choice: ");
                        int option = sc.nextInt();
                        if (option == 1) {
                            company.addPassenger(company);
                        } else if (option == 2) {
                            break;
                        } else {
                            System.out.println("** Please enter a valid choice ***");
                        }
                    }
                    System.out.println(".....................Login Menu.......................");
                    System.out.print("Enter  ID: ");
                    user_name_or_id = sc.next();
                    sc.nextLine();
                    System.out.print("Enter  password: ");
                    user_pin = sc.next();
                    sc.nextLine();
                    isAuthenticate = company.passengerLoginByUserNameOrID(user_name_or_id, user_pin);
                    if (isAuthenticate == null) {
                        System.out.println("ID,Username or password incorrect.please try again");
                    } else {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh-mm-ss");
                        String date = simpleDateFormat.format(new Date());
                        new LoginHistory(date, isAuthenticate);
                        if(isAuthenticate.isNewMessages) {
                            System.out.println("........................New Messages.....................");
                            for (String message : isAuthenticate.messages) {
                                System.out.println(message);
                            }
                            isAuthenticate.messages = null;
                            isAuthenticate.isNewMessages= false;
                        }
                        while (flag) {
                            PassengersController controller = new PassengersController();
                            flag = controller.showPassengerMenu((Passenger) isAuthenticate);
                        }
                    }
                } else {
                    System.out.println("Answer not found. Please enter only 1(employees) or 2(passengers)");
                }
            } catch (InputMismatchException e) {
                System.out.println("*** Invalid Input. Please enter valid integer input ***");
                sc.next();
            } catch (NoSuchElementException e) {
                System.out.println("*** Input not found. Please enter text without spaces ***");
            }
        } while (isAuthenticate == null);
    }

    /**
     * Show home menu to user to do operation
     * @param company   the company who has  the system
     * @param user    the person who do the operation
     * @param sc        the scanner object to input user
     * @return          the boolean value to stay login or log out
     */
    public static boolean showHomeMenu(Company company, User user, Scanner sc) {
        boolean flag = true;
        boolean inner_flag1 = true;
        boolean inner_flag2 = true;
        int option = 1;
        try {
            if (user.getClass().equals(SuperVisor.class)) {
                while (inner_flag1) {
                    System.out.println("\n..................Home Menu..............................");
                    System.out.println(user.getRole() + " " + user.getFirst_name() + ", " + user.getLast_name() + ".Welcome to " + company.getName());
                    System.out.println("1.Employees");
                    System.out.println("2.Airports");
                    System.out.println("3.Planes");
                    System.out.println("4.Flights");
                    System.out.println("5.Passengers");
                    System.out.println("6.Pilots");
                    System.out.println("7.Create Coupon");
                    System.out.println("8.Show login history");
                    System.out.println("9.Quit");
                    System.out.print("Enter a choice: ");
                    option = sc.nextInt();
                    sc.nextLine();
                    if (option >= 1 && option <= 9) {
                        inner_flag1 = false;
                    } else {
                        System.out.println("Just form 1 to 6 you can choose");
                    }
                }
                switch (option) {
                    case 1:
                        EmployeesController controller = new EmployeesController();
                        while (inner_flag2) {
                            inner_flag2 = controller.showEmployeeMenu(company, controller, user);
                        }
                        break;
                    case 2:
                        AirportController controller1 = new AirportController();
                        while (inner_flag2) {
                            inner_flag2 = controller1.showAirportMenu(company, controller1);
                        }
                        break;
                    case 3:
                        if (Company.airports.isEmpty()) {
                            System.out.println("**You must create airport before**");
                            break;
                        }
                        PlaneController controller2 = new PlaneController();
                        while (inner_flag2) {
                            inner_flag2 = controller2.showPlaneMenu(company, controller2);
                        }
                        break;
                    case 4:
                        if (Company.planes.isEmpty()) {
                            System.out.println("**You must create plane before**");
                            break;
                        }
                        FlightController controller3 = new FlightController();
                        while (inner_flag2) {
                            inner_flag2 = controller3.showFlightMenu(company, controller3, user);
                        }
                        System.out.println("Flag is: " + flag);
                        break;
                    case 5:
                        if (Company.flights.isEmpty()) {
                            System.out.println("**You must create flight before**");
                            break;
                        }
                        PassengersController controller4 = new PassengersController();
                        while (inner_flag2) {
                            inner_flag2 = controller4.showPassengerMenu(company, controller4);
                        }
                        break;
                    case 6:
                        PilotsController controller5 = new PilotsController();
                        while(inner_flag2){
                            inner_flag2 = controller5.showPilotMenu(company);
                        }
                        break;
                    case 7:
                        company.CreateCoupon(company);
                        break;
                    case 8:
                        new LoginHistory().printLoginHistory();
                        break;
                    case 9:
                        flag = false;
                        break;
                }
            }
            if (user.getClass().equals(Manager.class)) {
                while (inner_flag1) {
                    System.out.println("\n..................Home Menu..............................");
                    System.out.println(user.getFirst_name() + ", " + user.getLast_name() + ".Welcome to " + company.getName());
                    System.out.println("1.Employees");
                    System.out.println("2.Airports");
                    System.out.println("3.Planes");
                    System.out.println("4.Flights");
                    System.out.println("5.Passengers");
                    System.out.println("6.Create Coupon");
                    System.out.println("7.Quit");
                    System.out.print("Enter a choice: ");
                    option = sc.nextInt();
                    sc.nextLine();
                    if (option >= 1 && option <= 8) {
                        inner_flag1 = false;
                    } else {
                        System.out.println("Just form 1 to 6 you can choose");
                    }
                }
                switch (option) {
                    case 1:
                        EmployeesController controller = new EmployeesController();
                        while (inner_flag2) {
                            inner_flag2 = controller.showEmployeeMenu(company, controller, user);
                        }
                        break;
                    case 2:
                        AirportController controller1 = new AirportController();
                        while (inner_flag2) {
                            inner_flag2 = controller1.showAirportMenu(company, controller1);
                        }
                        break;
                    case 3:
                        if (Company.airports.isEmpty()) {
                            System.out.println("**You must create airport before**");
                            break;
                        }
                        PlaneController controller2 = new PlaneController();
                        while (inner_flag2) {
                            inner_flag2 = controller2.showPlaneMenu(company, controller2);
                        }
                        break;
                    case 4:
                        if (Company.planes.isEmpty()) {
                            System.out.println("**You must create plane before**");
                            break;
                        }
                        FlightController controller3 = new FlightController();
                        while (inner_flag2) {
                            inner_flag2 = controller3.showFlightMenu(company, controller3, user);
                        }
                        break;
                    case 5:
                        if (Company.flights.isEmpty()) {
                            System.out.println("**You must create flight before**");
                            break;
                        }
                        PassengersController controller4 = new PassengersController();
                        while (inner_flag2) {
                            inner_flag2 = controller4.showPassengerMenu(company, controller4);
                        }
                        break;
                    case 6:
                        company.CreateCoupon(company);
                        break;
                    case 7:
                        flag = false;
                        break;
                }
            }
            if (user.getClass().equals(Director.class)) {
                while (inner_flag1) {
                    System.out.println("\n..................Home Menu..............................");
                    System.out.println(user.getFirst_name() + ", " + user.getLast_name() + ".Welcome to " + company.getName());
                    System.out.println("1.Employees");
                    System.out.println("2.Airports");
                    System.out.println("3.Planes");
                    System.out.println("4.Flights");
                    System.out.println("5.Passengers");
                    System.out.println("6.Quit");
                    System.out.print("Enter a choice: ");
                    option = sc.nextInt();
                    sc.nextLine();
                    if (option >= 1 && option <= 8) {
                        inner_flag1 = false;
                    } else {
                        System.out.println("Just form 1 to 6 you can choose");
                    }
                }
                switch (option) {
                    case 1:
                        EmployeesController controller = new EmployeesController();
                        while (inner_flag2) {
                            inner_flag2 = controller.showEmployeeMenu(company, controller, user);
                        }
                        break;
                    case 2:
                        AirportController controller1 = new AirportController();
                        while (inner_flag2) {
                            inner_flag2 = controller1.showAirportMenu(company, controller1);
                        }
                        break;
                    case 3:
                        if (Company.airports.isEmpty()) {
                            System.out.println("**You must create airport before**");
                            break;
                        }
                        PlaneController controller2 = new PlaneController();
                        while (inner_flag2) {
                            inner_flag2 = controller2.showPlaneMenu(company, controller2);
                        }
                        break;
                    case 4:
                        if (Company.planes.isEmpty()) {
                            System.out.println("**You must create plane before**");
                            break;
                        }
                        FlightController controller3 = new FlightController();
                        while (inner_flag2) {
                            inner_flag2 = controller3.showFlightMenu(company, controller3, user);
                        }
                        break;
                    case 5:
                        if (Company.flights.isEmpty()) {
                            System.out.println("**You must create flight before**");
                            break;
                        }
                        PassengersController controller4 = new PassengersController();
                        while (inner_flag2) {
                            inner_flag2 = controller4.showPassengerMenu(company, controller4);
                        }
                        break;
                    case 6:
                        flag = false;
                        break;
                }
            }
        }catch (InputMismatchException e){
            System.out.println("*** Don't input text only integers accepted here ***");
            sc.nextLine();
        }
        return flag;
    }
}
