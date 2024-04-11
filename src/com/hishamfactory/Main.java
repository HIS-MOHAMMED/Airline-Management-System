package com.hishamfactory;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println(".....................................................................................................");
            System.out.println("Welcome to Airline System Management");
            Company company = new Company("Turkish Airline");
            System.out.println("......................................................................................................");
            Employee employee = new Employee("Hisham","Mohammed",20,"3947389473","Yemen","Admin","1234",company);
            Company.employees.add(employee);
//            if(Company.employees.isEmpty()){
//                System.out.println("As first employee you have to create Admin user");
//                company.addEmployee(company);
//                System.out.println("Thank you.Admin user has created");
//            }
            Person person = showLoginMenu(company,sc);
            showUserMenu(company,person,sc);
        }while (true);
    }
    public static Employee showLoginMenu(Company company,Scanner sc){
       Employee employeeAuth = null;
       try {
           do{
               System.out.print("Enter  ID: ");
               String employee_id = sc.next();
               System.out.print("Enter  password: ");
               String employee_pin = sc.next();

               employeeAuth =company.employeeLogin(employee_id,employee_pin);
               if(employeeAuth == null){
                   System.out.println("ID or password incorrect.please try again");
               }
           }while (employeeAuth == null);
       }catch (NullPointerException e){
           System.out.println("There is no person had created");
       }
        return employeeAuth;
    }
    public static void showUserMenu(Company company,Person person,Scanner sc){
        System.out.println("................................................");
        System.out.println(person.getFirst_name() +", "+ person.getLast_name() + ".Welcome to " + company.getName());
        System.exit(1);
    }
}
