package kafka_random_producer;

import dbmanagement.Database;
import domain.Proposal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.ManagedBean;

/**
 * Created by Nicolás on 26/03/2017.
 */
@ManagedBean
public class ProposalKafkaProducer {

    private static final Logger logger = Logger.getLogger(ProposalKafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Proposal> kafkaTemplate;

    @Autowired
    private Database dat;

    public void send(String topic, Proposal data) {
        ListenableFuture<SendResult<String, Proposal>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Proposal>>() {
            @Override
            public void onSuccess(SendResult<String, Proposal> result) {
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
