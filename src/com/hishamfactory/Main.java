package com.hishamfactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc  =new Scanner(System.in);
        Airport airport = new Airport();
        airport.addNewAirport();
        airport.addNewAirport();
        airport.addNewAirport();

        airport.printAllAirports();
        System.out.print("Enter airport id to editing: ");
        airport.editAirport(sc.next());
        System.out.print("Enter airport to delete: ");
        airport.deleteAirport(sc.next());
    }
}
