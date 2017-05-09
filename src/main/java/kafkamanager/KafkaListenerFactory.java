package kafkamanager;

import domain.Participant;
import domain.Proposal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

/**
 * Created by herminio on 26/12/16.
 */
@Configuration
@EnableKafka
public class KafkaListenerFactory {

    @Bean(name = "containerFactory")
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Proposal>> proposalContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Proposal> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Proposal> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(KafkaListenerConfig.consumerStringConfig());
    }

}
