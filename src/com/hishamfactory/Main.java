package com.hishamfactory;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Airline System Management");

        System.out.print("Enter the first name: ");
        String first_name = sc.next();
        System.out.print("Enter the last name: ");
        String last_name = sc.next();
        System.out.print("Enter the age: ");
        int age =sc.nextInt();
        System.out.print("Enter the telefon number: ");
        String tel_number = sc.next();
        System.out.print("Enter the address: ");
        String address = sc.next();
        System.out.println("..................................");

        Employee employee = Company.addEmployee(first_name,last_name,age,tel_number,address);

    }
}
