import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import dbmanagement.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Antonio Nicolas on 31/03/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class CucumberTest {

}
