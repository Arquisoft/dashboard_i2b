package kafka_random_producer;

import dbmanagement.Database;
import domain.Participant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.ManagedBean;

/**
 * Created by Jorge on 01/04/2017.
 */
@ManagedBean
public class ParticipantsKafkaProducer {

    private static final Logger logger = Logger.getLogger(ProposalKafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Participant> kafkaTemplate;

    @Autowired
    private Database dat;

    public void send(String topic, Participant data) {
        ListenableFuture<SendResult<String, Participant>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Participant>>() {
            @Override
            public void onSuccess(SendResult<String, Participant> result) {
                dat.save(data);
                logger.info("Success on sending message \"" + data + "\" to topic " + topic);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Error on sending message \"" + data + "\", stacktrace " + ex.getMessage());
            }
        });
    }
}
