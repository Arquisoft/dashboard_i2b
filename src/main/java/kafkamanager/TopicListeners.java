package kafkamanager;

import dbmanagement.CommentRepository;
import dbmanagement.ParticipantsRepository;
import dbmanagement.ProposalRepository;
import domain.Comment;
import domain.Participant;
import domain.Proposal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import statisticsCalculator.Processor;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class TopicListeners {

    @Autowired
    private ProposalRepository dat;

    @Autowired
    private ParticipantsRepository partiDat;

    @Autowired
    private CommentRepository commentDat;

    @Autowired
    private Processor proc;

    private static final Logger logger = Logger.getLogger(TopicListeners.class);

    @KafkaListener(topics = "proposal", containerFactory = "proposalContainerFactory")
    public void listen(Proposal data) {
        logger.info("New message received: \"" + data + "\"");
        dat.insert(data);
        proc.update(data);
    }

    @KafkaListener(topics = "participant", containerFactory = "participantContainerFactory")
    public void listen(Participant data){
        logger.info("New participant recieved!!!");
        partiDat.insert(data);
        proc.update(data);
    }

    @KafkaListener(topics = "comment", containerFactory = "commentContainerFactory")
    public void listen(Comment data){
        logger.info("New comment received");
        commentDat.insert(data);
        proc.update(data);
    }
}
