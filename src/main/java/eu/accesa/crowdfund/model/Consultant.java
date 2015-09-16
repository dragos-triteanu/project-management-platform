package eu.accesa.crowdfund.model;

import javax.swing.text.Document;
import java.util.UUID;

/**
 * Created by Dragos on 9/12/2015.
 */
public class Consultant {
    private UUID uid;
    private String firstName;
    private String lastName;
    private String mail;
    private String phoneNumber;
    private String address;
    private String school;
    private String ibanCode;
    private String cardOwner;
    private Document cv;
    private ConsultantCategory category;
    private int numerOfActiveProjects;

    public UUID getUid() {
        return uid;
    }
    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getFirstName(){return firstName;}
    public void setFirstName(String firstName){ this.firstName = firstName; }

    public String getLastName() { return lastName;  }
    public void setLastName(String lastName) { this.lastName = lastName;  }

    public String getMail() {return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPhoneNumber() { return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address; }

    public String getSchool() {return school;}
    public void setSchool(String school) { this.school = school; }

    public String getIbanCode() { return ibanCode;}
    public void setIbanCode(String ibanCode) { this.ibanCode = ibanCode; }

    public String getCardOwner() { return cardOwner;}
    public void setCardOwner(String cardOwner) { this.cardOwner = cardOwner;}

    public Document getCv() { return cv; }
    public void setCv(Document cv) { this.cv = cv; }

    public ConsultantCategory getCategory() { return category; }
    public void setCategory(ConsultantCategory category) { this.category = category; }

    public int getNumerOfActiveProjects() { return numerOfActiveProjects; }
    public void setNumerOfActiveProjects(int numerOfActiveProjects) { this.numerOfActiveProjects = numerOfActiveProjects; }
}