package com.hishamfactory;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EmployeesController{
    Scanner sc = new Scanner(System.in);

    /**
     * Get employee after check if it's exist
     *
     * @param first_last_name the first and last names of employee
     * @return the employee if it's exist or null if it's not
     */
    public Employee getEmployeeByName(String first_last_name) {
        for (Employee employee : Company.employees) {
            String name = employee.getFirst_name() + employee.getLast_name();
            if (first_last_name.equalsIgnoreCase(name)) return employee;
        }
        return null;
    }

    /**
     * print all employees that exists on system
     */
    public void showAllEmployees() {
        if (Company.employees.isEmpty()) {
            System.out.println("** Sorry,The list of employees is empty **");
        } else {
            System.out.println("..................Employees List............................");
            for (Employee employee : Company.employees) {
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
                System.out.print("Basic Salary: ");
                System.out.println(employee.getNet_salary());
                System.out.print("Address: ");
                System.out.println(employee.getAddress());
                System.out.println(".............................");
            }
        }
    }

    /**
     * Modify any information of employee
     *
     * @param name the name of the employee
     * @param u    the employee who do the modification
     */
    public void editEmployeeInfo(String name, User u) {
        boolean outer_flag = true;
        boolean flag = false;
        User employee;
        while (outer_flag) {
            employee = getEmployeeByName(name);
            if (employee != null) {
                User user = getEmployeeByName(u.getFirst_name() + u.getLast_name());
                if (user.getClass().equals(SuperVisor.class)) flag = true;
                if (!flag) {
                    System.out.println("Sorry, you don't have permission to editing, please contact with your manager.");
                    break;
                }else {
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
                        sc.nextLine();
                        switch (option) {
                            case 1:
                                System.out.print("Enter new first name: ");
                                employee.setFirst_name(sc.next());
                                sc.nextLine();
                                System.out.print("Enter new last name: ");
                                employee.setLast_name(sc.next());
                                sc.nextLine();
                                System.out.println("Name Changed to " + employee.getFirst_name() + " " + employee.getLast_name());
                                break;
                            case 2:
                                System.out.print("Enter new age: ");
                                employee.setAge(sc.nextInt());
                                sc.nextLine();
                                System.out.println("Age changed to " + employee.getAge());
                                break;

                            case 3:
                                System.out.print("Enter new tel number: ");
                                employee.setTel_number(sc.next());
                                sc.nextLine();
                                System.out.println("Tel number changed to " + employee.getTel_number());
                                break;
                            case 4:
                                System.out.print("Enter new role: ");
                                employee.setRole(sc.nextLine());
                                sc.nextLine();
                                System.out.println("Role changed to " + employee.getRole());
                                break;
                            case 5:
                                System.out.print("Enter new address: ");
                                employee.setAddress(sc.nextLine());
                                sc.nextLine();
                                System.out.println("Address changed to " + employee.getAddress());
                                break;
                            case 6:
                                break;
                            default:
                                System.out.println("*** Please enter a valid choice ***");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("*** The input you provided is not valid ***");
                        sc.nextLine();
                    }
                }
            } else {
                System.out.println("*** This employee doesn't exists ***");
            }
            outer_flag = false;

        }
    }

    /**
     * Delete any employee from system
     *
     * @param name the name of employee wanted to delete
     */
    public void deleteEmployee(String name,User user) {
        Employee employee = getEmployeeByName(name);
        if (employee != null) {
            employee.messages.add("Now You are unable to access your privileges as "+employee.getRole()+".Thank you to work us we hope the best for you");
            if (!user.getUuid().equals(employee.getUuid()) && !employee.getClass().equals(SuperVisor.class)) {
                if (user.getClass().equals(SuperVisor.class)) {
                    Company.users.remove(employee);
                    Company.employees.remove(employee);
                    System.out.println(employee.getRole() +" " +employee.getFirst_name() + ","+employee.getLast_name() +" was deleted");
                }
                else if (user.getClass().equals(Manager.class)) {
                    Company.users.remove(employee);
                    Company.employees.remove(employee);
                    System.out.println(employee.getRole() +" " +employee.getFirst_name() + ","+employee.getLast_name() +" was deleted");
                }
                else if (user.getClass().equals(Director.class) && !employee.getClass().equals(Manager.class)) {
                    Company.users.remove(employee);
                    Company.employees.remove(employee);
                    System.out.println(employee.getRole() +" " +employee.getFirst_name() + ","+employee.getLast_name() +" was deleted");
                }else{
                    System.out.println("*** You don't have permission to delete this account.Please contact with your manager ***");
                }
            } else {
                System.out.println("*** You can't delete this account ***");
            }
        } else {
            System.out.println("*** This account doesn't exists ***");
        }
    }

    /**
     * Process of firing any employee
     *
     * @param name the name of employee wanted to fire
     */
    public void fireEmployee(String name,User user) {
        Employee employee = getEmployeeByName(name);
        if (employee != null) {
            if (employee.getClass().equals(SuperVisor.class)) {
                System.out.println("*** You can't delete this account ***");
            } else {
                user.setStatus("non-active");
                System.out.println("Status: " + user.getStatus());
                //Notify Relevant Parties
                System.out.println("Send to relevant parties...done");
                //Revoke Access Privileges
                deleteEmployee(name, user);
                System.out.println("Delete Access Privileges...done");
            }
        } else {
            System.out.println("*** This account doesn't exists ***");
        }
    }

    /**
     * show employee menu to do any employee's operation
     *
     * @param company    the company who has the system
     * @param controller the controller object whose do operation by it
     * @param user       the employee who do the operation
     * @return the boolean value stay on employee menu or go out from it
     */
    public boolean showEmployeeMenu(Company company, EmployeesController controller, User user) {
        boolean flag = true;
        try {
            System.out.println(".......................Employee Menu..........................");
            System.out.println("1.Add new employee");
            System.out.println("2.Get employee by name");
            System.out.println("3.Show all employees");
            System.out.println("4.Calculate employee salary");
            System.out.println("5.Edit employee info");
            System.out.println("6.Delete employee");
            System.out.println("7.Fire employee");
            System.out.println("8.Quit");
            System.out.print("Enter a choice: ");
            int option = sc.nextInt();
            sc.nextLine();
            String name = "";
            if (option != 1 && option != 3 && option != 8) {
                System.out.print("Enter employee first name: ");
                name = sc.next();
                sc.nextLine();
                System.out.print("Enter employee last name: ");
                name += sc.next();
                sc.nextLine();
            }
            switch (option) {
                case 1:
                    company.addEmployee(company, user);
                    break;
                case 2:
                    User user1 = controller.getEmployeeByName(name);
                    if (user1 != null) {
                        System.out.println(user1);
                    } else {
                        System.out.println("*** This employee don't exists ***");
                    }
                    break;
                case 3:
                    controller.showAllEmployees();
                    break;
                case 4:
                    Employee employee = getEmployeeByName(name);
                    if(employee != null){
                        System.out.println(employee.getFirst_name()+","+employee.getLast_name() + "'s net salary is " + employee.calculateSalary()+"$");
                    }else{
                        System.out.println("*** This employee doesn't exists ***");
                    }
                case 5:
                    controller.editEmployeeInfo(name, user);
                    break;
                case 6:
                    controller.deleteEmployee(name,user);
                    break;
                case 7:
                    controller.fireEmployee(name,user);
                    break;
                default:
                    flag = false;
                    break;
            }
        } catch (NoSuchElementException e) {
            System.out.println(" *** Please enter a valid choice *** ");
            sc.nextLine();
        }
        return flag;
    }
}
