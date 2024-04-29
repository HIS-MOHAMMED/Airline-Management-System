package com.hishamfactory;

import com.sun.security.jgss.GSSUtil;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentMap;

public class EmployeesController{
    Scanner sc = new Scanner(System.in);
    public Employee getEmployeeByName(String first_last_name){
        int i = 0;
        while(i < Company.employees.size()){
            String name = Company.employees.get(i).getFirst_name()+Company.employees.get(i).getLast_name();
            if(first_last_name.equals(name)){
                return Company.employees.get(i);
            }
            i++;
        }
        return null;
    }
    public  void printAllEmployees(){
        System.out.println("..................Employees List............................");
        for(Employee employee : Company.employees){
            System.out.print("Name: ");
            System.out.println(employee.getFirst_name() + " " + employee.getLast_name());
            System.out.print("Age:");
            System.out.println(employee.getAge());
            System.out.print("Tel Number: ");
            System.out.println(employee.getTel_number());
            System.out.print("Id: ");
            System.out.println(employee.getUuid());
            System.out.print("Role: ");
            System.out.println(employee.getRole());
            System.out.print("Address: ");
            System.out.println(employee.getAddress());
            System.out.println(".............................");
        }
    }
    public void editEmployeeInfo(String name,Person u) {
        boolean outer_flag = true;
        boolean flag = false;
        Employee employee = null;
        while (outer_flag) {
            try {
                employee = getEmployeeByName(name);
                Employee user = getEmployeeByName(u.getFirst_name() + u.getLast_name());
                for (String permissionsUuid : Company.permissions_uuids) {
                    if (user.uuid.equals(permissionsUuid)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Sorry, you don't have permission to editing, please contact with your manager.");
                    break;
                }
            } catch (NullPointerException e) {
                System.out.println("***This account doesn't exists***");
                break;
            }
            try {
                System.out.println(".....................Editing Menu....................");
                System.out.println("1.Edit Name");
                System.out.println("2.Edit Age");
                System.out.println("3.Edit Tel Number");
                System.out.println("4.Edit Id");
                System.out.println("5.Quit");
                System.out.print("select option: ");
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.print("Enter new first name: ");
                        employee.setFirst_name(sc.next());
                        System.out.print("Enter new last name: ");
                        employee.setLast_name(sc.next());
                        System.out.println("Name Changed");
                        break;
                    case 2:
                        System.out.print("Enter new age: ");
                        employee.setAge(sc.nextInt());
                        System.out.println("Age changed");
                        break;

                    case 3:
                        System.out.print("Enter new tel number: ");
                        employee.setTel_number(sc.next());
                        System.out.println("Tel number changed");
                        break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Input not found. Please enter text without spaces");
                sc.next();
            }
            outer_flag = false;
        }
    }
    public void deleteEmployee(String name){
        Employee employee = getEmployeeByName(name);
        if(employee != null){
            Company.employees.remove(employee);
        }else{
            System.out.println("*** This account doesn't exists ***");
        }
    }
    public void fireEmployee(String name){
        //Update Employee Status
        try {
            Employee employee = getEmployeeByName(name);
            employee.setStatus("non-active");
            System.out.println("Status: " + employee.getStatus());
            //Notify Relevant Parties
            System.out.println("Send to relevant parties...done");
            //Revoke Access Privileges
            System.out.println("Revoke Access Privileges...done");
        }catch (NullPointerException e){
            System.out.println("***This account doesn't exists");
        }
    }
    public boolean showEmployeeMenu(Company company,EmployeesController controller,Person user) {
        boolean flag = true;
        try {
            System.out.println(".......................Employee Menu..........................");
            System.out.println("1.Add new employee");
            System.out.println("2.Get employee by name");
            System.out.println("3.Show all employees");
            System.out.println("4.Edit employee info");
            System.out.println("5.Delete employee");
            System.out.println("6.Fire employee");
            System.out.println("7.Quit");
            System.out.print("Enter a choice: ");
            int option = sc.nextInt();
            String name = "";
            if (option != 1 && option != 3 && option != 7) {
                System.out.print("Enter employee first name: ");
                name = sc.next();
                System.out.print("Enter employee last name: ");
                name += sc.next();
            }
            switch (option) {
                case 1:
                    company.addEmployee(company);
                    break;
                case 2:
                    Employee employee = controller.getEmployeeByName(name);
                    System.out.println(employee.toString());
                    break;
                case 3:
                    System.out.println("show all employees: ");
                    controller.printAllEmployees();
                    break;
                case 4:
                    controller.editEmployeeInfo(name,user);
                    break;
                case 5:
                    controller.deleteEmployee(name);
                    break;
                case 6:
                    controller.fireEmployee(name);
                    break;
                default:
                    flag = false;
                    break;
            }
        }catch (NoSuchElementException e){
            System.out.println("Input not found. Please enter text without spaces");
            sc.next();
        }
        return  flag;
    }
}
