package eu.accesa.crowdfund.model;

/**
 * Model class for representing a row in the 'consultant-category' DB schema.
 * Created by Dragos on 9/12/2015.
 */
public class ConsultantCategory {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
