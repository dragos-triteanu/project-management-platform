package eu.accesa.crowdfund.model;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * Model class for representing a row in the 'consultant-category' DB schema.
 * Created by Dragos on 9/12/2015.
 */
public class ConsultantSpeciality {

    private String specialityId;
    private String specialityName;

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(String specialityId) {
        this.specialityId = specialityId;
    }
}
