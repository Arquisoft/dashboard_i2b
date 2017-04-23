package kafkamanager;

import dbmanagement.CommentsRepository;
import dbmanagement.ParticipantsRepository;
import dbmanagement.ProposalRepository;
import domain.Comment;
import domain.Participant;
import domain.Proposal;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
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
    public void listenProposal(Proposal data) {
        logger.info("New message received: \"" + data + "\"");
        proc.update(data);
    }

    @KafkaListener(topics = "participant", containerFactory = "participantContainerFactory")
    public void listen(Participant data){
        logger.info("New participant recieved!!!");
        proc.update(data);
    }

    @KafkaListener(topics = "topicComment", containerFactory = "commentContainerFactory")
    public void listen(Comment data){
        logger.info("New comment received!");
        proc.update(data);
    }

    @KafkaListener(topics = "voteProposal", containerFactory = "voteContainerFactory")
    public void listenProposalVote(String data){
        logger.info("New vote for a proposal received!");
        String[] arr = data.split(";");
        //Get proposal and update number of votes
        Proposal prop = proposalRepository.findOne(arr[0]);
        prop.setVotes(prop.getVotes() + 1);
        //Get participant and update participant voting the proposal
        Participant par = participantsRepo.findOne(new ObjectId(arr[1]));
        prop.getVotedUsernames().add(par.getUserId());
        //Save data
        proposalRepository.save(prop);
        //Update data
        proc.update(prop);
    }

    @KafkaListener(topics = "voteComment", containerFactory = "voteContainerFactory")
    public void listenCommentVote(String data){
        //El formato es: propID;commentNumber;userId
        logger.info("New vote for a comment received!");
        String[] arr = data.split(";");

        //Get proposal, comment and voter
        Proposal prop = proposalRepository.findOne(arr[0]);
        Comment com = prop.getComments().get(Integer.parseInt(arr[1]));
        Participant par = participantsRepo.findOne(new ObjectId(arr[2]));

        //Update the votes
        com.getVotes().add(par.getUserId());

        //Save data
        commentsRepo.save(com);
        //Update data
        proc.update(com);
    }

    @KafkaListener(topics = "unvoteProposal", containerFactory = "voteContainerFactory")
    public void listenProposalUnvote(String data){
        logger.info("Removing a proposal vote received!");
        String[] arr = data.split(";");

        //Get proposal and update number of votes
        Proposal prop = proposalRepository.findOne(arr[0]);
        prop.setVotes(prop.getVotes() - 1);

        //Get participant and update participant not voting the proposal
        Participant par = participantsRepo.findOne(new ObjectId(arr[1]));
        prop.getVotedUsernames().remove(par.getUserId());

        //Update data
        proc.update(prop);
    }

    @KafkaListener(topics = "unvoteComment", containerFactory = "voteContainerFactory")
    public void listenCommentUnvote(String data){
        //El formato es: propID;commentNumber;userId
        logger.info("Removing a comment vote received!");
        String[] arr = data.split(";");

        //Get proposal, comment and voter
        Proposal prop = proposalRepository.findOne(arr[0]);
        Comment com = prop.getComments().get(Integer.parseInt(arr[1]));
        Participant par = participantsRepo.findOne(new ObjectId(arr[2]));

        //Remove the vote from the comment
        com.getVotes().remove(par.getUserId());

        //Update data
        proc.update(com);
    }
}
