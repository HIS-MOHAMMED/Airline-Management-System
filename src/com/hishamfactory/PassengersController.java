package com.hishamfactory;

import java.io.IOException;
import java.util.*;

public class PassengersController extends ClearData{
    Scanner sc = new Scanner(System.in);

    /**
     * Get a passenger after check if it's exists or not
     * @param first_last_name       the name of the passenger
     * @return                      the passenger if it's exist or null it's not
     */
    public static Passenger getPassengerByName(String first_last_name) {
        for (Passenger passenger : Company.passengers) {
            String name = passenger.getFirst_name() + passenger.getLast_name();
            if (name.equals(first_last_name)) {
                return passenger;
            }
        }
        return null;
    }

    public static Passenger getPassengerByUserName(String passengerUserName) {
        for (Passenger passenger : Company.passengers) {
            if(passenger.getUser_name().equals(passengerUserName)) return passenger;
        }
        return null;
    }
    /**
     * Print all passenger on the system
     */
    public void showAllPassengers() {
        if(Company.passengers.isEmpty()){
            System.out.println("*** Sorry,The list of passengers is empty ***");
        }else{
            ArrayList<Passenger> passengerHashsetToArrayList = new ArrayList<>(Company.passengers);
            Collections.sort(passengerHashsetToArrayList,Passenger.comparePassengersByAge);
            System.out.println("................................................................");
            for (Passenger passenger : passengerHashsetToArrayList) {
                System.out.println("[passenger_name:" + passenger.getFirst_name()+" " + passenger.getLast_name()+", age:" +passenger.getAge()+", tel:"+passenger.getTel_number()+", address:" +passenger.getAddress()+"]");
            }
        }
    }

    /**
     * Edit the passenger information by employee
     * @param name the name of the passenger
     */
    public void editPassengerInfo(String name) {
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
                sc.nextLine();
                switch (option) {
                    case 1:
                        System.out.print("Enter new first name: ");
                        passenger.setFirst_name(sc.next());
                        sc.nextLine();
                        System.out.print("Enter new last name: ");
                        passenger.setLast_name(sc.next());
                        sc.nextLine();
                        System.out.println("Name Changed to " + passenger.getFirst_name() + passenger.getLast_name());
                        break;
                    case 2:
                        System.out.print("Enter new age: ");
                        passenger.setAge(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Age changed to " + passenger.getAge());
                        break;

                    case 3:
                        System.out.print("Enter new tel number: ");
                        passenger.setTel_number(sc.next());
                        sc.nextLine();
                        System.out.println("Tel number changed to " + passenger.getTel_number());
                        break;
                    case 4:
                        System.out.print("Enter new password: ");
                        passenger.setPinHash(sc.next());
                        sc.nextLine();
                        System.out.println("Password changed");
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("*** Please enter a valid choice ***");
                }
        }
    }

    /**
     * Edit the passenger information by passenger himself
     * @param passenger     the name of passenger
     */
    public void editPassengerInfo(Passenger passenger) {
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
                    sc.nextLine();
                    System.out.print("Enter new last name: ");
                    passenger.setLast_name(sc.next());
                    sc.nextLine();
                    System.out.println("Name Changed to " + passenger.getFirst_name() + " " + passenger.getLast_name());
                    break;
                case 2:
                    System.out.print("Enter new age: ");
                    passenger.setAge(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Age changed to " + passenger.getAge());
                    break;

                case 3:
                    System.out.print("Enter new tel number: ");
                    passenger.setTel_number(sc.next());
                    sc.nextLine();
                    System.out.println("Tel number changed to " + passenger.getTel_number());
                    break;
                case 4:
                    System.out.print("Enter new password: ");
                    passenger.setPinHash(sc.next());
                    sc.nextLine();
                    System.out.println("Password changed");
                    break;
                case 5:
                    break;
                default:
                    System.out.println("*** Please enter a valid choice ***");
            }
    }

    /**
     * Delete a passenger from the system
     * @param name  the name fo the passenger
     */
    public void deletePassenger(String name) {
        Passenger passenger = getPassengerByName(name);
        if (passenger != null) {
            Company.passengers.remove(passenger);
            removePassengerFromFlights(passenger);
        } else {
            System.out.println("*** This passenger doesn't exists ***");
        }
    }

    /**
     * Show all available operations can perform on passenger section by employee
     * @param company       the company who has the system
     * @param controller    the controller object to perform operation by it
     * @return              boolean value to stay login on menu or log out from it
     */
    public boolean showPassengerMenu(Company company, PassengersController controller) {
        boolean flag = true;
            System.out.println(".......................Passenger Menu...............................");
            System.out.println("1.Add new passenger");
            System.out.println("2.Get passenger by name");
            System.out.println("3.Show all passengers");
            System.out.println("4.Edit passenger info");
            System.out.println("5.Delete passenger");
            System.out.println("6.Quit");
            System.out.print("Enter a choice: ");
        try {
            int option = sc.nextInt();
            sc.nextLine();
            String name = "";
            if (option != 1 && option != 3 && !(option > 5)) {
                System.out.print("Enter first name: ");
                name = sc.next();
                sc.nextLine();
                System.out.print("Enter last name: ");
                name += sc.next();
                sc.nextLine();
            }
            switch (option) {
                case 1:
                    company.addPassenger(company);
                    break;
                case 2:
                    System.out.println(getPassengerByName(name));
                    break;
                case 3:
                    controller.showAllPassengers();
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
        } catch (InputMismatchException e) {
            System.out.println("*** Your input mismatch whats excepted, please enter valid input ***");
            sc.nextLine();
        }
        return flag;
    }

    /**
     * Show all available operations can perform by passenger
     * @param passenger     the passenger who do the operation
     * @return              boolean value ot stay login on menu or log out from it
     */
    public boolean showPassengerMenu(Passenger passenger ){
        boolean flag = true;
        try {
            System.out.println(".......................Passenger Menu...............................");
            System.out.println("1.Book Flight");
            System.out.println("2.Edit Info");
            System.out.println("3.Show all flights");
            System.out.println("4.Cancel Flight");
            System.out.println("5.Clear passengers file");
            System.out.println("6.Quit");
            System.out.print("Enter a choice: ");
            int option = sc.nextInt();
            sc.nextLine();
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
                    clearDataFromFile("DataFiles/passengers.dat");
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("*** Please enter a valid choice ***");
            }
        } catch (InputMismatchException e) {
            System.out.println("*** Your input mismatch whats excepted, please enter valid input ***");
            sc.nextLine();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return flag;
    }

    /**
     * Check if the passenger itself has already exist on specific flight or not
     * @param user_name  the name of passenger
     * @return      already exist or not
     */
    public static boolean checkPassengerNotExist(String user_name) {
        for (Passenger passenger : Company.passengers) {
            if (passenger.getUser_name().equalsIgnoreCase(user_name)) {
                return true;
            }
        }
        return false;
    }
    public void removePassengerFromFlights(Passenger passenger){
        ArrayList<FlightBooked> passengerFlights = passenger.passenger_flights;
        for (FlightBooked passengerFlight : passengerFlights) {
            Flight flight = FlightController.getFlightById(passengerFlight.getFlight_uuid());
            if(flight != null)
                flight.getPassengers().remove(passenger);
            else
                System.out.println("There is a something wrong,couldn't remove passenger from flight has uuid: " + passengerFlight.getFlight_uuid());
        }
    }
}
