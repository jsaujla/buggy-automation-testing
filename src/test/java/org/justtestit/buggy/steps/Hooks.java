package org.justtestit.buggy.steps;

import commons.properties.PropertiesManager;
import commons.web.WebDriverManager;
import org.justtestit.buggy.constant.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * This class manages common steps of test scenarios that need to be performed before and after each test.
 */
public class Hooks {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    //********** OBJECT DECLARATION **********
    private final DependencyContainer dependencyContainer;

    /**
     * Constructor for Hooks class that takes a DependencyContainer object as a parameter.
     *
     * @param dependencyContainer the DependencyContainer object to be used in the Hooks class
     */
    public Hooks(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;
    }

    /**
     * This method sets up the environment before executing a test scenario.
     *
     * @param scenario the scenario object that represents the current test scenario being executed
     */
    @Before
    public void setUp(Scenario scenario) {
        LOGGER.info("XXXXXXXXXX" + " START TEST SCENARIO " + "XXXXXXXXXX");
        LOGGER.info("Scenario: " + scenario.getName());

        dependencyContainer.webDriverManager = new WebDriverManager(setConfig());
        maximizeWindow();
        setImplicitlyWait();
        setPageLoadTimeout();
    }

    /**
     * This method is called after each scenario and performs necessary cleanup tasks.
     *
     * @param scenario The scenario that just ran
     */
    @After()
    public void tearDown(Scenario scenario) {
        captureScreenshot(scenario);
        quitWebDriver();
        LOGGER.info("XXXXXXXXXX" + " END TEST SCENARIO " + "XXXXXXXXXX");
    }

    /**
     * Reads the configuration properties file based on the environment type provided by command-line execution.
     * If environment type not provided by command-line execution, the default value 'config-qa' will be used.
     *
     * @return a PropertiesManager object containing the loaded properties
     */
    private PropertiesManager setConfig() {
        String environmentType = System.getProperty("config.file", Constants.CONFIG_QA);
        String configFilePath = null;
        switch (environmentType) {
            case Constants.CONFIG_DEV:
                configFilePath = Constants.DEV_CONFIG_PROPERTIES_PATH;
                break;
            case Constants.CONFIG_QA:
                configFilePath = Constants.QA_CONFIG_PROPERTIES_PATH;
                break;
            case Constants.CONFIG_UAT:
                configFilePath = Constants.UAT_CONFIG_PROPERTIES_PATH;
                break;
            case Constants.CONFIG_PROD:
                configFilePath = Constants.PROD_CONFIG_PROPERTIES_PATH;
                break;
        }
        return dependencyContainer.propertiesManager = new PropertiesManager(configFilePath);
    }

    /**
     * This method maximizes the web browser window if the 'windows.maximize' property in the configuration file is set to true.
     */
    private void maximizeWindow() {
        if(dependencyContainer.propertiesManager.getPropertyAsBoolean("windows.maximize")) {
            dependencyContainer.webDriverManager.getDriver().manage().window().maximize();
            LOGGER.info("Browser windows maximized successfully");
        }
    }

    /**
     * This method sets the implicit wait timeout if the 'implicitly.wait' property in the configuration file is set to a non-zero value.
     */
    private void setImplicitlyWait() {
        long implicitlyWaitTimeout = dependencyContainer.propertiesManager.getPropertyAsLong("implicitly.wait");
        if (implicitlyWaitTimeout > 0) {
            dependencyContainer.webDriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitTimeout));
            LOGGER.info("Implicit wait '" + implicitlyWaitTimeout + " second(s)' implemented successfully");
        } else {
            LOGGER.info("Implicit wait not implemented");
        }
    }

    /**
     * This method sets the page load timeout if the 'page.load.timeout' property in the configuration file is set to a non-zero value.
     */
    private void setPageLoadTimeout() {
        long pageLoadTimeout = dependencyContainer.propertiesManager.getPropertyAsLong("page.load.timeout");
        if (pageLoadTimeout > 0) {
            dependencyContainer.webDriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
            LOGGER.info("Page load timeout '" + pageLoadTimeout + " second(s)' implemented successfully");
        } else {
            LOGGER.info("Page load timeout not implemented");
        }
    }

    /**
     * Captures a screenshot and attaches it to the given scenario if the scenario has failed.
     *
     * @param scenario The scenario to attach the screenshot to
     */
    private void captureScreenshot(Scenario scenario) {
        if(scenario.isFailed() && dependencyContainer.webDriverManager != null) {
            byte[] screenshot = dependencyContainer.webDriverManager.getTakesScreenshot().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "FailedScreenshot_" + scenario.getName());
            LOGGER.info("Screenshot captured and attached to the given scenario");
        } else {
            LOGGER.info("Skipped capturing screenshot, because webDriverManager is null");
        }
    }

    /**
     * Closes the web browser window(s) and ends the WebDriver session.
     */
    private void quitWebDriver() {
        if (dependencyContainer.webDriverManager != null) {
            dependencyContainer.webDriverManager.getDriver().quit();
            LOGGER.info("Closed web browser window(s) and ended the WebDriver session");
        } else {
            LOGGER.info("Skipped quiting WebDriver session, because webDriverManager is already null");
        }
    }

}
