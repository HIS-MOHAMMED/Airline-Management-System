package com.hishamfactory;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class LoginHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    private String date_and_time;
    private User user;
    public LoginHistory(String date_and_time, User user){
        this.date_and_time = date_and_time;
        this.user = user;
        Company.logs.add(this);
    }

    public String getDate_and_time() {
        return date_and_time;
    }
    public User getUser() {
        return user;
    }

}
