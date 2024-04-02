package com.hishamfactory;

public class Person {
    private String first_name;
    private String last_name;
    private int age;
    private double tel_number;
    private double id;


    Person(){}
    Person(String first_name,String last_name,int age,double tel_number,double id){
        this.first_name =first_name;
        this.last_name = last_name;
        this.age = age;
        this.tel_number = tel_number;
        this.id = id;
    }
}
