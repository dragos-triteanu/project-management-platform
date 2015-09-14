package eu.accesa.crowdfund.model;

import java.util.UUID;

/**
 * Created by Dragos on 9/12/2015.
 */
public class Category {
    private UUID uid;
    private String name;

    public UUID getUid() { return uid; }
    public void setUid(UUID uid){ this.uid = uid; }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
}
