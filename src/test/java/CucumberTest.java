import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import statisticsCalculator.ProposalsProcessor;

/**
 * Created by Antonio Nicolas on 31/03/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class CucumberTest {

}
