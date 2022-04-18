package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/Features"},
        glue = {"hiveStepDefinition","hiveCommon"},
        plugin = {"html:target/cucumber-report.html"},
        dryRun = false,
        monochrome = false,
        tags = "@LoadApp"

)

public class TestRunner {

}
