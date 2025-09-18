package com.lacoste.uat.testprovider.iosprovider;

import com.lacoste.uat.chore.AppiumDriverInitializer;
import com.lacoste.uat.testprovider.defaultprovider.BaseTest;
import com.lacoste.uat.testprovider.defaultprovider.HomePageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Profile("ios")
@Component
@Scope(SCOPE_CUCUMBER_GLUE)
@Slf4j
public class HomePageIOS extends BaseTest implements HomePageProvider {

  public HomePageIOS(AppiumDriverInitializer mobileDriver) {
    super(mobileDriver);
  }

  @Override
  public void openCatalogAndCheckProduct() {
    log.warn("My simulator doesn't work properly on my VMware machine because my computer is too slow.");
  }

  @Override
  public void validateSelectedProduct() {
    log.warn("My simulator doesn't work properly on my VMware machine because my computer is too slow.");
  }

  @Override
  public void validateAddCartOperation() {
    log.warn("My simulator doesn't work properly on my VMware machine because my computer is too slow.");
  }

  @Override
  public void removeProductFromCart() {
    log.warn("My simulator doesn't work properly on my VMware machine because my computer is too slow.");
  }

  @Override
  public void checkRemovedProductFromCart() {
    log.warn("My simulator doesn't work properly on my VMware machine because my computer is too slow.");
  }
}
