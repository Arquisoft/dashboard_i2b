package statisticsCalculator;

import dbmanagement.Agrupations.ParticipantLocalization;
import dbmanagement.Agrupations.ProposalCommented;
import domain.Comment;
import domain.Participant;
import domain.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jorge on 02/04/2017.
 */
@Service
public class ProcessorImpl implements Processor {

    @Autowired
    private ParticipantsProcessor partProc;
    @Autowired
    private ProposalsProcessor propProc;
    @Autowired
    private CommentsProcessor commProc;

    public ProcessorImpl(ParticipantsProcessor partProc, ProposalsProcessor propProc, CommentsProcessor commProc) {
        this.partProc=partProc;
        this.propProc=propProc;
        this.commProc=commProc;
    }

    @Override
    public void update(Participant data){
        partProc.update(data);
    }

    @Override
    public void update(Proposal data){
        propProc.update(data);
    }

    @Override
    public void update(Comment data){
        propProc.updateCommentReceived(); //we have to update the ProposalsProcessor in order to have
        commProc.update(data);
    }

    @Override
    public List<ParticipantLocalization> getNationAgrup() {
        return partProc.getNationAgrup();
    }

    @Override
    public List<ProposalCommented> getTopCommented() {
        return propProc.getTopCommented();
    }

    @Override
    public List<Proposal> getTopVotes() {
        return propProc.getTopVotes();
    }

    @Override
    public Long getParticipantsAmount() {
        return partProc.getAmount();
    }

    @Override
    public Long getProposalsAmount() {
        return propProc.getAmount();
    }

    @Override
    public Long getCommentsAmount() {
        return commProc.getAmount();
    }
}
