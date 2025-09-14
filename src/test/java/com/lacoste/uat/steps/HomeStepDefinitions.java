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

@Slf4j
public class HomeStepDefinitions {
  protected final AppiumDriverInitializer mobileDriver;
  private WebElement element;

  public HomeStepDefinitions(AppiumDriverInitializer mobileDriver) {
    this.mobileDriver = mobileDriver;
  }



  @When("I open product catalog")
  public void openCatalog(){
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-Cart drop zone")));
    Assert.assertTrue(element.isDisplayed(), "element " + element + "is not displayed");
  }


  @When("I add product to cart")
  public void validateProduct() {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-ADD TO CART")));
    element.click();
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-REMOVE")));
    Assert.assertTrue(element.isDisplayed(), "element " + element + "is not displayed");
  }

  @When("I expect adding successfully the product")
  public void validateCart() {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-Cart")));
    element.click();

    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-Item")));
    Assert.assertTrue(element.isDisplayed(), "element " + element + "is not displayed");
  }

  @When("I remove product from cart")
  public void removeProduct() {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-REMOVE")));
    element.click();
  }

  @When("I expect product to be removed from cart")
  public void checkRemovedProduct() {
    Boolean removeElementDisplay = mobileDriver.driverWait().until(ExpectedConditions
      .invisibilityOfElementLocated(AppiumBy.accessibilityId("test-REMOVE")));
    Assert.assertTrue(removeElementDisplay, "element " + removeElementDisplay + "is always displayed");
  }
}
