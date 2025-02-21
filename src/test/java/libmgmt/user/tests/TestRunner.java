package libmgmt.user.tests;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/libmgmt/user/tests/features",
        glue="libmgmt.user.tests.steps",
        plugin={"pretty","html:target/cucumber-reports.html"})


public class TestRunner {
}