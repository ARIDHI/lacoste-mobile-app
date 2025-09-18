package com.lacoste.uat.steps;

import com.lacoste.uat.testprovider.defaultprovider.LoginPageProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginStepDefinitions {

  protected final LoginPageProvider loginPageProvider;

  public LoginStepDefinitions(LoginPageProvider loginPageProvider) {
    this.loginPageProvider = loginPageProvider;
  }

  @Given("I am on the login page")
  public void imInLoginPage() {
    loginPageProvider.logInPagecheck();
  }

  @When("I login with {string} credentials")
  public void iLoginWith(String credentials) {
    loginPageProvider.logInWithMultiCredentials(credentials);
  }

  @When("I logout from application")
  public void iLogOut() {
    loginPageProvider.logOutCheck();
  }

  @Then("The app should display {string}")
  public void iShouldSee(String expectedResult) {
    loginPageProvider.iCheckloginResult(expectedResult);
  }
}
