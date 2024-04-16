package com.hishamfactory;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AirportController {
    Scanner sc = new Scanner(System.in);
    public static Airport getAirportByID(String code){
            for (Airport airport : Company.airports) {
                if(airport.getAirport_code().equals(code)){
                    return airport;
                }
            }
        return null;
    }
    public void printAllAirports(){
        for (Airport airport : Company.airports) {
            System.out.println(airport.toString());
        }
    }
    public void editAirport(){
        try {
            System.out.print("Enter airport code: ");
            Airport airport = getAirportByID(sc.next());
            System.out.print("1.Edit airport Name");
            System.out.println();
            System.out.print("2.Edit airport Location");
            System.out.println();
            System.out.print("3.Edit airport Number of Runways");
            System.out.println();
            System.out.print("4.Edit airport Number of Gates");
            System.out.println();
            System.out.print("Enter a choice? ");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter new airport name: ");
                    airport.setAirport_name(sc.next());
                    System.out.println("Airport name changes..");
                    break;
                case 2:
                    System.out.print("Enter new airport location: ");
                    airport.setAirport_location(sc.next());
                    System.out.println("Airport location changed..");
                    break;
                case 3:
                    System.out.print("Enter airport new number of runways: ");
                    airport.setAirport_number_runways(sc.nextInt());
                    System.out.println("Airport number of runways changed..");
                    break;
                case 4:
                    System.out.print("Enter airport new number of gates: ");
                    airport.setAirport_number_gates(sc.nextInt());
                    System.out.println("Airport number of gates changed..");
                    break;
                default:
                    System.out.println("You choose wrong number");
                    break;
            }
        }catch (NullPointerException e){
            System.out.println("A NullPointerException occurred.System will exit");
            System.exit(0);
        }catch (NoSuchElementException e){
            System.out.println("Input not found. Please enter text without spaces");
            sc.next();
        }
    }
    public void deleteAirport(){
        try {
            System.out.print("Enter airport code: ");
            Airport airport = getAirportByID(sc.next());
            Company.airports.remove(airport);
            System.out.println("The airport " + airport.getAirport_name() + " was deleted..");
        }catch (NullPointerException e){
            System.out.println("A NullPointerException occurred.System will exit");
            e.printStackTrace();
        }
    }
    public void showAirportMenu(Company company,AirportController controller) {
        try {
            System.out.println(".....................Airport Menu..........................");
            System.out.println("1.Add new airport");
            System.out.println("2.Show all airport");
            System.out.println("3.Edit airport info");
            System.out.println("4.Delete airport\n");
            System.out.print("Enter a choice: ");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    company.addAirport(company);
                    break;
                case 2:
                    controller.printAllAirports();
                    break;
                case 3:
                    controller.editAirport();
                    break;
                case 4:
                    controller.deleteAirport();
                    break;
            }
        }catch (NullPointerException e){
            System.out.println("A NullPointerException occurred.System will exit");
            System.exit(0);
        }catch (NoSuchElementException e){
            System.out.println("Input not found. Please enter text without spaces");
            sc.next();
        }
    }
}
