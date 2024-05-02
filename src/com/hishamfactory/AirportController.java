package com.hishamfactory;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AirportController {
    Scanner sc = new Scanner(System.in);
    public static Airport getAirportByID(String airport_code){
            for (Airport airport : Company.airports) {
                if(airport.getAirport_code().equals(airport_code)){
                    return airport;
                }
            }
        return null;
    }
    public static Airport getAirportByName(String airport_name){
        for (Airport airport : Company.airports) {
            if(airport.getAirport_name().equalsIgnoreCase(airport_name)) return airport;
        }
        return null;
    }
    public void printAllAirports(){
        if(Company.airports.isEmpty()){
            System.out.println("** Sorry.The airport list is empty **");
        }else{
            System.out.println("......................List of Airports......................");
            for (Airport airport : Company.airports) {
                System.out.println(airport.toString());
            }
        }
    }
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
                switch (option) {
                    case 1:
                        System.out.print("Enter new airport name: ");
                        airport.setAirport_name(sc.next());
                        System.out.println("Airport name changed to " + airport.getAirport_name());
                        break;
                    case 2:
                        System.out.print("Enter new airport location: ");
                        airport.setAirport_location(sc.next());
                        System.out.println("Airport location changed to " + airport.getAirport_location());
                        break;
                    case 3:
                        System.out.print("Enter airport new number of runways: ");
                        airport.setAirport_number_runways(sc.nextInt());
                        System.out.println("Airport number of runways changed to " + airport.getAirport_number_runways());
                        break;
                    case 4:
                        System.out.print("Enter airport new number of gates: ");
                        airport.setAirport_number_gates(sc.nextInt());
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
            sc.next();
        }
    }
    public void deleteAirport(String airport_code){
            Airport airport = getAirportByID(airport_code);
            if(airport != null) {
                Company.airports.remove(airport);
                System.out.println("The airport " + airport.getAirport_name() + " was deleted..");
            }else{
            System.out.println("*** This airport not exists ***");
        }
    }
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
            String airport_code = null;
            if(option >=3 && option <= 5) {
                System.out.print("Enter airport code: ");
                airport_code = sc.next();
            }
            switch (option) {
                case 1:
                    company.addAirport(company);
                    break;
                case 2:
                    controller.printAllAirports();
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
            sc.next();
        }
        return flag;
    }
}
