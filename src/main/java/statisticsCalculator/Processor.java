package statisticsCalculator;

import dbmanagement.Agrupations.ParticipantLocalization;
import dbmanagement.Agrupations.ProposalCommented;
import domain.Comment;
import domain.Participant;
import domain.Proposal;

import java.util.List;

/**
 * Created by Jorge on 28/03/2017.
 *
 * Interface that defines the processors that manage the data
 */
public interface Processor {

    void updateComCreate(String data);
    void updateComVote(String data);
    void updatePropCreate(String data);
    void updatePropVote(String data);

    List<ProposalCommented> getTopCommented();
    List<Proposal> getTopVotes();

    Long getProposalsAmount();
    Long getCommentsAmount();
}
