package com.hishamfactory;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class PassengersController {
    Scanner sc = new Scanner(System.in);

    public static Passenger getPassengerByName(String first_last_name) {
        int i = 0;
        while (i < Company.passengers.size()) {
            String name = Company.passengers.get(i).getFirst_name() + " " + Company.passengers.get(i).getLast_name();
            if (first_last_name.equals(name)) {
                return Company.passengers.get(i);
            }
            i++;
        }
        return null;
    }

    public void printAllPassengers() {
        if(Company.passengers.isEmpty()){
            System.out.println("*** Sorry,The list of passengers is empty ***");
        }else{
            System.out.println("................................................................");
            for (Passenger passenger : Company.passengers) {
                System.out.println(passenger.toString());
            }
        }
    }

    public void editPassengerInfo(String name) {
        try {
            Passenger passenger = getPassengerByName(name);
            if (passenger == null) {
                System.out.println("*** This passenger doesn't exists ***");
            } else {
                System.out.println("....................Editing Menu..........................");
                System.out.println("1.Edit Name");
                System.out.println("2.Edit Age");
                System.out.println("3.Edit Tel Number");
                System.out.println("4.Edit password");
                System.out.println("5.Quit");
                System.out.print("select option: ");
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.print("Enter new first name: ");
                        passenger.setFirst_name(sc.next());
                        System.out.print("Enter new last name: ");
                        passenger.setLast_name(sc.next());
                        System.out.println("Name Changed to " + passenger.getFirst_name() + " " + passenger.getLast_name());
                        break;
                    case 2:
                        System.out.print("Enter new age: ");
                        passenger.setAge(sc.nextInt());
                        System.out.println("Age changed to " + passenger.getAge());
                        break;

                    case 3:
                        System.out.print("Enter new tel number: ");
                        passenger.setTel_number(sc.next());
                        System.out.println("Tel number changed to " + passenger.getTel_number());
                        break;
                    case 4:
                        System.out.print("Enter new password: ");
                        passenger.setPinHash(sc.next());
                        System.out.println("Password changed");
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("*** Please enter a valid choice ***");
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Input not found. Please enter text without spaces");
            sc.next();
        }
    }
    public void editPassengerInfo(Passenger passenger) {
        try {
            System.out.println("....................Editing Menu..........................");
            System.out.println("1.Edit Name");
            System.out.println("2.Edit Age");
            System.out.println("3.Edit Tel Number");
            System.out.println("4.Edit password");
            System.out.println("5.Quit");
            System.out.print("select option: ");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter new first name: ");
                    passenger.setFirst_name(sc.next());
                    System.out.print("Enter new last name: ");
                    passenger.setLast_name(sc.next());
                    System.out.println("Name Changed to " + passenger.getFirst_name() + " " + passenger.getLast_name());
                    break;
                case 2:
                    System.out.print("Enter new age: ");
                    passenger.setAge(sc.nextInt());
                    System.out.println("Age changed to " + passenger.getAge());
                    break;

                case 3:
                    System.out.print("Enter new tel number: ");
                    passenger.setTel_number(sc.next());
                    System.out.println("Tel number changed to " + passenger.getTel_number());
                    break;
                case 4:
                    System.out.print("Enter new password: ");
                    passenger.setPinHash(sc.next());
                    System.out.println("Password changed");
                    break;
                case 5:
                    break;
                default:
                    System.out.println("*** Please enter a valid choice ***");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Input not found. Please enter text without spaces");
            sc.next();
        }
    }

    public void deletePassenger(String name) {
        Passenger passenger = getPassengerByName(name);
        if (passenger != null) {
            Company.passengers.remove(passenger);
        } else {
            System.out.println("*** This passenger doesn't exists ***");
        }
    }

    public boolean showPassengerMenu(Company company, PassengersController controller) {
        boolean flag = true;
        try {
            System.out.println(".......................Passenger Menu...............................");
            System.out.println("1.Add new passenger");
            System.out.println("2.Get passenger by name");
            System.out.println("3.Show all passengers");
            System.out.println("4.Edit passenger info");
            System.out.println("5.Delete passenger");
            System.out.println("6.Quit");
            System.out.print("Enter a choice: ");
            int option = sc.nextInt();
            String name = "";
            if (option != 1 && option != 3 && !(option > 5)) {
                System.out.print("Enter first name: ");
                name = sc.next();
                System.out.print("Enter last name: ");
                name += " " + sc.next();
            }
            switch (option) {
                case 1:
                    company.addPassenger(company);
                    break;
                case 2:
                    System.out.println(controller.getPassengerByName(name).toString());
                    break;
                case 3:
                    controller.printAllPassengers();
                    break;
                case 4:
                    controller.editPassengerInfo(name);
                    break;
                case 5:
                    controller.deletePassenger(name);
                    break;
                default:
                    flag = false;
                    break;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Input not found. Please enter text without spaces");
            sc.next();
        } catch (NullPointerException e) {
            System.out.println("*** This passenger doesn't exits ***");
        }
        return flag;
    }
    public boolean showPassengerMenu(Passenger passenger ){
        boolean flag = true;
        try {
            System.out.println(".......................Passenger Menu...............................");
            System.out.println("1.Book Flight");
            System.out.println("2.Edit Info");
            System.out.println("3.Show all flights");
            System.out.println("4.Cancel Flight");
            System.out.println("5.Quit");
            System.out.print("Enter a choice: ");
            int option = sc.nextInt();
            FlightController flightController = new FlightController();
            switch (option) {
                case 1:
                    flightController.bookFlight(passenger);
                    break;
                case 2:
                    editPassengerInfo(passenger);
                    break;
                case 3:
                    flightController.showAllFlights(passenger);
                    break;
                case 4:
                    flightController.cancelFlight(passenger);
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("*** Please enter a valid choice ***");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Input not found. Please enter text without spaces");
            sc.next();
        } catch (NullPointerException e) {
            System.out.println("*** This passenger doesn't exits ***");
        }
        return flag;
    }

    public static boolean checkPassengerNotExist(String name) {
        for (Passenger passenger : Company.passengers) {
            String passenger_name = passenger.getFirst_name() + " " + passenger.getLast_name();
            if (passenger_name.equalsIgnoreCase(name)) {
                return false;
            }
        }
        return true;
    }
}
