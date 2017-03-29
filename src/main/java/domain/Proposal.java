package domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nicolás on 29/03/2017.
 */
public class Proposal {

    @Id
    private ObjectId _id;

    private String category;
    private int votes;
    private List<String> votedUsernames;
    private List<Comment> comments;
    private int minimalSupport;
    private String author;
    private Date created;

    public Proposal(String category, int minimalSupport){
        this.category = category;
        this.votes = 0;
        this.votedUsernames = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.minimalSupport = minimalSupport;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setMinimalSupport(int minimalSupport) {
        this.minimalSupport = minimalSupport;
    }

    public String getCategory() {
        return category;
    }

    public int getVotes() {
        return votes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public int getMinimalSupport() {
        return minimalSupport;
    }

    public boolean isSupported(){
        return getVotes() > getMinimalSupport();
    }

    public List<String> getVotedUsernames() {
        return votedUsernames;
    }

    public ObjectId getId() {
        return _id;
    }
}
