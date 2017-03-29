package kafka_random_producer;

import main.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nicolás on 28/03/2017.
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class KafkaTest {

    @Autowired
    KafkaTester tester;

    @Test
    public void test() throws InterruptedException {
        List<String> mensajes = new ArrayList<>();
        for(int i = 0; i<10; i++){
            mensajes.add("HOLA");
        }
        tester.sendTest("HOLA", 10);
        Thread.sleep(1000);
        assertEquals(mensajes, tester.getMessageList());
    }
}
