package kafka_random_producer;

import dbmanagement.Database;
import domain.Comment;
import domain.Participant;
import domain.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nicolás on 26/03/2017.
 */
@Service
public class KafkaTester {

    @Autowired
    private StringKafkaProducer stringProducer;

    @Autowired
    private Database dat;

    public void sendTestProposalCreate(Proposal proposal){
        proposal = dat.save(proposal);
        String data = String.format("{id:%s}", proposal.get_id());
        stringProducer.send("CREATE_POST", data);
    }

    public void sendTestCommentCreate(Comment comment){
        comment = dat.save(comment);
        String voteCount = comment.getVotes() != null ? String.valueOf(comment.getVotes().size()) : "0";
        String data = String.format("{postId:%s, number:%s}"
                , comment.getProposal().get_id()
                , voteCount);
        stringProducer.send("CREATE_COMMENT", data);
    }

    public void sendTestPostVote(Proposal proposal){
        proposal = dat.save(proposal);
        String data = String.format("{id:%s}", proposal.get_id());
        stringProducer.send("VOTE_POST", data);
    }

    public void sendTestCommentVote(Comment comment){
        comment = dat.save(comment);
        String data = String.format("{postId:%s, number:%s}"
                , comment.getProposal().get_id()
                , comment.getVotes().size());
        stringProducer.send("VOTE_COMMENT", data);
    }

    public void sendTestParticipant(Participant participant) {
        dat.save(participant);
    }
}
