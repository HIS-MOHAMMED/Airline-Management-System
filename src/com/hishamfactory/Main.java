package com.hishamfactory;
public class Main {
    public static void main(String[] args) {
        Passenger passenger = new Passenger();
        Passenger.addNewPassenger();
        Passenger.addNewPassenger();
        Passenger.addNewPassenger();
        Passenger.printAllPassengers();

        passenger.editPassengerInfo("Hisham Mohammed");
        passenger.deletePassenger("Okan Ali");
        Passenger.printAllPassengers();


    }
}
