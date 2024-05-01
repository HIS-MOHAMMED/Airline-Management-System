package com.hishamfactory;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Company company = new Company("Turkish Airline");
        boolean isNew = true;
        do {
            System.out.println("......................................................");
            System.out.println("Welcome to Airline System Management");
            System.out.println("...................................,..................");
            while(isNew){
                System.out.println("**As first employee you must to create Admin user**");
                company.addEmployee(company);
                System.out.println("..................................................");
                if (!Company.administrators.isEmpty()) {
                    isNew = false;
                }
            }
            showLoginMenu(company, sc);
        } while (true);
    }

    public static void showLoginMenu(Company company, Scanner sc) {
        Person personAuth = null;
        boolean flag;
        do {
            try {
                flag = true;
                int person_type;
                String person_id = "";
                String person_pin = "";
                if (!Company.employees.isEmpty()) {
                    System.out.print("Are you 1.Employee or 2.Passenger: ");
                    person_type = sc.nextInt();
                    if (person_type == 1) {
                        System.out.print("Enter  ID: ");
                        person_id = sc.next();
                        System.out.print("Enter  password: ");
                        person_pin = sc.next();
                        personAuth = company.employeeLogin(person_id, person_pin);
                        if (personAuth == null) {
                            System.out.println("ID or password incorrect.please try again");
                        } else {
                            while (flag) {
                                flag = showUserMenu(company, personAuth, sc);
                            }
                        }
                    } else if (person_type == 2) {
                        System.out.print("Enter  ID: ");
                        person_id = sc.next();
                        System.out.print("Enter  password: ");
                        person_pin = sc.next();
                        Passenger passenger = company.passengerLogin(person_id, person_pin);
                        if (passenger == null) {
                            System.out.println("ID or password incorrect.please try again");
                        } else {
                            while (flag) {
                                PassengersController controller = new PassengersController();
                                flag = controller.showPassengerMenu(passenger);
                            }
                        }
                    } else {
                        System.out.println("Answer not found. Please enter only 1(employees) or 2(passengers)");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Please enter valid integer input");
                sc.next();
            } catch (NoSuchElementException e) {
                System.out.println("Input not found. Please enter text without spaces");
            }
        } while (personAuth == null);
    }

    public static boolean showUserMenu(Company company, Person person, Scanner sc) {
        boolean flag = true;
        boolean inner_flag1 = true;
        boolean inner_flag2 = true;
        int option = 1;
        if (Company.administrators.contains(person)) {
            while (inner_flag1) {
                System.out.println("\n........................Home Menu.....................");
                System.out.println(person.getFirst_name() + ", " + person.getLast_name() + ".Welcome to " + company.getName());
                System.out.println("1.Employees");
                System.out.println("2.Airports");
                System.out.println("3.Planes");
                System.out.println("4.Flights");
                System.out.println("5.Passengers");
                System.out.println("6.Quit");
                System.out.print("Enter a choice: ");
                option = sc.nextInt();
                if (option >= 1 && option <= 6) {
                    inner_flag1 = false;
                } else {
                    System.out.println("Just form 1 to 6 you can choose");
                }
            }
            switch (option) {
                case 1:
                    EmployeesController controller = new EmployeesController();
                    while (inner_flag2) {
                        inner_flag2 = controller.showEmployeeMenu(company, controller, person);
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
                        inner_flag2 = controller3.showFlightMenu(company, controller3,person);
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
        } else{
        while (inner_flag1) {
            System.out.println("\n........................Home Menu.....................");
            System.out.println(person.getFirst_name() + ", " + person.getLast_name() + ".Welcome to " + company.getName());
            System.out.println("1.Airports");
            System.out.println("2.Planes");
            System.out.println("3.Flights");
            System.out.println("4.Quit");
            System.out.print("Enter a choice: ");
            option = sc.nextInt();
            if (option >= 1 && option <= 6) {
                inner_flag1 = false;
            } else {
                System.out.println("Just form 1 to 4 you can choose");
            }
        }
        switch (option) {
            case 1:
                AirportController controller1 = new AirportController();
                while (inner_flag2) {
                    inner_flag2 = controller1.showAirportMenu(company, controller1);
                }
                break;
            case 2:
                if (Company.airports.isEmpty()) {
                    System.out.println("**You must create airport before**");
                    break;
                }
                PlaneController controller2 = new PlaneController();
                while (inner_flag2) {
                    inner_flag2 = controller2.showPlaneMenu(company, controller2);
                }
                break;
            case 3:
                if (Company.planes.isEmpty()) {
                    System.out.println("**You must create plane before**");
                    break;
                }
                FlightController controller3 = new FlightController();
                while (inner_flag2) {
                    inner_flag2 = controller3.showFlightMenu(company, controller3,person);
                }
                break;
            case 4:
                flag = false;
                break;
        }
    }
        return flag;
    }
}
