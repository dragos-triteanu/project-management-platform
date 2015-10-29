package eu.accesa.crowdfund.model;

import java.util.Date;

/**
 * Created by dragos.triteanu on 10/28/15.
 */
public class ChatMessage {

    private int id;
    private String content;
    private Date date;
    private int to;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
