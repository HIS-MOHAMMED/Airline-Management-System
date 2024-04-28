package com.hishamfactory;

import jdk.dynalink.NamedOperation;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class PassengersController {
    Scanner sc = new Scanner(System.in);
    public static Passenger getPassengerByName(String first_last_name){
        int i = 0;
        while(i < Company.passengers.size()){
            String name = Company.passengers.get(i).getFirst_name()+ " " + Company.passengers.get(i).getLast_name();
            if(first_last_name.equals(name)){
                return Company.passengers.get(i);
            }
            i++;
        }
        return null;
    }
    public  void printAllPassengers(){
        System.out.println("................................................................");
        for(Passenger passenger : Company.passengers){
            System.out.println(passenger.toString());
        }
        System.out.println("................................................................");
    }
    public void editPassengerInfo(String name) {
        try {
            Passenger passenger = getPassengerByName(name);
            String passenger_name = passenger.getFirst_name() + " " + passenger.getLast_name();
                if (passenger_name.equals(name)) {
                    System.out.println("1.Edit Name");
                    System.out.println("2.Edit Age");
                    System.out.println("3.Edit Tel Number");
                    System.out.println("4.Edit Id");
                    System.out.println("5.Quit");
                    System.out.print("select option: ");
                    int option = sc.nextInt();
                    switch (option) {
                        case 1:
                            System.out.print("Enter new first name: ");
                            passenger.setFirst_name(sc.next());
                            System.out.print("Enter new last name: ");
                            passenger.setLast_name(sc.next());
                            System.out.println("Name Changed");
                            break;
                        case 2:
                            System.out.print("Enter new age: ");
                            passenger.setAge(sc.nextInt());
                            System.out.println("Age changed");
                            break;

                        case 3:
                            System.out.print("Enter new tel number: ");
                            passenger.setTel_number(sc.next());
                            System.out.println("Tel number changed");
                            break;
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("Input not found. Please enter text without spaces");
                sc.next();
            } catch (NullPointerException e) {
                System.out.println("A NullPointerException occurred.System will exit");
                System.exit(0);
            }
        }
    public void deletePassenger(String name){
        try {
            Passenger passenger = getPassengerByName(name);
            Company.passengers.remove(passenger);
        }catch (NullPointerException e){
            System.out.println("A NullPointerException occurred.System will exit");
            System.exit(0);
        }
    }
    public boolean showPassengerMenu(Company company,PassengersController controller) {
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
        }catch (NoSuchElementException e){
            System.out.println("Input not found. Please enter text without spaces");
            sc.next();
        }catch (NullPointerException e){
            System.out.println("A NullPointerException occurred.System will exit");
            System.exit(0);
        }
        return flag;
    }
    public static boolean checkPassengerNotExist(String name){
        for (Passenger passenger : Company.passengers) {
            String passenger_name = passenger.getFirst_name() + " " +passenger.getLast_name();
            if(passenger_name.equalsIgnoreCase(name)){
                return false;
            }
        }
        return true;
    }
}
