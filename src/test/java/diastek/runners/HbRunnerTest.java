package diastek.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin = {
                "html:target/cucumber/cucumber.html" ,
                "me.jvt.cucumber.report.PrettyReports:target/cucumber/"
        },
        glue = {"diastek"},
         //glue = {"diastek.stepdefinitions","diastek.hooks"},
        features = "src/test/resources/features",
        tags = "@sanity")
//tags = "@regression")
//tags = "@demo")
public class HbRunnerTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
