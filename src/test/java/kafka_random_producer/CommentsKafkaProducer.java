package kafka_random_producer;

import domain.Comment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.ManagedBean;

/**
 * Created by Damian on 03/04/2017.
 */
@ManagedBean
public class CommentsKafkaProducer {

    private static final Logger logger = Logger.getLogger(ProposalKafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Comment> kafkaTemplate;

    public void send(String topic, Comment data) {
        ListenableFuture<SendResult<String, Comment>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Comment>>() {
            @Override
            public void onSuccess(SendResult<String, Comment> result) {
                logger.info("Success on sending message \"" + data + "\" to topic " + topic);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Error on sending message \"" + data + "\", stacktrace " + ex.getMessage());
            }
        });
    }
}
