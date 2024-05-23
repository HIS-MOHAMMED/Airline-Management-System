package com.hishamfactory;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AirportController {
    Scanner sc = new Scanner(System.in);

    /**
     * Get airport after check by code if it's exist or not
     * @param airport_code    the airport code to identify by it
     * @return                the airport object if exist or null if it's not
     */
    public static Airport getAirportByID(String airport_code){
            for (Airport airport : Company.airports) {
                if(airport.getAirport_code().equals(airport_code)){
                    return airport;
                }
            }
        return null;
    }
    /**
     * Get airport after check by name if it's exist or not
     * @param airport_name  the airport name to identify by it
     * @return              the airport if exist or null if it's not
     */
    public static Airport getAirportByName(String airport_name){
        for (Airport airport : Company.airports) {
            if(airport.getAirport_name().equalsIgnoreCase(airport_name)) return airport;
        }
        return null;
    }

    /**
     * Print all airports that exist on system
     */
    public void showAllAirports(){
        if(Company.airports.isEmpty()){
            System.out.println("** Sorry.The airport list is empty **");
        }else{
            System.out.println("......................List of Airports......................");
            for (Airport airport : Company.airports) {
                System.out.println(airport.toString());
            }
        }
    }

    /**
     * Edit airport information
     * @param airport_code  the airport code identifying the airport to be deleted
     */
    public void editAirport(String airport_code){
        try {
            Airport airport = getAirportByID(airport_code);
            if (airport != null) {
                System.out.println("....................Editing Menu......................");
                System.out.print("1.Edit airport Name");
                System.out.println();
                System.out.print("2.Edit airport Location");
                System.out.println();
                System.out.print("3.Edit airport Number of Runways");
                System.out.println();
                System.out.print("4.Edit airport Number of Gates");
                System.out.println();
                System.out.println("5.Quit");
                System.out.print("Enter a choice? ");
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1:
                        System.out.print("Enter new airport name: ");
                        airport.setAirport_name(sc.next());
                        sc.nextLine();
                        System.out.println("Airport name changed to " + airport.getAirport_name());
                        break;
                    case 2:
                        System.out.print("Enter new airport location: ");
                        airport.setAirport_location(sc.nextLine());
                        sc.nextLine();
                        System.out.println("Airport location changed to " + airport.getAirport_location());
                        break;
                    case 3:
                        System.out.print("Enter airport new number of runways: ");
                        airport.setAirport_number_runways(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Airport number of runways changed to " + airport.getAirport_number_runways());
                        break;
                    case 4:
                        System.out.print("Enter airport new number of gates: ");
                        airport.setAirport_number_gates(sc.nextInt());
                        sc.nextLine();
                        System.out.println("Airport number of gates changed to " + airport.getAirport_number_gates());
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("*** Please enter a valid choice ****");
                }
            } else {
                System.out.println("*** This airport not exists ***");
            }
        } catch (NoSuchElementException e) {
            System.out.println("*** Input not found. Please enter text without spaces ***");
            sc.nextLine();
        }
    }
    /**
     * Delete airport from system
     * @param airport_code  the airport code identifying the airport to be deleted
     */
    public void deleteAirport(String airport_code){
            Airport airport = getAirportByID(airport_code);
            if(airport != null) {
                Company.airports.remove(airport);
                System.out.println("The airport " + airport.getAirport_name() + " was deleted..");
            }else{
            System.out.println("*** This airport not exists ***");
        }
    }

    /**
     * Show airport menu to do operation on airport section
     * @param company       the company who owns the system
     * @param controller    the controller object used to perform operations
     * @return              the boolean value to stay login on menu or logout
     */
    public boolean showAirportMenu(Company company,AirportController controller) {
        boolean flag = true;
        try {
            System.out.println(".....................Airport Menu..........................");
            System.out.println("1.Add new airport");
            System.out.println("2.Show all airport");
            System.out.println("3.Get Airport by ID");
            System.out.println("4.Edit airport info");
            System.out.println("5.Delete airport");
            System.out.println("6.Quit");
            System.out.print("Enter a choice: ");
            int option = sc.nextInt();
            sc.nextLine();
            String airport_code = null;
            if(option >=3 && option <= 5) {
                System.out.print("Enter airport code: ");
                airport_code = sc.next();
                sc.nextLine();
            }
            switch (option) {
                case 1:
                    company.addAirport(company);
                    break;
                case 2:
                    controller.showAllAirports();
                    break;
                case 3:
                    Airport airport = getAirportByID(airport_code);
                    if(airport != null) {
                        System.out.println(getAirportByID(airport_code));
                    }else{
                        System.out.println("*** This airport not exits ***");
                    }
                    break;
                case 4:
                    controller.editAirport(airport_code);
                    break;
                case 5:
                    controller.deleteAirport(airport_code);
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter a valid choice");
            }
        } catch (NullPointerException e) {
            System.out.println("A NullPointerException occurred.System will exit");
            System.exit(0);
        } catch (NoSuchElementException e) {
            System.out.println("Input not found. Please enter text without spaces");
            sc.nextLine();
        }
        return flag;
    }
}
