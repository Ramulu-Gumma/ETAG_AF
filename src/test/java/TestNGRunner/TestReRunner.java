package TestNGRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "@target/rerun.txt",
    glue = {"com.StepDefinations", "com.Hooks"},
    monochrome = true,
    plugin = {
        "pretty",
        "html:target/rerun-reports/cucumber.html",
        "rerun:target/rerun2.txt"  // For second retry if needed
    }
)
public class TestReRunner extends AbstractTestNGCucumberTests {
}