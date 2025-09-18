package com.lacoste.uat.testprovider;

import com.lacoste.uat.chore.AppiumDriverInitializer;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static org.testng.Assert.assertTrue;


@Profile("android")
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class LoginPageAndroid extends BaseTest implements LoginPageProvider{
  WebElement element;
  public LoginPageAndroid(AppiumDriverInitializer mobileDriver) {
    super(mobileDriver);
  }

  @Override
  public void logInPagecheck() {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.id("android:id/content")));
    Assert.assertTrue(element.isDisplayed(), "element " + element + "is not displayed");
  }

  @Override
  public void logInWithMultiCredentials(String credentials) {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId(credentials)));
    element.click();
    element = mobileDriver.getDriver().findElement(AppiumBy.accessibilityId("test-LOGIN"));
    element.click();
  }

  @Override
  public void iCheckloginResult(String expectedResult) {

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

  @Override
  public void logOutCheck() {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-Menu")));
    element.click();
    element = mobileDriver.getDriver().findElement(By.xpath("//*[contains(@text, 'LOGOUT')]"));
    element.click();
  }
}
