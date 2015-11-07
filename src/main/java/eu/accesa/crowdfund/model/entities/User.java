package eu.accesa.crowdfund.model.entities;

import eu.accesa.crowdfund.security.Authority;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Dragos on 11/5/2015.
 */
@Entity
@Table(name="users")
@Inheritance(strategy=InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue
    @Column(name = "userId")
    private int userId;
    @Column(name="mail", nullable = false)
    private String mail;
    @Column(name="password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Authority role;
    @Column(name = "lastLogin", nullable = true)
    private Timestamp lastLogin;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Authority getRole() {
        return role;
    }

    public void setRole(Authority role) {
        this.role = role;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
}
