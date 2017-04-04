package domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

/**
 * Created by Nicolás on 29/03/2017.
 */
public class Comment{

    @Id
    private ObjectId _id;

    private String author;
    private String body;
    private Date created;
    @DBRef
    private Proposal proposal;

    private List<String> votedUsernames;

    public Comment(){

    }

    public Comment(String author, String body, Date created){
        setBody(body);
        setAuthor(author);
        setCreated(created);
    }

    public Comment(String body, Proposal prop, String author){
        this(author, body, new Date());
        setProposal(prop);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public List<String> getVotedUsernames() {
        return votedUsernames;
    }

    public void setVotedUsernames(List<String> votedUsernames) {
        this.votedUsernames = votedUsernames;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "author='" + author + '\'' +
                ", body='" + body + '\'' +
                ", created=" + created +
                ", proposal=" + proposal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (author != null ? !author.equals(comment.author) : comment.author != null) return false;
        if (created != null ? !created.equals(comment.created) : comment.created != null) return false;
        return proposal != null ? proposal.equals(comment.proposal) : comment.proposal == null;
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (proposal != null ? proposal.hashCode() : 0);
        return result;
    }
}
