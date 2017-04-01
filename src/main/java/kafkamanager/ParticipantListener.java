package kafkamanager;

import dbmanagement.ParticipantsRepository;
import domain.Participant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import statisticsCalculator.ParticipantsProcessor;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class ParticipantListener {

    @Autowired
    private ParticipantsRepository partiDat;

    @Autowired
    private ParticipantsProcessor partiProc;

    private static final Logger logger = Logger.getLogger(ParticipantListener.class);

    @KafkaListener(topics = "participant")
    public void listen(Participant data){
        logger.info("New participant recieved!!!");
        partiDat.insert(data);
        partiProc.Update(data);
    }



}
