package com.hishamfactory;

public class LogsController {
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
}
