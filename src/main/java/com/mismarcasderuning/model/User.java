/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.model;

import java.util.Calendar;

/**
 *
 * @author Jesus Cruz
 */
public class User {
    
    private final int id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surnames;
    private long mobile;
    private Calendar birthDate;
    
    private final int token;
    private boolean verified;
    private boolean loggedIn;
    
    private static int nUsers = 1;

    public User(int id, String username, String password,String email, String name, String surnames, long mobile, Calendar birthDate, int token, boolean verified, boolean loggedIn) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surnames = surnames;
        this.mobile = mobile;
        this.birthDate = birthDate;
        this.token = token;
        this.verified = verified;
        this.loggedIn = loggedIn;
    }
    
    public User () {
        id = -1;
        loggedIn = false;
        token = -1;
    }
    
    public User (String username, String password, String email, String name, String surnames, long mobile, Calendar birthDate){
        id = nUsers++;        
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surnames = surnames;
        this.mobile = mobile;
        this.birthDate = birthDate;
        
        token = (int) (Math.random() * 899999 + 100000);
        verified = false;
        loggedIn = true;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getEmail() {
        return email;
    }

    public long getMobile() {
        return mobile;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public int getToken() {
        return token;
    }

    public boolean isVerified() {
        return verified;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }  

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public static void setNUsers(int i) {
        nUsers = i;
    }
}
