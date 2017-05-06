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

    void update(Participant data);
    void update(Proposal data);
    void update(Comment data);

    List<ParticipantLocalization> getNationAgrup();
    List<ProposalCommented> getTopCommented();
    List<Proposal> getTopVotes();

    Long getParticipantsAmount();
    Long getProposalsAmount();
    Long getCommentsAmount();

}
