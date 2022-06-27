package com.example.model;

import java.io.Serializable;

public class Users implements Serializable {
    private String id;
    private String username;
    private String imageURL;
    private String phone;


    public Users(String id, String username, String imageURL, String  phone) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        this.phone = phone;
    }
    public Users(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public  String getPhone(String phone) {
        return phone;
    }
    public void  setPhone(String phone){this.phone = phone;}
}

