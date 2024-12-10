package com.hishamfactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FlightController extends ClearData{
    Scanner sc = new Scanner(System.in);

    /**
     * Print all available flights on the system
     */
    public void showAllFlights() {
        if(Company.flights.isEmpty()){
            System.out.println("** Sorry,The list of flights is empty **");
        }else{
            ArrayList<Flight> flightsHashsetToArrayList = new ArrayList<>(Company.flights);
            Collections.sort(flightsHashsetToArrayList, Flight.comparesFlightsByDepartureTime);
            System.out.println(".......................List of Flights..........................");
            for (Flight flight : flightsHashsetToArrayList) {
                System.out.println("[departure_airport:"+flight.getDeparture_airport().getAirport_name()
                        +", destination_airport:"+flight.getDestination_airport().getAirport_name()+", pilot:"
                        + flight.getFlightPilot().getLast_name()+", departure_time:"+flight.getDeparture_time()
                        +", arrival_time:" + flight.getArrival_time()+", price:" +flight.getTicket_price()+"]");
            }
        }
    }

    /**
     * Print all flights booked by specific passenger
     * @param passenger     the passenger who has these flights
     */
    public void showAllFlights(Passenger passenger) {
        if(passenger.passenger_flights.isEmpty()){
            System.out.println("*** Sorry,There is not any flight booked ***");
        }else{
            System.out.println(".......................List of Flights..........................");
            for (FlightBooked flightBooked : passenger.passenger_flights) {
                System.out.println(flightBooked.toString());
            }
        }
    }

    /**
     * Get flight by id after check it's exist or not
     * @param id    the flight id identifying the flight
     * @return      the flight if it's exist or null it's not
     */
    public static Flight getFlightById(String id) {
        for (Flight flight : Company.flights) {
            if (flight.getFlight_uuid().equals(id)) return flight;
        }
        return null;
    }

    /**
     * Show all necessary information of flight
     * @param flight_code   the flight code identifying the flight
     */
    public void showFlightInfo(String flight_code) {
            Flight flight = getFlightById(flight_code);
            if(flight != null)
                System.out.println(flight.toString());
            else
                System.out.println("*** This flight not exits ***");
    }

    /**
     * Edit information that related to flight section
     * @param flight_code   the code identifying the flight to be edited
     * @param user        the employee who do this modification
     * @param company       the company who has this system
     */
    public void editFlightInfo(String flight_code, User user, Company company) {
        Flight flight = getFlightById(flight_code);
        if (flight != null) {
            boolean hasPermission = false;
            for (String permissionsUuid : Company.permissions_editing_uuids) {
                if (permissionsUuid.equalsIgnoreCase(user.getUuid())) {
                    hasPermission = true;
                    break;
                }
            }
            if (!hasPermission) {
                System.out.println("*** Sorry, you don't have editing access please contact with you manager ***");
            } else {
                System.out.println("...............Editing Menu.....................");
                System.out.println("1.Edit Departure airport");
                System.out.println("2.Edit Destination airport");
                System.out.println("3.Edit Departure time");
                System.out.println("4.Edit Arrival time");
                System.out.println("5.Edit Used Plane");
                System.out.println("6.Edit Ticket Price");
                System.out.println("7.Add New Passenger");
                System.out.println("8.Remove Passenger");
                System.out.println("9.Change flight captain");
                System.out.println("10.Quit");
                System.out.print("Enter a choice: ");
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1:
                        System.out.println("......................Airports Available...................");
                        for (Airport airport : Company.airports) {
                            System.out.println(airport.toString());
                        }
                        System.out.println("...........................................................");
                        System.out.print("Enter new departure airport name: ");
                        Airport departure_airport = AirportController.getAirportByName(sc.next());
                        sc.nextLine();
                        if ( departure_airport!= null) {
                            flight.setDeparture_airport(departure_airport);
                            System.out.println("Flight's departure airport changed to " + departure_airport.getAirport_name());
                        }else{
                            System.out.println("*** This airport doesn't exists ***");
                        }
                        break;
                    case 2:
                        System.out.println("......................Airports Available...................");
                        for (Airport airport : Company.airports) {
                            System.out.println(airport.toString());
                        }
                        System.out.println("...........................................................");
                        System.out.print("Enter new destination airport name: ");
                        Airport destination_airport = AirportController.getAirportByName(sc.next());
                        sc.nextLine();
                        if (destination_airport != null) {
                            flight.setDestination_airport(destination_airport);
                            System.out.println("Flight's destination airport changed to " + destination_airport.getAirport_name());
                        }else{
                            System.out.println("*** This airport doesn't exists ***");
                        }
                        break;
                    case 3:
                        System.out.print("Enter new departure time: ");
                        String departure_time=sc.next();
                        sc.nextLine();
                        if(departure_time != null){
                            flight.setDeparture_time(departure_time);
                            System.out.println("Flight's departure time change to " + flight.getDeparture_time());
                        }else{
                            System.out.println("*** Please enter a valid departure time ***");
                        }
                        break;
                    case 4:
                        System.out.print("Enter new arrival time: ");
                        String arrival_time=sc.next();
                        sc.nextLine();
                        if(arrival_time != null){
                            flight.setArrival_time(arrival_time);
                            System.out.println("Flight's arrival time changed to " + flight.getArrival_time());
                        }else{
                            System.out.println("*** Please enter a valid arrival time ***");
                        }
                        break;
                    case 5:
                        System.out.println("....................Available Planes.......................");
                        for (Plane plane : Company.planes) {
                            System.out.println(plane.toString());
                        }
                        System.out.println("...........................................................");
                        System.out.print("Enter new plane code: ");
                        Plane plane = PlaneController.getPlaneById(sc.next());
                        sc.nextLine();
                        if(plane != null){
                            flight.setPlane(plane);
                            System.out.println("Flight's plane change to Plane has ID " + plane.getPlane_uuid());
                        }else{
                            System.out.println("*** This plane doesn't exists ***");
                        }
                        break;
                    case 6:
                        System.out.print("Enter new price of flight: ");
                        double ticket_price_new = sc.nextDouble();
                        sc.nextLine();
                        flight.setTicket_price(ticket_price_new);
                        break;
                    case 7:
                        company.addPassenger(company,flight);
                        break;
                    case 8:
                        System.out.println(".......................Flight's Passengers.................");
                        ArrayList<Passenger> passengers = flight.getPassengers();
                        for (Passenger passenger : passengers) {
                            System.out.println(passenger.toString());
                        }
                        System.out.println("............................................................");
                        System.out.print("Enter passenger first name: ");
                        String passenger_first_name_to_remove = sc.next();
                        sc.nextLine();
                        System.out.print("Enter passenger last name: ");
                        String passenger_last_name_to_remove = sc.next();
                        sc.nextLine();
                        Passenger passenger_to_remove = PassengersController.getPassengerByName(passenger_first_name_to_remove + passenger_last_name_to_remove);
                        if(passenger_to_remove != null){
                            passenger_to_remove.hasNewMessages("Your ticket was removed by admin");
                            flight.getPassengers().remove(passenger_to_remove);
                            System.out.println("Passenger " +passenger_to_remove.getFirst_name() +","+passenger_to_remove.getLast_name() + " removed from flight");
                        }else{
                            System.out.println("*** This passenger doesn't exists ***");
                        }
                        break;
                    case 9:
                        System.out.println("...................................Available Pilots..............................");
                        for (Pilot pilot : Company.pilots) {
                            System.out.println(pilot.toString());
                        }
                        System.out.println(".................................................................................");
                        System.out.print("Enter pilot first name: ");
                        String name = sc.next();
                        sc.nextLine();
                        System.out.print("Enter pilot last name: ");
                        name += sc.next();
                        sc.nextLine();
                        EmployeesController employeesController = new EmployeesController();
                        Pilot  pilot = (Pilot) employeesController.getEmployeeByName(name);
                        if(pilot != null){
                            flight.setFlight_captain(pilot);
                            System.out.println("Flight's captain change to pilot has ID " + pilot.getUuid());
                        }else {
                            System.out.println("*** This pilot doesn't exists ***");
                        }
                    case 10:
                        break;
                    default:
                        System.out.println("*** Please enter a valid choice ***");
                }
            }
        }else{
            System.out.println("*** This flight doesn't exists ***");
        }
    }

    /**
     * Print all passengers recorded on this flight
     * @param flight_code   the code identifying the flight to print its passengers
     */
    public void showFlightPassengers(String flight_code) {
        Flight flight = getFlightById(flight_code);
        if(flight != null) {
            if(flight.getPassengers().isEmpty()){
                System.out.println("** Sorry.The Passengers list of this flight is empty ***");
            }else{
                System.out.println(".................Passengers of flight..........................");
                ArrayList<Passenger> passengers = flight.getPassengers();
                for (Passenger passenger : passengers) {
                    System.out.println(passenger.toString());
                }
            }
            }else {
            System.out.println("*** This flight not exits ***");
        }
    }

    /**
     * Cancel flight from the system of airline company
     * @param flight_code   the code identifying flight to be canceled
     */
    public  void cancelFlight(String flight_code){
        Flight flight = getFlightById(flight_code);
        if(flight != null){
            ArrayList<Passenger> passengers = flight.getPassengers();
            if(!passengers.isEmpty()){
                for (Passenger passenger : passengers) {
                    passenger.hasNewMessages("Unfortunately, you flight from" + flight.getDeparture_airport() + " to " + flight.getDestination_airport() + " was cancelled we are sorry for that");
                    passenger.passenger_flights.remove(flight);
                }
            }
            Company.flights.remove(flight);
            System.out.println("Flight " + flight.getFlight_uuid() +" removed from system");
        }else{
            System.out.println("*** This flight not exists ***");
        }
    }

    /**
     * Cancel flight from account of passenger
     * @param passenger     the passenger who want to cancel this flight
     */
    public  void cancelFlight(Passenger passenger){
        System.out.println(".......................Future Flights........................");
        for (FlightBooked flightBooked : passenger.passenger_flights) {
            System.out.println(flightBooked);
        }
        System.out.print("Enter flight code you want cancel: ");
        Flight flight = getFlightById(sc.next());
        sc.nextLine();
        if(flight != null){
            ArrayList<Passenger> passengers = flight.getPassengers();
            passenger.passenger_flights.remove(flight);
            passenger.hasNewMessages("Your flight was cancelled");
            passengers.remove(passenger);
        }else{
            System.out.println("*** This flight not exists ***");
        }
    }

    /**
     * Passenger can book any available flight
     * @param passenger     the passenger who want to book a flight
     */
    public  void bookFlight(Passenger passenger){
        boolean flight_exit = false;
        boolean passenger_exit = false;
        System.out.print("Enter dep airport name: ");
        Airport dep_airport = AirportController.getAirportByName(sc.next());
        sc.nextLine();
        System.out.print("Enter des airport name : ");
        Airport des_airport = AirportController.getAirportByName(sc.next());
        sc.nextLine();
        System.out.println("...................Available Flights.......................");
        for (Flight flight : Company.flights) {
            if (flight.getDeparture_airport().equals(dep_airport) && flight.getDestination_airport().equals(des_airport)) {
                System.out.println(flight);
                flight_exit = true;
                System.out.println(".......................................................");
            }
        }
        if (flight_exit) {
            System.out.print("Enter flight code: ");
            Flight flight = getFlightById(sc.next());
            sc.nextLine();
            if (flight != null) {
                if(flight.hasAvailableSeat(flight)) {
                    ArrayList<Passenger> passengers =flight.getPassengers();
                    for (Passenger passenger1 : passengers) {
                        if (passenger1.getUuid().equals(passenger.getUuid())) {
                            passenger_exit = true;
                            break;
                        }
                    }
                    if (!passenger_exit) {
                        boolean flightIsAdded = false;
                        int counter = 0;
                        String[] flight_seats = flight.getFlight_seats();
                        System.out.println("    ");
                        for (int i = 0; i < flight_seats.length; i++) {
                            if(flight_seats[i] == null){
                                System.out.print(i+1 +" ");
                                counter++;
                            }
                            if(counter > 15){
                                counter = 0;
                                System.out.println("    ");
                            }
                        }
                        System.out.println();
                        System.out.print("Select your seat's number : ");
                        int passenger_seat = sc.nextInt();
                        sc.nextLine();
                        flight_seats[passenger_seat-1] = passenger.getFirst_name()+" "+passenger.getLast_name();
                        System.out.print("Do you have a coupon code(Yes(y) or No(n)): ");
                        String have_coupon = sc.next();
                        sc.nextLine();
                        FlightBooked flightBooked = new FlightBooked(flight.getFlight_uuid(),flight.getDeparture_airport(),flight.getDestination_airport(),flight.getDeparture_time(),flight.getArrival_time(),flight.getTicket_price(),passenger_seat);
                        if(have_coupon.equalsIgnoreCase("Y") || have_coupon.equalsIgnoreCase("Yes")){
                            boolean isValidCoupon = true;
                            while(isValidCoupon){
                            System.out.print("Enter coupon code: ");
                            String coupon_code = sc.next();
                            sc.nextLine();
                            CouponController couponController = new CouponController();
                            Coupon coupon = couponController.getCouponByCode(coupon_code);
                            if(coupon == null){
                                System.out.println(" *** The coupon is invalid *** ");
                                System.out.println("........................................................");
                                System.out.print("Do you want try again(Y or N): ");
                                String try_again = sc.next();
                                sc.nextLine();
                                System.out.println("........................................................");
                                if(try_again.equalsIgnoreCase("N")) break;
                                continue;
                            }
                            isValidCoupon = false;
                            passenger.passenger_flights.add(flightBooked);
                            flightIsAdded = true;
                            double price_after_discount = couponController.discountCalculation(flight.getTicket_price(),coupon.getCoupon_in_percentage());
                            flightBooked.setTicket_price(price_after_discount);
                                System.out.println("The new price of flight after discount is " + flightBooked.getTicket_price());

                        }
                        }
                        if(!flightIsAdded) passenger.passenger_flights.add(flightBooked);
                        flight.getPassengers().add(passenger);
                        System.out.println("Flight booked from " + flight.getDeparture_airport().getAirport_name() + " to " + flight.getDestination_airport().getAirport_name() + " at " + flight.getDeparture_time());
                    }else {
                        System.out.println("*** You already booked into this flight ***");
                    }
                }else{
                    System.out.println("** Sorry this flight is full ***");
                }
            } else {
                System.out.println("*** This flight doesn't exists ***");
            }
        } else {
            System.out.println("*** Sorry, There aren't any flight to your Trip ***");
        }
    }
    /**
     * Show operations can be performed on flight
     * @param company       the airline company who has this system
     * @param controller    the controller object can perform operation by it
     * @param user        the person who do these operations
     * @return              boolean value to stay on login on this menu or log out from it
     */
    public boolean showFlightMenu(Company company, FlightController controller, User user) {
        boolean flag = true;
            System.out.println(".................Flight Menu..............................");
            System.out.println("1.add flight");
            System.out.println("2.Show all flights");
            System.out.println("3.Show flight passengers");
            System.out.println("4.Show flight info");
            System.out.println("5.Edit flight info");
            System.out.println("6.Cancel flight");
            System.out.println("7.Clear flights file");
            System.out.println("8.Quit");
            System.out.print("Enter a choice: ");
        try {
            int option = sc.nextInt();
            sc.nextLine();
            String flight_code = null;
            if(option >= 3 && option <= 6){
                System.out.print("Enter flight code: ");
                flight_code = sc.next();
                sc.nextLine();
            }
            switch (option) {
                case 1:
                    company.addFlight(company);
                    break;
                case 2:
                    controller.showAllFlights();
                    break;
                case 3:
                    controller.showFlightPassengers(flight_code);
                    break;
                case 4:
                    controller.showFlightInfo(flight_code);
                    break;
                case 5:
                    controller.editFlightInfo(flight_code, user,company);
                    break;
                case 6:
                    controller.cancelFlight(flight_code);
                    break;
                case 7:
                    clearDataFromFile("DataFiles/flights.dat");
                    break;
                case 8:
                    flag = false;
                    break;
                default:
                    System.out.println("*** Please enter a valid choice ***");
            }
        }catch (InputMismatchException e) {
            System.out.println("*** Your input mismatch whats excepted, please enter valid input ***");
            sc.nextLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return flag;
    }
    public static int getNumberOfSeatOfPassenger(Flight flight, String passengerName){
        String[] flightSeats = flight.getFlight_seats();
        for (int i = 0; i < flightSeats.length; i++) {
            if(flightSeats[i] != null && flightSeats[i].equals(passengerName)){
                return i+1;
            }
        }
        return -1;
    }
}
