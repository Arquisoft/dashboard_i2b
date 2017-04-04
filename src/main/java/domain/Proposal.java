package domain;

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
public class Proposal {

    @Id
    private ObjectId _id;

    private String title;
    private String author;
    private String category;
    private int votes;
    private int minimalSupport;
    private String body;

    @DBRef
    @JsonManagedReference
    private List<Comment> comments;
    private List<String> votedUsernames;

    private Date created;

    public Proposal(){

    }

    public Proposal(String category, int minimalSupport){
        this.category = category;
        this.votes = 0;
        this.votedUsernames = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.minimalSupport = minimalSupport;
    }

    public ObjectId get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setVotedUsernames(List<String> votedUsernames) {
        this.votedUsernames = votedUsernames;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setMinimalSupport(int minimalSupport) {
        this.minimalSupport = minimalSupport;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCategory() {
        return category;
    }

    public int getVotes() {
        return votes;
    }

    public List<String> getVotedUsernames() {
        return votedUsernames;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public int getMinimalSupport() {
        return minimalSupport;
    }

    public String getAuthor() {
        return author;
    }

    public Date getCreated() {
        return created;
    }

    public boolean isSupported(){
        return getVotes() > getMinimalSupport();
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", votes=" + votes +
                ", minimalSupport=" + minimalSupport +
                ", body='" + body + '\'' +
                ", created=" + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Proposal proposal = (Proposal) o;

        if (category != null ? !category.equals(proposal.category) : proposal.category != null) return false;
        if (author != null ? !author.equals(proposal.author) : proposal.author != null) return false;
        return created != null ? created.equals(proposal.created) : proposal.created == null;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
