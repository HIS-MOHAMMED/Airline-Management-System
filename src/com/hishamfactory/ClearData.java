package com.hishamfactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public abstract class ClearData {
    public void clearDataFromFile(String path_name) throws IOException {
        try(ObjectOutputStream employeesOutput = new ObjectOutputStream(new FileOutputStream(path_name))){
        }
    }
}
