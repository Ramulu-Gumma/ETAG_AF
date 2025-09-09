package TestNGRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "Features/MDHLogin.feature",
    glue = {"com.StepDefinations", "com.Hooks"},
    monochrome = true,
    tags = "@smoke", 
    plugin = {
        "pretty",
        "html:target/final-reports/cucumber.html",
        "rerun:target/final-rerun.txt"
    }
)
public class AutomatedRetryTestRunner extends AbstractTestNGCucumberTests {
}
