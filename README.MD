# lacoste mobile application tests


# Prerequisites
* Java 21
* Maven 3.x
* Cucumber 7
* appium 9.x
* automator2
* appium inspector
* android virtual device
* XCUITest
* adb 

# APPIUM Drivers :
1- Appium documentation: https://appium.github.io/appium/docs/en/2.0/

2- UIautomator2: UIAutomator2 is a user interface testing framework for Android, developed by Google. It automates interactions with Android applications, even if their source code is not available (ideal for black-box testing).
*UiAutomator2 Driver Documentation: https://github.com/appium/appium-uiautomator2-driver

3- XcuiTest: XCUITest is Apple's official framework for automating testing of iOS applications (iPhone, iPad, etc.). It is part of Xcode and leverages XCTest technology, which Apple uses for unit and interface testing.
*XCUITest Driver Documentation:  https://github.com/appium/appium-xcuitest-driver

4- Cucumber Expressions: https://github.com/cucumber/cucumber-expressions#readme

5- Appium Inspector: https://github.com/appium/appium-inspector

6- Cucumber JUnit Documentation: https://cucumber.io/docs/cucumber/api/?lang=java#junit

7- Cucumber TestNG Samples: https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-testng/src/test/java/io/cucumber/testng

8- Gherkin Syntax and Step Organization: https://cucumber.io/docs/gherkin/



# Install 
1- install Appium command line interface (CLI)server
* check node and npm (node package manageri are installed using : node -v   and  npm -v -> else install node.js from link nodejs.org/en/download/ (nb: use latest and not current version )
* install Appium using npm command : npm install -g appium@next --> commande to start appium : appium  , commande to locate appium path : where appium , commande to uninstall appium : npm uninstall -g appium

2- install uiAutomator2:
*check appium driver -->  with command : appium driver -h   or appium driver list
* install drivers exp :   uiAutomator2 : appium driver install uiautomator2

3- install appium inspector:
* install appium inspector : https://github.com/appium/appium-inspector/releases

4- install java JDK :  Please refrain from using Java JDK 8, 9, or 10 for any automation development involving Appium, TestNG or Cucumber with TestNG. The latest version of TestNG no longer supports versions below Java 11, and similarly, Appium requires a minimum of Java 11

5- install android studio :
* download and install android studio : https://developer.android.com/studio?hl=fr
* edit the system environement variable  : ANDROID_HOME with the sdk path
* add %ANDROID_HOME%\cmdline_tools  and %ANDROID_HOME%\platform-tools  to system environement variable path

6- install XCUITest for iOS inspector  https://github.com/appium/appium-xcuitest-driver


# Usage

1- Run appium server with command appium , wait until appium run correctly and check url server 
2- Run appium inspector
* Use appium server and port 
* Use capabilities exp:
  - "appium:automationName": "uiautomator2",
  - "appium:platformName": "Android",
  - "appium:app": "C:\\Users\\aridh\\uat-lacoste-tests\\src\\test\\resources\\androidApp\\android.app.apk",
3- Run android studiod and create a virtual device 
4- Get device name with this command : adb devices
5- Start session to inspect element

# Run Test
* Navigate to the project directory
* cd userDir/Project
* Run all test : mvn clean test
* Run a unit test : mvn test -Dcucumber.options="--tags @tag1"



