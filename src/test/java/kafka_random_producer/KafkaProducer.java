package kafka_random_producer;

import domain.UserLoginData;
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
public class KafkaProducer {

    private static final Logger logger = Logger.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, UserLoginData> kafkaTemplate;

    public void send(String topic, UserLoginData data) {
        ListenableFuture<SendResult<String, UserLoginData>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, UserLoginData>>() {
            @Override
            public void onSuccess(SendResult<String, UserLoginData> result) {
                logger.info("Success on sending message \"" + data + "\" to topic " + topic);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Error on sending message \"" + data + "\", stacktrace " + ex.getMessage());
            }
        });
    }

}
