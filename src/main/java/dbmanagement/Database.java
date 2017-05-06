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

    Proposal insert(Proposal proposal);
    Participant save(Participant participant);
    Proposal save(Proposal proposal);
    Comment save(Comment comment);

    void delete(Proposal proposal);
    void reset();

    Long countParticipants();
    Long countProposals();
    Long countComments();

    //Crud
    Proposal findProposal(String author, String category, Date created);

    List<ParticipantLocalization> getParticipantsGroupByNationality();

    List<Proposal> findTop5ProposalsByVotes();

    List<ProposalCommented> findTop5MostCommentedProposal();
}
