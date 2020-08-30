package com.example.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class JsonUser extends User{

    @SerializedName("result")
    User user;

    public JsonUser(int id, String username, String emailAddress, String firstName, String lastName, int age, String address, int phone) {
        super(id, username, emailAddress, firstName, lastName, age, address, phone);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
