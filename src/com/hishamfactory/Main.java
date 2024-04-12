package com.hishamfactory;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Company company = new Company("Turkish Airline");
        Employee employee = new Employee("Hisham","Mohammed",20,"3947389473","Yemen","Admin","1234",company);
        Company.employees.add(employee);
        boolean flag = true;
        do{
            System.out.println(".....................................................................................................");
            System.out.println("Welcome to Airline System Management");
            System.out.println("......................................................................................................");
//            if(Company.employees.isEmpty()){
//                System.out.println("As first employee you have to create Admin user");
//                company.addEmployee(company);
//                System.out.println("Thank you.Admin user has created");
//            }
            Person person = showLoginMenu(company,sc);
            while(flag){
                flag = showUserMenu(company,person,sc);
            }
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
    public static boolean showUserMenu(Company company,Person person,Scanner sc){
        boolean flag = true;
        System.out.println();
        System.out.println("................................................");
        System.out.println(person.getFirst_name() +", "+ person.getLast_name() + ".Welcome to " + company.getName());
        System.out.println("1.Employees");
        System.out.println("2.Passengers");
        System.out.println("3.Planes");
        System.out.println("4.Airports");
        System.out.println("5.Flights");
        System.out.println("6.Quit");
        System.out.print("Enter a choice: ");
        int option = sc.nextInt();
        String name ="";
        switch (option){
            case 1:
                EmployeesController controller = new EmployeesController();
                controller.showEmployeeMenu(company,controller);
                break;
            case 2:
                PassengersController controller1 = new PassengersController();
                controller1.showPassengerMenu(company,controller1);
                break;
            case 3:
                PlaneController controller2 = new PlaneController();
                controller2.showPlaneMenu(company,controller2);
                break;
            case 4:
                AirportController controller3 = new AirportController();
                controller3.showAirportMenu(company,controller3);
                break;
            case 5:
                FlightController controller4 = new FlightController();
                controller4.showFlightMenu(company,controller4);
                break;
            case 6:
                flag = false;
                break;
        }
        return flag;
    }
}
