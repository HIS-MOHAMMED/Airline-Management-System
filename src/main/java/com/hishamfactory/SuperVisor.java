package com.hishamfactory;

import java.io.Serializable;

public class SuperVisor extends RootUser implements Serializable {
    private static final long serialVersionUID = 1L;
    SuperVisor(String first_name,String last_name,String user_name,int age,String tel_number,String address,String role,double basic_salary,String SuperVisor_pin,Company company){
        super(first_name,last_name,user_name,age,tel_number,address,role,basic_salary,SuperVisor_pin,company);
        this.permissionLevel = 10;
        Company.users.add(this);
        Company.employees.add(this);
        Company.permissions_editing_uuids.add(this.getUuid());
    }
    SuperVisor(String uuid,String first_name,String last_name,String user_name,int age,String tel_number,String address,byte[] SuperVisor_pin,String role,double basic_salary,Company company){
        super(uuid,first_name,last_name,user_name,age,tel_number,address,SuperVisor_pin,role,basic_salary,company);
        this.permissionLevel = 10;
        Company.permissions_editing_uuids.add(this.getUuid());
    }
}
