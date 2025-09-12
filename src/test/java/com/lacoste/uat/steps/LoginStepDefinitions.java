package com.lacoste.uat.steps;

import com.lacoste.uat.chore.AppiumDriverInitializer;
import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

@Slf4j
public class LoginStepDefinitions {
  protected final AppiumDriverInitializer mobileDriver;
  private WebElement element;

  public LoginStepDefinitions(AppiumDriverInitializer mobileDriver) {
    this.mobileDriver = mobileDriver;
  }

  @Given("I am on the login page")
  public void imInLoginPage() {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.id("android:id/content")));
    Assert.assertTrue(element.isDisplayed(), "element " + element + "is not displayed");
  }

  @When("I login with {string} credentials")
  public void iLoginWith(String credentials) {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId(credentials)));
    element.click();
    element = mobileDriver.getDriver().findElement(AppiumBy.accessibilityId("test-LOGIN"));
    element.click();
  }

  @When("I logout from application")
  public void iLogOut() {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-Menu")));
    element.click();
    element = mobileDriver.getDriver().findElement(By.xpath("//*[contains(@text, 'LOGOUT')]"));
    element.click();

  }

  @Then("The app should display {string}")
  public void iShouldSee(String expectedResult) {

    switch (expectedResult) {

      case "success_login" -> assertTrue(mobileDriver.driverWait().until(ExpectedConditions
        .visibilityOfElementLocated(AppiumBy.accessibilityId("test-Cart drop zone"))).isDisplayed());

      case "invalid_user" -> Assert.assertEquals(mobileDriver.driverWait().until(ExpectedConditions
          .visibilityOfElementLocated(By.xpath("//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")))
        .getText(), "Sorry, this user has been locked out.");

      case "invalid_credentials" -> assertTrue(mobileDriver.driverWait().until(ExpectedConditions
        .visibilityOfElementLocated(AppiumBy.accessibilityId("test-PRODUCTS"))).isDisplayed());

      case "success_logout" -> assertTrue(mobileDriver.driverWait().until(ExpectedConditions
        .visibilityOfElementLocated(AppiumBy.accessibilityId("test-Login"))).isDisplayed());

      default -> throw new IllegalArgumentException("Unexpected result: " + expectedResult);
    }
  }
}
