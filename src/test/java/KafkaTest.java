import dbmanagement.Database;
import domain.Comment;
import domain.Participant;
import domain.Proposal;
import kafka_random_producer.KafkaTester;
import main.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.*;

/**
 * Created by Nicolás on 28/03/2017.
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KafkaTest {

    private static final int LOOP_TEST = 5;

    @Autowired
    private KafkaTester tester;

    @Autowired
    private Database database;

    private int idProducer;

    private String[] titleTemplate = {"What about building a wall?"
            , "Bigger univeristy for software engineer"
            , "Let's make uniovi great again"
            , "More efficient traffic lights"
            , "New hospital near Corredoria"};

    /*@After
    public void tearDown() throws Exception {
        database.reset();
    }*/

    /*@Before
    public void setUp() throws Exception {
        database.reset();
    }*/

    @Test
    public void test() throws InterruptedException {
        //| "Test author" | "Test category" | 50 | 20 | Mar 31, 2017|
        Proposal proposal = new Proposal("Test Proposals", 50);
        proposal.setAuthor("Test author");
        proposal.setCategory("Test category");
        proposal.setVotes(50);
        proposal.setMinimalSupport(20);
        proposal.setCreated(new Date());
        tester.sendTestProposalCreate(proposal);
        Thread.sleep(10000);
        Proposal test = database.findProposal(
                proposal.getAuthor()
                , proposal.getCategory()
                , proposal.getCreated());
        //assertEquals(test, proposal);
        //database.delete(test);
    }

    @Test
    public void loopingTestProposals() throws InterruptedException{
        Random rnd = new Random();
        for(int i = 0; i<LOOP_TEST; i++){
            Proposal proposal = new Proposal("Test Proposals", 50);
            proposal.setAuthor(generateRandomChars("abcdefghijklmnopqrst",rnd.nextInt(10)));
            proposal.setVotes( rnd.nextInt(1000));
            proposal.setTitle(titleTemplate[i]);
            proposal.setBody(generateRandomChars("abcdefghijklmnopqrstuvwxyz.,-",rnd.nextInt(1000)+500));
            tester.sendTestProposalCreate(proposal);
            Thread.sleep(5000);
        }
    }

    @Test
    public void loopingTestParticipants() throws InterruptedException{
        String[] natTemplate ={"Spain","Canada","EEUU","Chile","Mexico","France","Ireland","Portugal","Alaska"};
        Random rnd = new Random();
        Calendar cal = Calendar.getInstance();
        for(int i = 0; i<LOOP_TEST; i++){
            Participant participant = new Participant(
                    generateRandomChars("participantsqw",8)
                    , generateRandomChars("apelidoguay",6)
                    ,generateRandomChars("blatinfzp",9)+ "@.gmail.com"
                    , generateRandomChars("abcdefghijklmnopqrstuvwxyz",rnd.nextInt(25))
                    , cal.getTime()
                    , "medaigua"
                    , natTemplate[i]
                    , String.valueOf(rnd.nextInt(100000)));
            tester.sendTestParticipant(participant);
            Thread.sleep(5000);
        }
    }

    //Comment test
    @Test
    public void loopingTestComments() throws InterruptedException {
        String[] phrasesTemplate ={"Good ", "Excellent ", "I don't like that ", "I really like that ", "I enjoy the ",
                "What a shame of ",
                "You must be ashamed of doing this  "};
        Random rnd = new Random();
        List<Proposal> proposals = new ArrayList<>();
        for (String title : titleTemplate) {
            Proposal proposal = new Proposal("Test Proposals", 50);
            proposal.setTitle(title);
            proposals.add(database.insert(proposal));
        }

        Comment c;
        for(int i = 0; i < LOOP_TEST; i++){
            int number = rnd.nextInt(titleTemplate.length);
            Proposal chosenProposal =  proposals.get(number);
            c = new Comment("Test author, num: " + i
                    ,phrasesTemplate[rnd.nextInt(phrasesTemplate.length)] + "proposal"
                    , new Date()
                    , chosenProposal);
            tester.sendTestCommentCreate(c);
            Thread.sleep(1000);
        }

    }

    private String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }
        return sb.toString();
    }
}
