package com.lacoste.uat.testprovider.iosprovider;

import com.lacoste.uat.chore.AppiumDriverInitializer;
import com.lacoste.uat.testprovider.defaultprovider.BaseTest;
import com.lacoste.uat.testprovider.defaultprovider.LoginPageProvider;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static org.testng.Assert.assertTrue;


@Slf4j
@Component
@Profile("ios")
@Scope(SCOPE_CUCUMBER_GLUE)
public class LoginPageIOS extends BaseTest implements LoginPageProvider {
  WebElement element;
  public LoginPageIOS(AppiumDriverInitializer mobileDriver) {
    super(mobileDriver);
  }

  @Override
  public void logInPagecheck() {
    log.warn("My simulator doesn't work properly on my VMware machine because my computer is too slow.");
  }

  @Override
  public void logInWithMultiCredentials(String credentials) {
    log.warn("My simulator doesn't work properly on my VMware machine because my computer is too slow.");
  }

  @Override
  public void iCheckloginResult(String expectedResult) {
    log.warn("My simulator doesn't work properly on my VMware machine because my computer is too slow.");
  }

  @Override
  public void logOutCheck() {
    log.warn("My simulator doesn't work properly on my VMware machine because my computer is too slow.");
  }
}
