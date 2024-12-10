package com.hishamfactory;

public class UserController {
    public static User getUserByUserName(String userName){
        for (User user : Company.users) {
            if(user.getUser_name().equalsIgnoreCase(userName)) return user;
        }
        return null;
    }
}
