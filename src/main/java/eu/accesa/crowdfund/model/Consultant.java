package eu.accesa.crowdfund.model;

import javax.swing.text.Document;

/**
 * Created by Dragos on 9/12/2015.
 */
public class Consultant {
    private String id;
    private String lastName;
    private String firstName;
    private String mail;
    private String phoneNumber;
    private String address;
    private String studies;
    private String ibanCode;
    private String cardOwner;
    private byte[] cv;
    private ConsultantSpeciality speciality;
    private int numberOfActiveProjects;

    public Consultant(){
        speciality = new ConsultantSpeciality();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public void setSpecialityId(final String specialityId){
        this.speciality.setSpecialityId(specialityId);
    }

    public int getNumberOfActiveProjects() {
        return numberOfActiveProjects;
    }

    public void setNumberOfActiveProjects(int numberOfActiveProjects) {
        this.numberOfActiveProjects = numberOfActiveProjects;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }
}