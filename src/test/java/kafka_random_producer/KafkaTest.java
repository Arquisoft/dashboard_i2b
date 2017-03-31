package kafka_random_producer;

import dbmanagement.ProposalRepository;
import domain.Proposal;
import main.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nicolás on 28/03/2017.
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class KafkaTest {

    @Autowired
    KafkaTester tester;

    @Autowired
    private ProposalRepository repo;

    @Test
    public void test() throws InterruptedException {
        Proposal proposal = new Proposal("Test Proposals", 50);
        proposal.setAuthor("Autor prueba");
        tester.sendTestProposal(proposal);
        Thread.sleep(1000);
        Proposal test = repo.findByAuthor(proposal.getAuthor()).get(0);
        assertEquals(test, proposal);
        repo.delete(test);
    }

    @Test
    public void loopingTest() throws InterruptedException{
        int number =0;
        while(number<10){
            Proposal proposal = new Proposal("Test Proposals", 50);
            proposal.setAuthor("Autor prueba");
            tester.sendTestProposal(proposal);
            Thread.sleep(10000);
            number++;
        }
    }
}
