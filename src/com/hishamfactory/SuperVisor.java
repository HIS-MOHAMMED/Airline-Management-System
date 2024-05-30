package com.hishamfactory;

public class SuperVisor extends RootUser {
    SuperVisor(String first_name,String last_name,String user_name,int age,String tel_number,String address,String role,double basic_salary,String SuperVisor_pin,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,SuperVisor_pin,company);
        Company.users.add(this);
        Company.employees.add(this);
        Company.permissions_editing_uuids.add(this.getUuid());
    }
}
