package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources",
        glue = "steps", strict = true,
        plugin = {"pretty","json:target/cucumber-reports/cucumber.json","html:target/cucumber-html-reports.html"})
public class TestRunner extends AbstractTestNGCucumberTests {

}
