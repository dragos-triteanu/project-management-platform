package eu.accesa.crowdfund.model;

import java.util.UUID;

/**
 * Created by Dragos on 9/19/2015.
 */
public class Client {
    private UUID uid;
    private String firstName;
    private String lastName;
    private String email;

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
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
