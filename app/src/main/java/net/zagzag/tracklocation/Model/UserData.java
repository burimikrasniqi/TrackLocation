package net.zagzag.tracklocation.Model;

import java.util.ArrayList;

/**
 * Created by burim on 12/27/2016.
 */

public class UserData {
    private String name;
    private String surname;
    private String email;

    public UserData(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


