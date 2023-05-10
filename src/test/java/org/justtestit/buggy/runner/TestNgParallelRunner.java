package org.justtestit.buggy.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * This class is used to configure Cucumber options and run the test(s) with TestNG in parallel mode.
 *
 * @author Jaspal Aujla
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.justtestit.buggy.steps"},
        monochrome = true,
        dryRun = false,
        publish = false,
        tags="@regression",
        plugin = {"pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        }
)
public class TestNgParallelRunner extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }

}
