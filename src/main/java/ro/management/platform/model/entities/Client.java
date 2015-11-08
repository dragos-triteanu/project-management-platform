package ro.management.platform.model.entities;

import javax.persistence.*;

/**
 * Created by Dragos on 9/19/2015.
 */
@Entity
@Table(name="clients")
@PrimaryKeyJoinColumn(name="clientId" , referencedColumnName = "userId")
public class Client extends User {

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name= "lastName" , nullable = false)
    private String lastName;

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
}
