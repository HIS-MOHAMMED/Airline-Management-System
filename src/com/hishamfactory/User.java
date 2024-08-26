package com.hishamfactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public abstract class User extends Person implements Identifiable {
    protected String uuid;
    protected byte[] pinHash;
    protected  String user_name;
    protected ArrayList<String> messages;
    protected boolean isNewMessages;
    User(String first_name, String last_name,String user_name, int age, String tel_number,String address,String person_pin,String role, Company company){
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
        this.user_name = user_name;
        messages = new ArrayList<>();
        isNewMessages = false;
    }
    /*
    constructor to create users objects when reading from files
     */
    User(String uuid,String first_name, String last_name,String user_name, int age, String tel_number,String address, byte[] person_pin,String role, Company company){
        super(first_name,last_name,age,tel_number,address,role);
        this.pinHash = person_pin;
        this.uuid = uuid;
        this.user_name = user_name;
        messages = new ArrayList<>();
        isNewMessages = false;
    }
    public String getUuid() {
        return uuid;
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
    public byte[] getPinHash() {
        return this.pinHash;
    }
    /**
     *Check whether a given password matches the UserRoot password or not
     * @param user_pin  the password of the UserRoot
     * @return              whether the password is valid or not
     */
    public boolean validatePinByUserPin(String user_pin){
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public void hasNewMessages(String new_messages){
        this.messages.add(new_messages);
        this.isNewMessages =  true;
    }
    @Override
    public String identify() {
        return this.uuid;
    }
}
