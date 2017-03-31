package kafkamanager;

import dbmanagement.ProposalRepository;
import domain.Proposal;
import main.MainController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import statisticsCalculator.CommentsProcessor;
import statisticsCalculator.ParticipantsProcessor;
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
    private ParticipantsProcessor parProc;
    @Autowired
    private CommentsProcessor comProc;
    @Autowired
    private ProposalsProcessor propProc;

    private static final Logger logger = Logger.getLogger(MessageListener.class);



    /*@KafkaListener(topics = "participants")
    public void listen(String data) {
        //logger.info("New message received: \"" + data + "\"");
        System.out.println("Nuevo participante!!!! : " + data);
        MainController.update();
    }*/

    @KafkaListener(topics = "proposal")
    public void listen(Proposal data) {
        propProc.Update();
        System.out.print("Nuevo participante!!");
        dat.insert(data);
    }

    /*@KafkaListener(topics = "exampleTopic")
    public void listen(Proposal data) {
        logger.info("New message received: \"" + data + "\"");
        dat.insert(data);
    }*/



}
