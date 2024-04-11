package com.hishamfactory;

import java.util.Scanner;

public class PassengersController {
    Scanner sc = new Scanner(System.in);
    public Passenger getPassengerByName(String first_last_name){
        int i = 0;
        while(i < Company.passengers.size()){
            String name = Company.passengers.get(i).getFirst_name()+ " " + Company.passengers.get(i).getLast_name();
            if(first_last_name.equals(name)){
                return Company.passengers.get(i);
            }
            i++;
        }
        return null;//must handle nullPointerException when using this method....
    }
    public  void printAllPassengers(){
        System.out.println(".......................");
        for(Passenger passenger : Company.passengers){
            System.out.println("Name: " + passenger.getFirst_name() + passenger.getLast_name());
            System.out.print("Age" + passenger.getUuid());
        }
        System.out.println("........................");
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
                }
            }
        }catch (Exception e){
            System.out.println("The given value is null.check it");
        }
    }
    public void deletePassenger(String name){
        Passenger passenger = getPassengerByName(name);
        Company.passengers.remove(passenger);
    }

}
