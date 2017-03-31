package kafkamanager;

import dbmanagement.ProposalRepository;
import domain.Proposal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import statisticsCalculator.CommentsProcessor;
import statisticsCalculator.Processor;
import statisticsCalculator.ProposalsProcessor;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class MessageListener {

    @Autowired
    private ProposalRepository dat;

    @Autowired
    private ProposalsProcessor proc;

    private static final Logger logger = Logger.getLogger(MessageListener.class);

    @KafkaListener(topics = "proposal")
    public void listen(Proposal data) {
        logger.info("New message received: \"" + data + "\"");
        dat.insert(data);
        proc.Update();
    }



}
