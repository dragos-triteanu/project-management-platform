package ro.management.platform.model.entities;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

import static ro.management.platform.repository.Queries.*;
import static ro.management.platform.repository.Queries.RETRIEVE_CONSULTANTS_BY_ADDRESS;

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

    @Formula(value = " concat(firstName, ' ', lastName)")
    private String fullName;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
