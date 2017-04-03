package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dbmanagement.ProposalRepository;
import domain.Comment;
import domain.Proposal;
import kafka_random_producer.KafkaTester;
import main.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by Antonio Nicolas on 03/04/2017.
 */
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class UpdateSteps {

    @Autowired
    private ProposalRepository proposalRepo;

    @Autowired
    private KafkaTester tester;
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    private Proposal prop;
    private Comment comment;

    @When("^proposals are sent:$")
    public void proposalsAreSentFromAList(List<Proposal> proposals) throws Throwable {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
        prop = proposals.get(0);
        MvcResult result = mvc.perform(get("/")).andReturn();
        assertTrue(result.getResponse().getContentAsString()
                .contains("<label>nº of proposals in the system: <p>0</p></label>"));
        tester.sendTestProposal(prop);
        Thread.sleep(1000);
    }

    @Then("^the database has to be updated with a new proposal$")
    public void theDatabaseHasToBeUpdatedNewProposal() throws Throwable {
        Proposal test = proposalRepo
                .findByAuthorAndCategoryAndCreated(prop.getAuthor(), prop.getCategory(), prop.getCreated());
        assertEquals(prop, test);
    }

    @And("^the interface has to be updated, including the list of most voted$")
    public void theInterfaceHasToBeUpdatedIncludingTheListOfMostVoted() throws Throwable {
        //Can't parse it another way
        MvcResult result = mvc.perform(get("/")).andReturn();
        assertTrue(result.getResponse().getContentAsString()
                .contains("<label>nº of proposals in the system: <p>1</p></label>"));
    }

    @When("^a comment event is sent$")
    public void aCommentEventIsSent(List<Comment> comments) throws Throwable {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
        comment = comments.get(0);
        MvcResult result = mvc.perform(get("/")).andReturn();
        assertTrue(result.getResponse().getContentAsString()
                .contains("<label>nº of proposals in the system: <p>0</p></label>"));
        tester.sendTestComment(comment);
        Thread.sleep(1000);
    }

    @Then("^the database has to be updated with a new comment$")
    public void theDatabaseHasToBeUpdatedNewComment() throws Throwable {

        //assertEquals(prop, test);
    }

    @And("^the interface has to be updated, at least the comment count$")
    public void theInterfaceHasToBeUpdatedAtLeastTheCommentCount() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
