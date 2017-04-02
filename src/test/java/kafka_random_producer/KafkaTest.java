package kafka_random_producer;

import dbmanagement.ProposalRepository;
import domain.Participant;
import domain.Proposal;
import main.Application;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KafkaTest {

    private static final int LOOP_TEST = 3;
    @Autowired
    KafkaTester tester;

    @Autowired
    private ProposalRepository repo;

    @Test
    public void test() throws InterruptedException {
        //| "Test author" | "Test category" | 50 | 20 | Mar 31, 2017|
        Proposal proposal = new Proposal("Test Proposals", 50);
        proposal.setAuthor("Test author");
        proposal.setCategory("Test category");
        proposal.setVotes(50);
        proposal.setMinimalSupport(20);
        proposal.setCreated(new Date());
        tester.sendTestProposal(proposal);
        Thread.sleep(10000);
        Proposal test = repo.findByAuthorAndCategoryAndCreated(
                proposal.getAuthor()
                , proposal.getCategory()
                , proposal.getCreated());
        assertEquals(test, proposal);
        repo.delete(test);
    }

    @Test
    public void loopingTestProposals() throws InterruptedException{
        Random rnd = new Random();
        for(int i = 0; i<LOOP_TEST; i++){
            Proposal proposal = new Proposal("Test Proposals", 50);
            proposal.setAuthor(generateRandomChars("abcdefghijklmnopqrst",rnd.nextInt(10)));
            proposal.setVotes( rnd.nextInt(1000));
            tester.sendTestProposal(proposal);
            Thread.sleep(5000);
        }
    }
    //@Test
    public void loopingTestParticipants() throws InterruptedException{
        String[] natTemplate ={"Spain","Canada","EEUU","Chile","Mexico","France","Ireland","Portugal","Alaska"};
        Random rnd = new Random();
        for(int i = 0; i<LOOP_TEST; i++){
            Participant participant = new Participant(
                    generateRandomChars("participantsqw",8)
                    , generateRandomChars("apelidoguay",6)
                    ,generateRandomChars("blatinfzp",9)+ "@.gmail.com"
                    , generateRandomChars("abcdefghijklmnopqrstuvwxyz",rnd.nextInt(25))
                    , new Date(), "medaigua", natTemplate[rnd.nextInt(natTemplate.length)]
                    , String.valueOf(rnd.nextInt(100000)));
            tester.sendTestParticipant(participant);
            Thread.sleep(5000);
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
