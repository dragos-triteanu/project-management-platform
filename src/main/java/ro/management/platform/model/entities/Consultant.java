package ro.management.platform.model.entities;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

import static ro.management.platform.repository.Queries.*;

/**
 * Created by Dragos on 9/12/2015.
 */
@NamedQueries({
    @NamedQuery(name = UPDATE_CONSULTANT , query = "UPDATE Consultant c SET c.lastName=:lastName,c.firstName=:firstName,c.mail=:mail, c.phoneNumber= :phoneNumber, c.address = :address,  c.studies= :studies, c.ibanCode=:ibanCode,c.cardOwner=:cardOwner , c.speciality.specialityId= :specialityId WHERE c.userId= :userId"),
    @NamedQuery(name = UPDATE_CONSULTANT_WITH_CV , query = "UPDATE Consultant c SET c.lastName=:lastName,c.firstName=:firstName,c.mail=:mail, c.phoneNumber= :phoneNumber, c.address = :address,  c.studies= :studies, c.ibanCode=:ibanCode,c.cardOwner=:cardOwner , c.speciality.specialityId=:specialityId , c.cv=:cv WHERE c.userId= :userId"),
    @NamedQuery(name = DELETE_CONSULTANT_BY_ID , query = "DELETE FROM Consultant WHERE userId=:userId"),
    @NamedQuery(name = RETRIEVE_CONSULTANTS_BY_NAME , query = "SELECT user FROM Consultant user WHERE CONCAT(user.firstName, ' ', user.lastName) like :searchText OR CONCAT(user.lastName, ' ', user.firstName) like :searchText"),
    @NamedQuery(name = RETRIEVE_CONSULTANTS_BY_ADDRESS , query = "SELECT user FROM Consultant user WHERE user.address like :searchText" )
})
@Entity
@Table(name="consultants")
@PrimaryKeyJoinColumn(name="consultantId" , referencedColumnName = "userId")
public class Consultant extends User {

    @Column(name = "lastName", nullable = false)
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

    @Column(name = "cv", nullable = true , columnDefinition = "LONGBLOB")
    @Lob
    private byte[] cv;

    @OneToOne
    @JoinColumn(name="specialityId", nullable = false)
    private ConsultantSpeciality speciality;

    @Formula(value = "concat(firstName,' ', lastName)")
    private String fullName;

    @Formula(value = "(SELECT COUNT(*) FROM consultantOrders co WHERE co.status = 3 AND co.consultantId = consultantId)" )
    private int numberOfActiveProjects;

    @Transient
    private String cvURL;

    @Column(name = "rating" , nullable = true , columnDefinition = "FLOAT default 0")
    private float rating;

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

    public String getFullName() {  return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}