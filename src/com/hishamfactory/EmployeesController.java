package com.hishamfactory;

import java.util.Scanner;

public class EmployeesController {
    Scanner sc = new Scanner(System.in);
    public Employee getEmployeeByName(String first_last_name){
        int i = 0;
        while(i < Company.employees.size()){
            String name = Company.employees.get(i).getFirst_name()+ " " + Company.employees.get(i).getLast_name();
            if(first_last_name.equals(name)){
                return Company.employees.get(i);
            }
            i++;
        }
        return null;
    }
    public void printAllEmployees(){
        for(Employee employee : Company.employees){
            System.out.print("Name: ");
            System.out.println(employee.getFirst_name() + " " + employee.getLast_name());
            System.out.println();
            System.out.print("Age:");
            System.out.println(employee.getAge());
            System.out.println();
            System.out.print("Tel Number: ");
            System.out.println(employee.getTel_number());
            System.out.println();
            System.out.print("Id: ");
            System.out.println(employee.getUuid());
            System.out.print("Address: ");
            System.out.println(employee.getAddress());
            System.out.println(".........................................................");
        }
    }
    public void editEmployeeInfo(String name){
        Employee passenger = getEmployeeByName(name);
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
    public void deleteEmployee(String name){
        Company.employees.remove(getEmployeeByName(name));
    }
    public void fireEmployee(String name){
        //Update Employee Status
        Employee employee = getEmployeeByName(name);
        employee.setStatus("Active");
        System.out.println("Status: " + employee.getStatus());
        //Notify Relevant Parties
        System.out.println("Send to relevant parties...done");
        //Revoke Access Privileges
        System.out.println("Revoke Access Privileges...done");
    }


}
