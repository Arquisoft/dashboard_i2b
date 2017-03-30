package kafkamanager;

import java.util.Map;

/**
 * Created by Antonio Nicolas on 30/03/2017.
 */
public class KafkaJsonConfiguration {

    private Map<String, Object> producerConfiguration;
    private static KafkaJsonConfiguration config;

    public static KafkaJsonConfiguration getInstance(){
        if(config == null){
            config = new KafkaJsonConfiguration();
        }
        return config;
    }

    private KafkaJsonConfiguration(){
        producerConfiguration = loadProducerConfig();
    }

    public Map<String, Object> getProducerConfiguration(){
        return producerConfiguration;
    }

    private Map<String, Object> loadProducerConfig() {
        return null;
    }

}
