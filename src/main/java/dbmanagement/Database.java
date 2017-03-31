package dbmanagement;

import domain.Comment;
import domain.Participant;

import domain.Proposal;
import java.util.*;

/**
 * Created by Antonio Nicolas on 30/03/2017.
 */

public interface Database {
    List<Participant> getParticipants();
    List<Proposal> getProposals();
    List<Comment> getComments();
}
