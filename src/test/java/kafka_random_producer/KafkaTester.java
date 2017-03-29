package kafka_random_producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nicolás on 26/03/2017.
 */
@Service
public class KafkaTester {

    @Autowired
    private TestListener listener;
    @Autowired
    private KafkaProducer producer;

    public void sendTest(String message, int times){
        for(int i = 0; i<times; i++){
            producer.send("exampleTopic", message);
        }
    }

    public List<String> getMessageList(){
        return listener.getDataList();
    }

}
