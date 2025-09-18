package com.lacoste.uat.testprovider;

import com.lacoste.uat.chore.AppiumDriverInitializer;
import org.openqa.selenium.WebElement;

public class BaseTest {
  protected final AppiumDriverInitializer mobileDriver;


  public BaseTest(AppiumDriverInitializer mobileDriver) {
    this.mobileDriver = mobileDriver;
  }

}
