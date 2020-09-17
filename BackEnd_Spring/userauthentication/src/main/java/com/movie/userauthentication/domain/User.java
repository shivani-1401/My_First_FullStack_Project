package com.movie.userauthentication.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Document
public class User {

    @Id
    private String id;
    private String password;


    public User() {
    }

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
