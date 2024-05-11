package com.hishamfactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class User extends Person implements Identifiable {
    protected String uuid;
    protected byte[] pinHash;
    User(String first_name, String last_name, int age, String tel_number,String address,String role, String person_pin, Company company){
        super(first_name,last_name,age,tel_number,address,role);
        try{
            MessageDigest md =MessageDigest.getInstance("SHA-256");
            this.pinHash =md.digest(person_pin.getBytes(StandardCharsets.UTF_8));
        }catch (NoSuchAlgorithmException e){
            System.err.println("error, caught NoSuchAlgorithmException.");
            e.printStackTrace();
            System.exit(0);
        }
        this.uuid = company.getNewUUID();
    }
    User(String first_name, String last_name, int age, String tel_number,String address, String person_pin, Company company){
        super(first_name,last_name,age,tel_number,address);
        try{
            MessageDigest md =MessageDigest.getInstance("SHA-256");
            this.pinHash =md.digest(person_pin.getBytes(StandardCharsets.UTF_8));
        }catch (NoSuchAlgorithmException e){
            System.err.println("error, caught NoSuchAlgorithmException.");
            e.printStackTrace();
            System.exit(0);
        }
        this.uuid = company.getNewUUID();
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Sets the hash value of the person's password using SHA-256 encryption.
     * @param person_pin The PIN of the person to be hashed.
     */
    public void setPinHash(String person_pin){
        try{
            MessageDigest md =MessageDigest.getInstance("SHA-256");
            this.pinHash =md.digest(person_pin.getBytes(StandardCharsets.UTF_8));
        }catch (NoSuchAlgorithmException e){
            System.err.println("error, caught NoSuchAlgorithmException.");
            e.printStackTrace();
            System.exit(0);
        }
    }
    /**
     *Check whether a given password matches the UserRoot password or not
     * @param user_pin  the password of the UserRoot
     * @return              whether the password is valid or not
     */
    public boolean validatePin(String user_pin){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return MessageDigest.isEqual(md.digest(user_pin.getBytes(StandardCharsets.UTF_8)),this.pinHash);
        }catch (NoSuchAlgorithmException e){
            System.out.println("error, caught NoSuchAlgorithmException..");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }
    public byte[] getPinHash() {
        return pinHash;
    }

    @Override
    public String identify() {
        return this.uuid;
    }
}
