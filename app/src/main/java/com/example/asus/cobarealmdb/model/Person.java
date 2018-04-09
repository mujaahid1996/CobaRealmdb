package com.example.asus.cobarealmdb.model;

import io.realm.RealmObject;

/**
 * Created by asus on 29/12/17.
 */

public class Person
        extends RealmObject {

    private String name;
    private String email;

    // instansiasi
    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
