package statisticsCalculator;

import domain.Comment;
import domain.Participant;
import domain.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        propProc.updateCommentRecieved(); //we have to update the ProposalsProcessor in order to have
        commProc.update(data);
    }

}
