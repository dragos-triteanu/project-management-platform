package eu.accesa.crowdfund.model;

import java.util.UUID;

/**
 * Created by Dragos on 9/19/2015.
 */
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public int getUid() {
        return id;
    }

    public void setUid(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
