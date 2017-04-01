package kafka_random_producer;

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
    private KafkaProducer producer;
    @Autowired
    private KafkaProducerParti partiProducer;

    public void sendTestProposal(Proposal data){
        producer.send("proposal", data);
    }


    public void sendTestParticipant(Participant participant) { partiProducer.send("participant", participant);
    }
}
