package kafkamanager;

import domain.Proposal;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Created by Antonio Nicolas on 30/03/2017.
 */
@Configuration
@EnableKafka
public class ProposalListenerFactory extends KafkaListenerFactory {

    @Override
    protected Class<Proposal> getElementClass() {
        return Proposal.class;
    }
}
