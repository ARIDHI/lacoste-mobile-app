package com.lacoste.uat.chore;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(plugin = {"pretty","html:target/cucumber-report/core_plugin_report.html",
  "json:target/cucumber-reports/Cucumber.json"
  ,"rerun:target/rerun.txt"},
  features = "src/test/resources/feature",
  glue= {"com.lacoste.uat", "com.lacoste.uat.steps"},
  publish = true)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
  @Override
  @DataProvider(parallel = false)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
