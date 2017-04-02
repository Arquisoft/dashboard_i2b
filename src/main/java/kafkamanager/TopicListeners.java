package kafkamanager;

import dbmanagement.ParticipantsRepository;
import dbmanagement.ProposalRepository;
import domain.Participant;
import domain.Proposal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import statisticsCalculator.ParticipantsProcessor;
import statisticsCalculator.ProposalsProcessor;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class TopicListeners {

    @Autowired
    private ProposalRepository dat;

    @Autowired
    private ProposalsProcessor proc;

    @Autowired
    private ParticipantsRepository partiDat;

    @Autowired
    private ParticipantsProcessor partiProc;

    private static final Logger logger = Logger.getLogger(TopicListeners.class);

    @KafkaListener(topics = "proposal", containerFactory = "proposalContainerFactory")
    public void listen(Proposal data) {
        logger.info("New message received: \"" + data + "\"");
        dat.insert(data);
        proc.Update(data);
    }

    @KafkaListener(topics = "participant", containerFactory = "participantContainerFactory")
    public void listen(Participant data){
        logger.info("New participant recieved!!!");
        partiDat.insert(data);
        partiProc.Update(data);
    }
}
