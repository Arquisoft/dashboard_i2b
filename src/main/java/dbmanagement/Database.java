package dbmanagement;

import dbmanagement.Agrupations.ParticipantLocalization;
import dbmanagement.Agrupations.ProposalCommented;
import domain.Comment;
import domain.Participant;
import domain.Proposal;

import java.util.List;

/**
 * Created by Antonio Nicolas on 30/03/2017.
 */

public interface Database {

    List<Participant> getParticipants();
    List<Proposal> getProposals();
    List<Comment> getComments();

    Long countParticipants();
    Long countProposals();
    Long countComments();

    List<ParticipantLocalization> getParticipantsGroupByNationality();


    List<Proposal> findTop5ProposalsByVotes();

    List<ProposalCommented> findTop5MostCommentedProposal();
}
