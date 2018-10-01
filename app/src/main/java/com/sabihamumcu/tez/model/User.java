package com.sabihamumcu.tez.model;

/**
 * Created by sabis on 3/11/2018.
 */

public class User {

    public User() {

    }

    private String key, mail, password;

    public User(String key, String mail, String password) {
        this.key = key;
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
