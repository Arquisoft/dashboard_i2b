package kafkamanager;

import dbmanagement.CommentsRepository;
import dbmanagement.ParticipantsRepository;
import dbmanagement.ProposalRepository;
import domain.Comment;
import domain.Participant;
import domain.Proposal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import statisticsCalculator.Processor;

/**
 * Created by herminio on 28/12/16.
 */
@Service
public class TopicListeners {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ParticipantsRepository participantsRepo;

    @Autowired
    private CommentsRepository commentsRepo;

    @Autowired
    private Processor proc;

    private static final Logger logger = Logger.getLogger(TopicListeners.class);

    @KafkaListener(topics = "proposal", containerFactory = "proposalContainerFactory")
    public void listen(Proposal data) {
        logger.info("New message received: \"" + data + "\"");
        proposalRepository.insert(data);
        proc.update(data);
    }

    @KafkaListener(topics = "participant", containerFactory = "participantContainerFactory")
    public void listen(Participant data){
        logger.info("New participant recieved!!!");
        participantsRepo.insert(data);
        proc.update(data);
    }

    @KafkaListener(topics = "comment", containerFactory = "commentContainerFactory")
    public void listen(Comment data){
        logger.info("New comment received!");
        data = commentsRepo.insert(data); //Update the comment with the Mongo ID
        Proposal prop = proposalRepository.findOne(data.getProposal().get_id());
        //Get the proposal and add the comment
        prop.getComments().add(data);
        proposalRepository.save(prop);
        //Update the proposal
        proc.update(data);
    }
}
