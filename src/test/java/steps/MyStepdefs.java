package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import dbmanagement.ProposalRepository;
import domain.Proposal;
import kafka_random_producer.KafkaTester;
import main.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Antonio Nicolas on 31/03/2017.
 */
@ContextConfiguration
@SpringBootTest(classes = Application.class)
public class MyStepdefs {

    @Autowired
    private KafkaTester tester;
    @Autowired
    private ProposalRepository repo;

    private Proposal prop;

    @Given("^a list of proposals:$")
    public void aListOfProposals(List<Proposal> props) throws Throwable {
        prop = props.get(0);
        tester.sendTestProposal(prop);
        Thread.sleep(1000);
    }


    @Then("^database contains at least one Proposal$")
    public void databaseContainsAtLeastOneProposal() throws Throwable {
        Proposal test = repo.findByAuthorAndCategoryAndCreated(prop.getAuthor(), prop.getCategory(), prop.getCreated());
        assertEquals(prop, test);
    }
}
