package com.lacoste.uat.testprovider.androidprovider;

import com.lacoste.uat.chore.AppiumDriverInitializer;
import com.lacoste.uat.testprovider.defaultprovider.BaseTest;
import com.lacoste.uat.testprovider.defaultprovider.HomePageProvider;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Profile("android")
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class HomePageAndroid extends BaseTest implements HomePageProvider {

  WebElement element;
  public HomePageAndroid(AppiumDriverInitializer mobileDriver) {
    super(mobileDriver);
  }

  @Override
  public void openCatalogAndCheckProduct() {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-Cart drop zone")));
    Assert.assertTrue(element.isDisplayed(), "element " + element + "is not displayed");
  }

  @Override
  public void validateSelectedProduct() {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-ADD TO CART")));
    element.click();
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-REMOVE")));
    Assert.assertTrue(element.isDisplayed(), "element " + element + "is not displayed");
  }

  @Override
  public void validateAddCartOperation() {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-Cart")));
    element.click();

    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-Item")));
    Assert.assertTrue(element.isDisplayed(), "element " + element + "is not displayed");
  }

  @Override
  public void removeProductFromCart() {
    element = mobileDriver.driverWait().until(ExpectedConditions
      .visibilityOfElementLocated(AppiumBy.accessibilityId("test-REMOVE")));
    element.click();
  }

  @Override
  public void checkRemovedProductFromCart() {
    Boolean removeElementDisplay = mobileDriver.driverWait().until(ExpectedConditions
      .invisibilityOfElementLocated(AppiumBy.accessibilityId("test-REMOVE")));
    Assert.assertTrue(removeElementDisplay, "element " + removeElementDisplay + "is always displayed");
  }
}
