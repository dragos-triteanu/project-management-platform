package ro.management.platform.model.dto;

/**
 * Created by Dragos on 11/17/2015.
 */
public class StoredFile {

    private String location;

    private String fileName;

    private int from;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }
}
