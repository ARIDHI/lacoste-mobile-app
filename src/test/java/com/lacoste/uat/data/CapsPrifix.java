package com.lacoste.uat.data;

import lombok.Data;

@Data
public class CapsPrifix {
    private String app;
    private String platformName;
    private String deviceName;
    private String automationName;
    private String appActivity;
    private String appPackage;
    private String systemPort;
    private String chromeDriverPort;
    private String wdaLocalPort;
    private String webkitDebugProxyPort;
    private String bundleId;
}
