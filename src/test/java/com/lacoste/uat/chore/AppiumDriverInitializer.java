package com.lacoste.uat.chore;

import com.lacoste.uat.conf.CapabilitiesManager;
import com.lacoste.uat.conf.UatProperties;
import com.lacoste.uat.data.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Slf4j
public class AppiumDriverInitializer {

  private AppiumDriver driver;
  CapabilitiesManager capabilitiesManager;
  UatProperties envProperties;
  private WebDriverWait defaultWebDriverWait;


  @Autowired
  public AppiumDriverInitializer(CapabilitiesManager capabilitiesManager, UatProperties envProperties) {
    this.capabilitiesManager = capabilitiesManager;
    this.envProperties = envProperties;
  }

  public AppiumDriver getDriver() {
    return driver;
  }

  public WebDriverWait driverWait() {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(20));
  }

  @Before
  public void driverInitializer() throws MalformedURLException {
    DesiredCapabilities caps = capabilitiesManager.buildCapabilities();
    Platforms platforms = Platforms.from(envProperties.getCurrentEnv());
    driver = switch (platforms) {
      case ANDROID -> new AndroidDriver(new URL("http://localhost:4723/"), caps);
      case IOS -> new IOSDriver(new URL("http://localhost:4723/"), caps);
    };

    log.info("Driver initialized for platform: {}", platforms);
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
      driver = null;
      log.info("Driver quit successfully");
    }
  }
}
