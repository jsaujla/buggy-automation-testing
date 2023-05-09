# README #

### About the repository ###
* The repository contains UI test automation assessment work on website 'buggy.justtestit'.
* There are 5 test scenarios automated. Few test scenarios execute with different test data, so total tests are 15.

### Prerequisite for execution ###
* Java 11 or higher installed
* Maven installed
* JAVA_HOME environment variable configured
* MAVEN_HOME environment variable configured
* Google Chrome web browser installed

### Prerequisite for development ###
* Java 11 or higher installed
* Maven installed
* JAVA_HOME environment variable configured
* MAVEN_HOME environment variable configured
* Java IDE installed (IntelliJ IDEA or Eclipse)
* TestNG plugin installed in IDE
* Cucumber plugin installed in IDE
* Google Chrome web browser installed

### Technologies used ###
* Java
* Maven
* TestNG
* Selenium WebDriver
* Web Driver Manager
* Cucumber

### How do I get set up ###
* Download the repository into system
* Unzip the repository

### How to execute tests ###
* Open Command Prompt or Windows PowerShell
* Go to project directory
* Execute below command to run tests with default properties file, default browser and default tag(s):
  * Default config properties file is QA '\src\test\resources\config-qa.properties'. Note: 'config-qa.properties' includes PROD URL because there is no access to QA Env
  * Default browser is as per configured in config properties file. Currently, it is 'chrome'
  * Default tag(s) is as per configured in '\src\test\org\justtestit\buggy\runner\TestNgRunner.class'
```
mvn clean verify
```
* Execute tests on specific environment (dev, qa, uat, prod):
```
mvn clean verify -Dconfig.file=config-dev
mvn clean verify -Dconfig.file=config-qa
mvn clean verify -Dconfig.file=config-uat
mvn clean verify -Dconfig.file=config-prod
```
* Execute tests on a specific browser (chrome, firefox, edge, ie, safari):
  * Note: Additional configuration needs to be done on local system/browser to execute tests on IE and Safari browsers
```
mvn clean verify -Dbrowser.name=chrome
mvn clean verify -Dbrowser.name=firefox
mvn clean verify -Dbrowser.name=edge
mvn clean verify -Dbrowser.name=ie
mvn clean verify -Dbrowser.name=safari
```
* Execute tests with specific tag(s):
```
mvn clean verify -Dcucumber.filter.tags="@smoke"
mvn clean verify -Dcucumber.filter.tags="@regression"
```
* Above mvn command parameters can also be used together. For example:
```
mvn clean verify -Dconfig.file=config-qa -Dbrowser.name=chrome -Dcucumber.filter.tags="@smoke"
```

### Test execution results ###
* Cucumber default HTML report 'cucumber-reports.html' will be available under directory 'target' after test execution finished
  * The screenshot can be seen within the report 'cucumber-reports.html' just below the failed test scenario 
* The test execution logs will be available under directory 'target\log' after test execution finished

### Browser driver exe ###
* By default, WebDriverManager automatically download/handle the driver exe(s) required to execute tests on a specific browser.
* In case, to handle browser driver exe(s) manually, follow below steps:
  1. Go to config properties file, set web.driver.manager=false
  2. Download the compatible browser driver exe(s) and paste under directory/folder 'browser-drivers' within the project 'buggy-automation-testing' by following below naming:
     * chromedriver.exe
     * geckodriver.exe
     * MicrosoftWebDriver.exe
     * IEDriverServer.exe

### Project packages/structure ###
* BDD test scenarios, refer feature files under directory '\src\test\resources\features'
* Test script implementation, refer packages under directory '\src\test\java\org\justtestit\buggy'
* Generic utils, refer packages under directory '\src\main\java\commons'

### Who do I talk to ###
* For more information contact: Jaspal Aujla at [jaspal.qa@outlook.com](mailto:jaspal.qa@outlook.com) or [jsaujla1@gmail.com](mailto:jsaujla1@gmail.com)