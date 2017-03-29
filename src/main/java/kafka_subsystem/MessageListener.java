package kafka_subsystem;

import domain.UserLoginData;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class MessageListener {

    private static final Logger logger = Logger.getLogger(MessageListener.class);

    @KafkaListener(topics = "exampleTopic")
    public void listen(UserLoginData data) {
        logger.info("New message received: \"" + data + "\"");
        TestContainer.add(data);
    }



}
