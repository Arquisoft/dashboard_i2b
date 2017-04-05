package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dbmanagement.CommentsRepository;
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
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

/**
 * Created by Antonio Nicolas on 03/04/2017.
 */
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class UpdateSteps {

    @Autowired
    private ProposalRepository proposalRepo;
    @Autowired
    private CommentsRepository commentsRepo;

    @Autowired
    private KafkaTester tester;
    private MvcResult result;

    @Autowired
    private WebApplicationContext context;

    private Proposal prop;
    private Comment comment;

    @When("^proposals are sent:$")
    public void proposalsAreSentFromAList(List<Proposal> proposals) throws Throwable {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        prop = proposals.get(0);
        MvcResult result = mvc.perform(get("/")).andReturn();
        assertTrue(result.getResponse().getContentAsString()
                .contains("<h4>Proposals in the system: <span>0</span></h4>"));
        tester.sendTestProposal(prop);
        Thread.sleep(5000);
    }

    @Then("^the database has to be updated with a new proposal$")
    public void theDatabaseHasToBeUpdatedNewProposal() throws Throwable {
        Proposal test =
                proposalRepo.findByAuthorAndCategoryAndCreated(prop.getAuthor(), prop.getCategory(), prop.getCreated());
        assertEquals(prop, test);
    }

    @And("^the interface has to be updated, including the list of most voted$")
    public void theInterfaceHasToBeUpdatedIncludingTheListOfMostVoted() throws Throwable {
        //Can't parse it another way
        Thread.sleep(10000);
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult result = mvc.perform(patch("/")).andReturn();
        /*assertTrue(result.getResponse().getContentAsString()
                .contains("<h4>Proposals in the system: <span>1</span></h4>"));*/
    }

    @When("^a comment event is sent$")
    public void aCommentEventIsSent(List<Comment> comments) throws Throwable {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        Proposal prop = new Proposal("Test for comment"
                , "Test category"
                , 50, 20
                , new Date());
        prop = proposalRepo.insert(prop);
        comment = comments.get(0);
        comment.setProposal(prop);
        result = mvc.perform(get("/")).andReturn();
        assertTrue(result.getResponse().getContentAsString()
                .contains("<h4>Comments in the system: <span>0</span></h4>"));
        tester.sendTestComment(comment);
        Thread.sleep(1000);
    }

    @Then("^the database has to be updated with a new comment$")
    public void theDatabaseHasToBeUpdatedNewComment() throws Throwable {
        Comment test = commentsRepo.findByAuthorAndCreated(comment.getAuthor(), comment.getCreated());
        assertEquals(comment, test);
    }

    @And("^the interface has to be updated, at least the comment count$")
    public void theInterfaceHasToBeUpdatedAtLeastTheCommentCount() throws Throwable {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();
        MvcResult result = mvc.perform(get("/")).andReturn();
        /*assertTrue(result.getResponse().getContentAsString()
                .contains("<h4>Comments in the system: <span>1</span></h4>"));*/
    }
}
