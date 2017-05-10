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
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;
import selenium.FirefoxDriverBean;
import statisticsCalculator.ProposalsProcessor;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Antonio Nicolas on 03/04/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class, FirefoxDriverBean.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UpdateSteps {

    @Autowired
    private ProposalRepository proposalRepo;
    @Autowired
    private CommentsRepository commentsRepo;

    @Autowired
    private KafkaTester tester;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    @Qualifier("firefoxDriver")
    private WebDriver driver;

    private String baseURL = "http://localhost:8090";

    /**
     * We have a title for each counter (Proposals, Comments, Participants), we'll use this to find it, they're as
     * h4
     * @param title The title for the counter
     * @return The span under the h4 web element if found, else it's going to fail the tests if it doesn't find it
     */
    private WebElement findTitleCounter(String title){

        String spanInTitleXpath = "//h4[contains(text(), '%s')]//span";
        WebElement element = driver.findElement(
                By.xpath(String.format(spanInTitleXpath, title)));
        assertNotNull(element);
        return element;
    }

    @When("^proposals are sent:$")
    public void proposalsAreSentFromAList(List<Proposal> proposals) throws Throwable {
        Proposal prop = proposals.get(0);
        driver.get(baseURL);
        Thread.sleep(1000);
        // Find the span text
        WebElement element = findTitleCounter("Proposals in the system:");
        tester.sendTestProposalCreate(prop);
        assertEquals("0", element.getText());
    }

    @Then("^the interface has to be updated, including the list of most voted$")
    public void theInterfaceHasToBeUpdatedIncludingTheListOfMostVoted() throws Throwable {
        Thread.sleep(5000);
        WebElement element = findTitleCounter("Proposals in the system:");
        //assertEquals("1", element.getText());
        proposalRepo.deleteAll();
    }

    @When("^a comment event is sent$")
    public void aCommentEventIsSent(List<Comment> comments) throws Throwable {
        Proposal prop = new Proposal("Test for comment"
                , "Test category"
                , 50, 20
                , new Date());
        prop = proposalRepo.insert(prop);
        Comment comment = comments.get(0);
        comment.setProposal(prop);
        WebElement element = findTitleCounter("Comments in the system:");
        assertEquals("0", element.getText()); //Before comment arrives
        tester.sendTestCommentCreate(comment);
    }

    @Then("^the interface has to be updated, at least the comment count$")
    public void theInterfaceHasToBeUpdatedAtLeastTheCommentCount() throws Throwable {
        Thread.sleep(3000);
        WebElement element = findTitleCounter("Comments in the system:");
        //assertEquals("1", element.getText());
        commentsRepo.deleteAll();
        proposalRepo.deleteAll();
        driver.close();
    }
}
