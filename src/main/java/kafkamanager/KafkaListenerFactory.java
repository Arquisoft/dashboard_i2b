package kafkamanager;

import domain.Comment;
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
import org.springframework.kafka.support.serializer.JsonDeserializer;

/**
 * Created by herminio on 26/12/16.
 */
@Configuration
@EnableKafka
public class KafkaListenerFactory {

    @Bean(name = "participantContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Participant>> participantContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Participant> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerParticipantFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Participant> consumerParticipantFactory() {
        return new DefaultKafkaConsumerFactory<>(KafkaConfig.consumerJsonConfig(),
                null, new JsonDeserializer<>(Participant.class));
    }

    @Bean(name = "proposalContainerFactory")
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Proposal>> proposalContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Proposal> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerProposalFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Proposal> consumerProposalFactory() {
        return new DefaultKafkaConsumerFactory<>(KafkaConfig.consumerJsonConfig(),
                null, new JsonDeserializer<>(Proposal.class));
    }

    @Bean(name = "commentContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Comment>> commentContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Comment> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerCommentFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Comment> consumerCommentFactory() {
        return new DefaultKafkaConsumerFactory<>(KafkaConfig.consumerJsonConfig(),
                null, new JsonDeserializer<>(Comment.class));
    }
}
