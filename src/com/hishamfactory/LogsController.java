package com.hishamfactory;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LogsController extends ClearData {
    /**
     * Print all login history since the system starts
     */
    public static void printLoginHistory(){
        System.out.println("..............................Login History.............................");
        System.out.println("    Date and Time       |       User");
        for (LoginHistory history : Company.logs) {
            System.out.println(" " +history.getDate_and_time()+"     |      "+history.getUser().getFirst_name()+" "+history.getUser().getLast_name());
        }
    }
    public boolean showLogsMenu() {
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("...............................Coupon History.............................");
        System.out.println("1.Show Login History");
        System.out.println("2.Clear coupons file");
        System.out.println("3.Quit");
        System.out.print("Enter a option: ");
        try{
            int option = sc.nextInt();
            switch (option) {
                case 1 :
                    printLoginHistory();
                    break;
                case 2:
                    clearDataFromFile("DataFiles/logs.dat");
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter valid option");
            }
        }catch (InputMismatchException ex){
            System.out.println("*** Your input mismatch whats excepted, please enter valid input ***");
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return flag;
    }
}
