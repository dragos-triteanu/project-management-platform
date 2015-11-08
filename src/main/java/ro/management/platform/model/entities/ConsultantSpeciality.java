package ro.management.platform.model.entities;

import javax.persistence.*;

import static ro.management.platform.repository.Queries.DELETE_CONSULTANT_CATEGORY_BY_ID;

/**
 * Model class for representing a row in the 'consultant-category' DB schema.
 * Created by Dragos on 9/12/2015.
 */


@Entity
@Table(name="consultantSpecialities")
@NamedQueries({
        @NamedQuery(name = DELETE_CONSULTANT_CATEGORY_BY_ID, query = "DELETE FROM ConsultantSpeciality WHERE specialityId=:specialityId")
})
public class ConsultantSpeciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialityId")
    private int specialityId;

    @Column(name = "name",nullable = false)
    private String specialityName;

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }
}
