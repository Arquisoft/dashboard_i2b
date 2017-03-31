package dbmanagement;

import domain.Comment;
import domain.Participant;
import domain.Proposal;
import java.util.*;

/**
 * Created by Antonio Nicolas on 30/03/2017.
 */

public interface Database {

    public List<Participant> getParticipants();
    public List<Proposal> getProposals();
    public List<Comment> getComments();
}
