package kafka_random_producer;

import domain.UserLoginData;
import kafka_subsystem.TestContainer;
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
        List<UserLoginData> mensajes = new ArrayList<>();
        mensajes.add(new UserLoginData("login", "password"));
        tester.sendTest(mensajes.get(0), 10);
        Thread.sleep(1000);
        assertEquals(mensajes, TestContainer.getList());
    }
}
