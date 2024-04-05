package com.hishamfactory;
public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.addNewEmployee();
        employee.addNewEmployee();
        employee.addNewEmployee();
        employee.printAllEmployees();

        employee.editEmployeeInfo("Hisham Mohammed");
        employee.deleteEmployee("Okan Ali");
        Passenger.printAllPassengers();
    }
}
