package com.hishamfactory;

import java.util.ArrayList;

public class Employee extends  Person {
    private String address;
    Employee(String first_name,String last_name,int age,String tel_number,String id,String address){
        super(first_name,last_name,age,tel_number,id);
        this.address = address;
    }
    public void addNewEmployee(){}
    public void getEmployeeByName(String first_last_name){}
    public void printAllEmployees(ArrayList<Employee> employees){}
    public void editEmployeeInfo(double id){}
    public void deleteEmployee(double id){}
    public void fireEmployee(double id){}

}
