package com.hishamfactory;

import java.util.Scanner;

public abstract class FullTimeEmployee extends Employee{
    protected double bonuses;
    protected double insurance_premium;
    Scanner sc = new Scanner(System.in);
    public FullTimeEmployee(String first_name,String last_name,String user_name,int age,String tel_number,String address,String role,double basic_salary,String employee_pin,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,employee_pin,company);
        setTax((double) 10 / 100);
    }
    public double getBonuses() {
        return bonuses;
    }

    public void setBonuses(double bonuses) {
        double official_bonuses_amount = 50.0;
        if(official_bonuses_amount > bonuses){
            System.out.println("*** Since given bonuses less than default, the default amount (50.0) applied ***");
            this.bonuses = official_bonuses_amount;
        }else{
            this.bonuses =bonuses;
        }
    }

    public double getInsurance_premium() {
        return insurance_premium;
    }

    public void setInsurance_premium(double insurance_premium) {
        double minimal_insurance_premium = 300.0;
        if(minimal_insurance_premium > insurance_premium){
            System.out.println("*** Since given amount less than default, the default amount (300.0) applied ***");
            this.insurance_premium = minimal_insurance_premium;
        }else{
            this.insurance_premium  = insurance_premium;
        }
    }

    @Override
    public double calculateSalary() {
            System.out.print("Enter employee bonuses: ");
            this.setBonuses(sc.nextDouble());
            sc.nextLine();
            System.out.print("Enter insurance_premium: ");
            this.setInsurance_premium(sc.nextDouble());
            sc.nextLine();
            double total_tax = this.getBasic_salary() * this.getTax();
            double net_salary = this.getBasic_salary() + this.getBonuses() + getInsurance_premium() - total_tax;
            this.setNet_salary(net_salary);
            return this.getNet_salary();
    }
}
