package com.hishamfactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {

    Company turkishAirlinesCompany;
    Manager turkishAirlinesCompanyManager;

    @BeforeEach
    public void setup(){
        turkishAirlinesCompany = new Company("Turkish-Airlines");
        turkishAirlinesCompanyManager = new Manager("hisham","mohammed","hisham1234",24,"02384934","Turkiye","Manager",5000.0,"1234",turkishAirlinesCompany);
    }

    @Test
    public void returnedTurkishAirlinesCompanyNameTrue(){
        assertEquals("Turkish-Airlines", turkishAirlinesCompany.getName());
    }

    @Test
    public void employeeLoginByUserNameORUuidSame(){
        assertSame(turkishAirlinesCompanyManager,turkishAirlinesCompany.employeeLoginByUserNameOrId("hisham1234","1234"));
    }

    @Test
    public void employeeLoginByUserNameORUuidNotSame(){
        assertNotSame(turkishAirlinesCompanyManager,turkishAirlinesCompany.employeeLoginByUserNameOrId("hisham1234","12345"));
    }




}
