package kafkamanager;

import domain.Participant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/**
 * Created by herminio on 26/12/16.
 */
@Configuration
@EnableKafka
public abstract class ParticipantListenerFactory {

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Participant>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Participant> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Participant> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(KafkaConfig.consumerConfig(),
                null, new JsonDeserializer<>(Participant.class));
    }

}
