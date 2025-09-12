package com.lacoste.uat.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomeStepDefinitions {
  @Given("connect to platform")
  public void connectToSession(){
    log.info("i'm connected");
  }
  @When("initialize session")
  public void initializeSession() {
    log.info("i'm login");
  }

  @When("validate session")
  public void validateSession() {
    log.info("i log out");
  }


}
