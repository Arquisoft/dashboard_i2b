package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nicolás on 29/03/2017.
 */
public class Comment{

    @Id
    private ObjectId _id;

    private String text;
    @DBRef
    @JsonBackReference
    private Proposal proposal;
    private String author;
    private Date created;
    private List<String> votes;
    private long num;

    public Comment(){

    }

    public Comment(String text) {
        this.text = text;
    }

    public Comment(String text, Proposal prop, String author){
        setText(text);
        setProposal(prop);
        setAuthor(author);
        this.votes = new ArrayList<>();
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

    public List<String> getVotes() {
        return votes;
    }

    public void setVotes(List<String> votes) {
        this.votes = votes;
    }

    public long getNum() {
        return this.num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (_id != null ? !_id.equals(comment._id) : comment._id != null) return false;
        if (!text.equals(comment.text)) return false;
        if (proposal != null ? !proposal.equals(comment.proposal) : comment.proposal != null) return false;
        if (author != null ? !author.equals(comment.author) : comment.author != null) return false;
        return created != null ? created.equals(comment.created) : comment.created == null;

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + text.hashCode();
        result = 31 * result + (proposal != null ? proposal.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "_id=" + _id +
                ", text='" + text + '\'' +
                ", proposal=" + proposal +
                ", author='" + author + '\'' +
                ", created=" + created +
                '}';
    }
}
