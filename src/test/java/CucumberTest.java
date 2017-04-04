import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dbmanagement.CommentsRepository;
import dbmanagement.ParticipantsRepository;
import dbmanagement.ProposalRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Antonio Nicolas on 31/03/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class CucumberTest {

    @Autowired
    private ProposalRepository proposalRepo;
    @Autowired
    private ParticipantsRepository participantRepo;
    @Autowired
    private CommentsRepository commentRepo;

    @Before
    public void setUp() throws Exception {
        proposalRepo.deleteAll();
        participantRepo.deleteAll();
        commentRepo.deleteAll();
    }
}
