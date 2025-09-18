package com.lacoste.uat.conf;


import com.lacoste.uat.data.CapsPrifix;
import com.lacoste.uat.data.Platforms;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CapabilitiesManager {

    private final UatProperties envProperties;

    public DesiredCapabilities buildCapabilities() {
        CapsPrifix config =  envProperties.environment();
        DesiredCapabilities caps = new DesiredCapabilities();
        Platforms platforms = Platforms.from(envProperties.getCurrentEnv());
        caps.setCapability("appium:platformName", config.getPlatformName());
        caps.setCapability("appium:deviceName", config.getDeviceName());
        caps.setCapability("appium:automationName", config.getAutomationName());
        switch (platforms) {
            case ANDROID -> {
                caps.setCapability("appium:app", config.getApp());
                caps.setCapability("appium:appActivity", config.getAppActivity());
                caps.setCapability("appium:appPackage", config.getAppPackage());
                caps.setCapability("appium:adbExecTimeout", 60000);

            }
            case IOS -> {
                caps.setCapability("appium:app", config.getApp());
                caps.setCapability("appium:bundleId", config.getBundleId());
            }
        }
        log.info("build platform capabilities : {}", caps);
        return caps;
    }
}
