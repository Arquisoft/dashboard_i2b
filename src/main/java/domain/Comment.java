package domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Nicolás on 29/03/2017.
 */
public class Comment {

    @Id
    private ObjectId _id;

    private String text;
    private ObjectId proposalId;
    private String author;
    private Date created;

    public Comment(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getText() {
        return text;
    }

}
