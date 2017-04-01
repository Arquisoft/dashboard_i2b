package kafka_random_producer;

import dbmanagement.ProposalRepository;
import domain.Participant;
import domain.Proposal;
import main.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

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
    public void loopingTestProposals() throws InterruptedException{
        Random rnd = new Random();
        int number =0;
        while(number<10){
            Proposal proposal = new Proposal("Test Proposals", 50);
            proposal.setAuthor(generateRandomChars("abcdefghijklmnopqrst",rnd.nextInt(10)));
            proposal.setVotes( rnd.nextInt(1000));
            tester.sendTestProposal(proposal);
            Thread.sleep(10000);
            number++;
        }
    }
    @Test
    public void loopingTestParticipants() throws InterruptedException{
        String[] natTemplate ={"Spain","Canada","EEUU","Chile","Mexico","France","Ireland","Portugal","Alaska"};
        Random rnd = new Random();
        int number =0;
        while(number<10){
            Participant participant = new Participant(generateRandomChars("participantsqw",8), generateRandomChars("apelidoguay",6),
                    generateRandomChars("blatinfzp",9)+ "@.gmail.com",
                    generateRandomChars("abcdefghijklmnopqrstuvwxyz",rnd.nextInt(25)), new Date(), "medaigua", natTemplate[rnd.nextInt(natTemplate.length)], String.valueOf(rnd.nextInt(100000)));
            tester.sendTestParticipant(participant);
            Thread.sleep(10000);
            number++;
        }
    }

    private static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }

        return sb.toString();
    }
}
