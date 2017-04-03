package domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Nicolás on 29/03/2017.
 */
public class Comment{

    @Id
    private ObjectId _id;

    private String text;
    @DBRef
    private Proposal proposal;
    private String author;
    private Date created;

    public Comment(){

    }

    public Comment(String text) {
        this.text = text;
    }

    public Comment(String text, Proposal prop, String author){
        setText(text);
        setProposal(prop);
        setAuthor(author);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
