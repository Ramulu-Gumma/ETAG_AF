package TestNGRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@CucumberOptions(
    features = "Features/MDHLogin.feature",
    glue = {"com.StepDefinations", "com.Hooks"},
    monochrome = true,
    tags = "@smoke",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "rerun:target/rerun.txt"
    }
    
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    public void beforeSuite() {
    	// Clean previous rerun file
        try {
            Files.deleteIfExists(Path.of("target/rerun.txt"));
        } catch (IOException e) {
            System.out.println("No previous rerun file found");
        }
        System.out.println("Test Suite Started");
    }

    @AfterSuite
    public void afterSuite() {
    	// Check if there are failed tests
        File rerunFile = new File("target/rerun.txt");
        if (rerunFile.exists() && rerunFile.length() > 0) {
            System.out.println("There are failed tests. Run FailedTestRunner to retry them.");
        } else {
            System.out.println("All tests passed!");
        }
        System.out.println("Test Suite Completed");
    }
    
    private void runFailedTests() {
        try {
            // Execute the failed tests runner
            Process process = Runtime.getRuntime().exec(
                "mvn test -Dtest=FailedTestRunner -q"
            );
            process.waitFor();
            
            // Check if rerun was successful
            File rerun2File = new File("target/rerun2.txt");
            if (rerun2File.exists() && rerun2File.length() > 0) {
                System.out.println("Some tests still failing after retry");
            } else {
                System.out.println("All failed tests passed on retry!");
            }
        } catch (Exception e) {
            System.out.println("Error during failed tests execution: " + e.getMessage());
        }
    }
}

