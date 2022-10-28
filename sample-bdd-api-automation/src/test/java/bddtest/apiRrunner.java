package bddtest;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/feature/ApiAutomationTest.feature",
        glue = {"bddtest"}, dryRun = true)
//tags= {"@APITest1"})
public class apiRrunner {
}
