package kafkamanager;

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
    private Processor proc;

    private static final Logger logger = Logger.getLogger(TopicListeners.class);

    @KafkaListener(topics = "CREATE_POST", containerFactory = "containerFactory")
    public void listenCreatePost(String data){
        logger.info("New message received CREATE_POST: \"" + data + "\"");
        proc.updatePropCreate(data);
    }

    @KafkaListener(topics = "VOTE_POST", containerFactory = "containerFactory")
    public void listenVotePost(String data){
        logger.info("New message received VOTE_POST: \"" + data + "\"");
        proc.updatePropVote(data);

    }
    @KafkaListener(topics = "CREATE_COMMENT", containerFactory = "containerFactory")
    public void listenCreateComment(String data){
        logger.info("New message received CREATE_COMMENT: \"" + data + "\"");
        proc.updateComCreate(data);
    }

    @KafkaListener(topics = "VOTE_COMMENT", containerFactory = "containerFactory")
    public void listenVoteComment(String data){
        logger.info("New message received VOTE_COMMENT: \"" + data + "\"");
        proc.updateComVote(data);
    }

    @KafkaListener(topics = "UNVOTE_POST", containerFactory = "containerFactory")
    public void listenProposalUnvote(String data){
        logger.info("New message received VOTE_COMMENT: \"" + data + "\"");
        proc.updatePropVote(data);
    }
}
