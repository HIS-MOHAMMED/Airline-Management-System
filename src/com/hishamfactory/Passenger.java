package com.hishamfactory;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Scanner;

public class Passenger extends  Person {
    private  static ArrayList<Passenger> passengers = new ArrayList<>();
    private int test;
   static Scanner sc = new Scanner(System.in);
    Passenger(){}
    Passenger(String first_name,String last_name,int age,String tel_number,String id){
        super(first_name,last_name,age,tel_number,id);
        //System.out.println("New Passenger Add");
    }
    public static void addNewPassenger(){
        System.out.print("Enter the first name: ");
        String first_name = sc.next();
        System.out.print("Enter the last name: ");
        String last_name = sc.next();
        System.out.print("Enter the age: ");
        int age =sc.nextInt();
        System.out.print("Enter the telefon number: ");
        String tel_number = sc.next();
        System.out.print("Enter the id: ");
        String id = sc.next();
        System.out.println("..................................");
        Passenger newPassenger = new Passenger(first_name,last_name,age,tel_number,id);
        passengers.add(newPassenger);

    }
    public Passenger getPassengerByName(String first_last_name){
        int i = 0;
        while(i < passengers.size()){
            String name = passengers.get(i).getFirst_name()+ " " + passengers.get(i).getLast_name();
            if(first_last_name.equals(name)){
                return passengers.get(i);
            }
            i++;
        }
        return null;//must handle nullPointerException when using this method....
    }
    public static void printAllPassengers(){
        for(Passenger passenger : passengers){
            System.out.print("Name: ");
            System.out.println(passenger.getFirst_name() + " " + passenger.getLast_name());
            System.out.println();
            System.out.print("Age:");
            System.out.println(passenger.getAge());
            System.out.println();
            System.out.print("Tel Number: ");
            System.out.println(passenger.getTel_number());
            System.out.println();
            System.out.print("Id: ");
            System.out.println(passenger.getId());
            System.out.println(".........................................................");
        }
    }
    public void editPassengerInfo(String name){
            Passenger passenger = getPassengerByName(name);
            String passenger_name = passenger.getFirst_name() +" " + passenger.getLast_name();
            try {
                if(passenger_name.equals(name)){
                    System.out.println("1.Edit Name");
                    System.out.println("2.Edit Age");
                    System.out.println("3.Edit Tel Number");
                    System.out.println("4.Edit Id");
                    System.out.println("5.Quit");
                    System.out.print("select option: ");
                    int option = sc.nextInt();
                    switch (option){
                        case  1:
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
                            System.out.print("Enter new telefon number: ");
                            passenger.setTel_number(sc.next());
                            System.out.println("Telefon number changed");
                            break;

                        case 4:
                            System.out.print("Enter new Id: ");
                            passenger.setId(sc.next());
                            System.out.println("Id changed");
                            break;
                    }
                }
            }catch (Exception e){
                System.out.println("The given value is null.check it");
            }
        }
    public void deletePassenger(String name){
        Passenger passenger = getPassengerByName(name);
        passengers.remove(passenger);

    }
}
