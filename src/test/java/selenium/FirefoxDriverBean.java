package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Antonio Nicolas on 19/04/2017.
 */
@Configuration
public class FirefoxDriverBean {

    @Bean(name = "firefoxDriver")
    public WebDriver getDriver(){
        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        return driver;
    }

}
