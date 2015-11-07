package eu.accesa.crowdfund.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Dragos on 9/12/2015.
 */
@Entity
@Table(name="consultants")
@PrimaryKeyJoinColumn(name="consultantId" , referencedColumnName = "userId")

public class Consultant extends User {

    private String lastName;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "studies", nullable = false)
    private String studies;
    @Column(name = "ibanCode", nullable = false)
    private String ibanCode;
    @Column(name = "cardOwner", nullable = false)
    private String cardOwner;
    @Column(name = "cv", nullable = true)
    private byte[] cv;
    @OneToOne
    @JoinColumn(name="specialityId", nullable = false)
    private ConsultantSpeciality speciality;
    @Column(name = "numberOfActiveProjects", nullable = false)
    private int numberOfActiveProjects;
    @Column(name = "cvURL", nullable = true)
    private String cvURL;

    public Consultant() {
        speciality = new ConsultantSpeciality();
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIbanCode() {
        return ibanCode;
    }

    public void setIbanCode(String ibanCode) {
        this.ibanCode = ibanCode;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    public ConsultantSpeciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(ConsultantSpeciality speciality) {
        this.speciality = speciality;
    }

    public void setSpecialityId(final int specialityId) {
        this.speciality.setSpecialityId(specialityId);
    }

    public int getNumberOfActiveProjects() {
        return numberOfActiveProjects;
    }

    public void setNumberOfActiveProjects(int numberOfActiveProjects) {this.numberOfActiveProjects = numberOfActiveProjects; }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public String getCvURL() {
        return cvURL;
    }

    public void setCvURL(String cvURL) {
        this.cvURL = cvURL;
    }

}