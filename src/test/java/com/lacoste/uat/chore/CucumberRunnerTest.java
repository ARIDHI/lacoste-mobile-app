package com.lacoste.uat.chore;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(plugin = {"pretty","html:target/cucumber-report/core_plugin_report.html",
  "json:target/cucumber-reports/Cucumber.json"
  ,"rerun:target/rerun.txt",
  "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
  features = "src/test/resources/feature",
  glue= {"com/lacost/uat"},
  publish = true)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
  @DataProvider(parallel = true)
  @Override
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
