package com.hishamfactory;
import com.sun.security.jgss.GSSUtil;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class EmployeesController{
    Scanner sc = new Scanner(System.in);
    public Employee getEmployeeByName(String first_last_name){
        for (Employee employee1 : Company.employees) {
            String name = employee1.getFirst_name()+employee1.getLast_name();
            if(first_last_name.equalsIgnoreCase(name))return employee1;
        }
        return null;
    }
    public  void printAllEmployees(){
        if(Company.employees.isEmpty()){
            System.out.println("** Sorry,The list of employees is empty **");
        }else{
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
    }
    public void editEmployeeInfo(String name,Person u) {
        boolean outer_flag = true;
        boolean flag = false;
        Employee employee;
        while (outer_flag) {
                employee = getEmployeeByName(name);
                if(employee != null) {
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
                    try {
                        System.out.println(".....................Editing Menu....................");
                        System.out.println("1.Edit Name");
                        System.out.println("2.Edit Age");
                        System.out.println("3.Edit Tel Number");
                        System.out.println("4.Edit Role");
                        System.out.println("5.Edit Address");
                        System.out.println("6.Quit");
                        System.out.print("select option: ");
                        int option = sc.nextInt();
                        switch (option) {
                            case 1:
                                System.out.print("Enter new first name: ");
                                employee.setFirst_name(sc.next());
                                System.out.print("Enter new last name: ");
                                employee.setLast_name(sc.next());
                                System.out.println("Name Changed to " + employee.getFirst_name() + " " + employee.getLast_name());
                                break;
                            case 2:
                                System.out.print("Enter new age: ");
                                employee.setAge(sc.nextInt());
                                System.out.println("Age changed to " + employee.getAge());
                                break;

                            case 3:
                                System.out.print("Enter new tel number: ");
                                employee.setTel_number(sc.next());
                                System.out.println("Tel number changed to " + employee.getTel_number());
                                break;
                            case 4:
                                System.out.print("Enter new role: ");
                                employee.setRole(sc.next());
                                System.out.println("Role changed to " + employee.getRole());
                                break;
                            case 5:
                                System.out.print("Enter new address: ");
                                employee.setAddress(sc.next());
                                System.out.println("Address changed to " + employee.getAddress());
                                break;
                            case 6:

                                break;
                            default:
                                System.out.println("*** Please enter a valid choice ***");
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("Input not found. Please enter text without spaces");
                        sc.next();
                    }
                }else {
                    System.out.println("*** This employee doesn't exists ***");
                }
            outer_flag = false;

        }
    }
    public void deleteEmployee(String name){
        if(Company.administrators.size() == 1){
            System.out.println("*** You can't delete this account***");
        }else{
            Employee employee = getEmployeeByName(name);
            if(employee != null){
                if(Company.administrators.contains(employee)){
                    Company.administrators.remove(employee);
                    Company.employees.remove(employee);
                }else{
                    Company.customerServices.remove(employee);
                    Company.employees.remove(employee);
                }
            }else{
                System.out.println("*** This account doesn't exists ***");
            }
        }
    }
    public void fireEmployee(String name){
        Employee employee = getEmployeeByName(name);
        if(Company.administrators.size() == 1){
            System.out.println("*** You can't delete this account ***");
        }else {
            if (employee != null) {
                employee.setStatus("non-active");
                System.out.println("Status: " + employee.getStatus());
                //Notify Relevant Parties
                System.out.println("Send to relevant parties...done");
                //Revoke Access Privileges
                deleteEmployee(name);
                System.out.println("Delete Access Privileges...done");
            } else {
                System.out.println("*** This account doesn't exists ***");
            }
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
                    if(employee != null){
                        System.out.println(employee);
                    }else {
                        System.out.println("*** This employee don't exists ***");
                    }
                    break;
                case 3:
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
