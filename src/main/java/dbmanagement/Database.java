package dbmanagement;

import dbmanagement.Agrupations.ParticipantLocalization;
import dbmanagement.Agrupations.ProposalCommented;
import domain.Comment;
import domain.Participant;
import domain.Proposal;

import java.util.Date;
import java.util.List;

/**
 * Created by Antonio Nicolas on 30/03/2017.
 */

public interface Database {

    //Crud
    Proposal findPropByAuthorAndCategoryAndCreated(String author, String category, Date created);

    List<Participant> getParticipants();
    List<Proposal> getProposals();
    List<Comment> getComments();

    Proposal insert(Proposal proposal);

    Participant save(Participant participant);
    Proposal save(Proposal proposal);
    Comment save(Comment comment);

    void reset();
    void delete(Proposal proposal);


    Long countParticipants();
    Long countProposals();
    Long countComments();

    List<ParticipantLocalization> getParticipantsGroupByNationality();

    List<Proposal> findTop5ProposalsByVotes();

    List<ProposalCommented> findTop5MostCommentedProposal();
}
