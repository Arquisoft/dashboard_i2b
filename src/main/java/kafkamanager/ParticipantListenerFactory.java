package kafkamanager;

import domain.Participant;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Created by Jorge on 01/04/2017.
 */
@Configuration
@EnableKafka
public class ParticipantListenerFactory extends KafkaListenerFactory {
    @Override
    protected Class getElementClass() {
        return Participant.class;
    }
}
