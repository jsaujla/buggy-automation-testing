package commons.web;

import commons.properties.PropertiesManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

/**
 * This class centralizes Selenium WebDriver objects at a single place to provide them in ready form, making test script development easier.
 * The class also provides important logging by default.
 *
 * @author Jaspal Aujla
 */
public class WebDriverManager {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverManager.class);

    //********** OBJECT DECLARATION / INSTANCE VARIABLE **********
    private final int webDriverWaitTime;
    private final WebDriver driver;

    /**
     * Constructs a new WebDriverManager with the specified properties manager.
     *
     * @param propertiesManager the properties manager to use for configuring the WebDriverManager
     */
    public WebDriverManager(PropertiesManager propertiesManager) {
        LOGGER.info("Constructing WebDriverManager with the specified properties manager");
        this.webDriverWaitTime = propertiesManager.getPropertyAsInt("web.driver.wait");
        WebDriverInitializer webDriverInitializer = new WebDriverInitializer(propertiesManager);
        this.driver = webDriverInitializer.initializeWebDriver();
    }

    /**
     * Returns an initialized WebDriver.
     *
     * @return The initialized WebDriver object
     */
    public WebDriver getDriver() {
        LOGGER.info("Returning an initialized WebDriver");
        return driver;
    }

    /**
     * Returns an initialized WebDriverWait object with the specified wait time.
     *
     * @param waitTimeInSeconds The wait time in seconds. If not specified, the default value will be used
     * @return The initialized WebDriverWait object
     */
    public WebDriverWait getWebDriverWait(int... waitTimeInSeconds) {
        int waitTime = waitTimeInSeconds.length > 0 ? waitTimeInSeconds[0] : webDriverWaitTime;
        LOGGER.info("Creating WebDriverWait with wait time of " + waitTime + " second(s)");
        return new WebDriverWait(driver, Duration.ofSeconds(waitTime));
    }

    /**
     * Returns a WebElement located by the given By object.
     *
     * @param locator the By object used to locate the element
     * @return the located WebElement
     * @throws NoSuchElementException if the element is not found
     */
    public WebElement getWebElement(By locator) {
        try {
            WebElement webElement = driver.findElement(locator);
            LOGGER.info("WebElement located with locator: " + locator);
            return webElement;
        } catch (Throwable e) {
            LOGGER.error("Failed to locate WebElement with locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Returns a list of WebElement located by the given By object.
     *
     * @param locator the By object used to locate the elements
     * @return the list of located WebElement
     * @throws NoSuchElementException if no element is found
     */
    public List<WebElement> getWebElements(By locator) {
        try {
            List<WebElement> webElements = driver.findElements(locator);
            LOGGER.info("Found " + webElements.size() + " WebElements with locator: " + locator);
            return webElements;
        } catch (Throwable e) {
            LOGGER.error("Failed to locate WebElement with locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Wait until presence (if required) then returns WebElement.
     *
     * @param locator the By object used to locate the element
     * @param waitTimeInSeconds The wait time in seconds. If not specified, the default value will be used
     * @return the located WebElement
     */
    public WebElement waitUntilPresenceThenGetWebElement(By locator, int... waitTimeInSeconds) {
        try{
            WebElement webElement = getWebDriverWait(waitTimeInSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
            LOGGER.info("Waited for presence then got WebElement with locator: " + locator);
            return webElement;
        } catch (Throwable e) {
            LOGGER.error("Failed to wait for presence then get WebElement with locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Wait until presence (if required) then returns list of WebElement.
     *
     * @param locator the By object used to locate the elements
     * @param waitTimeInSeconds The wait time in seconds. If not specified, the default value will be used
     * @return the list of located WebElement
     */
    public List<WebElement> waitUntilPresenceThenGetWebElements(By locator, int... waitTimeInSeconds) {
        try{
            List<WebElement> webElements = getWebDriverWait(waitTimeInSeconds).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            LOGGER.info("Waited for presence then got list of WebElement with locator: " + locator);
            return webElements;
        } catch (Throwable e) {
            LOGGER.error("Failed to wait for presence then get list of WebElement with locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Wait until visibility (if required) then returns WebElement.
     *
     * @param locator the By object used to locate the element
     * @param waitTimeInSeconds The wait time in seconds. If not specified, the default value will be used
     * @return the located WebElement
     */
    public WebElement waitUntilVisibilityThenGetWebElement(By locator, int... waitTimeInSeconds) {
        WebElement webElement;
        try{
            webElement = getWebDriverWait(waitTimeInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
            LOGGER.info("Waited for visibility then got WebElement with locator: " + locator);
            return webElement;
        } catch (Throwable e) {
            LOGGER.error("Failed to wait for visibility then get WebElement with locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Wait until visibility (if required) then returns list of WebElement.
     *
     * @param locator the By object used to locate the elements
     * @param waitTimeInSeconds The wait time in seconds. If not specified, the default value will be used
     * @return the list of located WebElement
     */
    public List<WebElement> waitUntilVisibilityThenGetWebElements(By locator, int... waitTimeInSeconds) {
        try{
            List<WebElement> webElements = getWebDriverWait(waitTimeInSeconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            LOGGER.info("Waited for visibility then got list of WebElement with locator: " + locator);
            return webElements;
        } catch (Throwable e) {
            LOGGER.error("Failed to wait for visibility then get list of WebElement with locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Wait until visibility and enabled (if required) then returns WebElement.
     *
     * @param locator the By object used to locate the elements
     * @param waitTimeInSeconds The wait time in seconds. If not specified, the default value will be used
     * @return the located WebElement
     */
    public WebElement waitUntilVisibilityAndEnabledThenGetWebElement(By locator, int... waitTimeInSeconds) {
        WebElement webElement;
        try{
            webElement = getWebDriverWait(waitTimeInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
            LOGGER.info("Waited for visibility and enabled then got WebElement with locator: " + locator);
            return webElement;
        } catch (Throwable e) {
            LOGGER.error("Failed to wait for visibility and enabled then get WebElement with locator: " + locator, e);
            throw e;
        }
    }

    /**
     * Returns a Select WebElement located by the given By object.
     *
     * @param locator the By object used to locate the element
     * @return the located Select WebElement
     */
    public Select getSelect(By locator) {
        LOGGER.info("Get Select WebElement with locator: " + locator);
        return new Select(getWebElement(locator));
    }

    /**
     * Wait until the element located by the given By object is present then returns a Select object.
     *
     * @param locator the By object used to locate the element
     * @param waitTimeInSeconds The wait time in seconds. If not specified, the default value will be used
     * @return the located Select WebElement
     */
    public Select waitUntilPresenceThenGetSelect(By locator, int... waitTimeInSeconds) {
        WebElement webElement = waitUntilPresenceThenGetWebElement(locator, waitTimeInSeconds);
        LOGGER.info("Waited for presence then got Select WebElement with locator: " + locator);
        return new Select(webElement);
    }

    /**
     * Wait until the element located by the given By object is visible then returns a Select object.
     *
     * @param locator the By object used to locate the element
     * @param waitTimeInSeconds The wait time in seconds. If not specified, the default value will be used
     * @return the located Select WebElement
     */
    public Select waitUntilVisibilityThenGetSelect(By locator, int... waitTimeInSeconds) {
        WebElement webElement = waitUntilVisibilityThenGetWebElement(locator, waitTimeInSeconds);
        LOGGER.info("Waited for visibility then got Select WebElement with locator: " + locator);
        return new Select(webElement);
    }

    /**
     * Wait until the element located by the given By object is visible and enabled then returns a Select object.
     *
     * @param locator the By object used to locate the element
     * @param waitTimeInSeconds The wait time in seconds. If not specified, the default value will be used
     * @return the located Select WebElement
     */
    public Select waitUntilVisibilityAndEnabledThenGetSelect(By locator, int... waitTimeInSeconds) {
        WebElement webElement = waitUntilVisibilityAndEnabledThenGetWebElement(locator, waitTimeInSeconds);
        LOGGER.info("Waited for visibility and enabled then got Select WebElement with locator: " + locator);
        return new Select(webElement);
    }

    /**
     * Returns an instance of Actions class initialized with the current WebDriver instance.
     *
     * @return Actions object
     */
    public Actions getActions() {
        LOGGER.info("Initializing Actions object");
        return new Actions(driver);
    }

    /**
     * Switches to alert, and returns the Alert object.
     *
     * @return Alert object
     */
    public Alert getAlert() {
        try{
            Alert alert = driver.switchTo().alert();
            LOGGER.info("Switched to alert successfully");
            return alert;
        } catch (Throwable e) {
            LOGGER.error("Failed to switch to alert", e);
            throw e;
        }
    }

    /**
     * Waits for an alert to be present, switches to it, and returns the Alert object.
     *
     * @param waitTimeInSeconds The wait time in seconds. If not specified, the default value will be used
     * @return Alert object
     */
    public Alert waitUntilPresenceThenGetAlert(int... waitTimeInSeconds) {
        try{
            Alert alert = getWebDriverWait(waitTimeInSeconds).until(ExpectedConditions.alertIsPresent());
            LOGGER.info("Alert present and switched to successfully");
            return alert;
        } catch (Throwable e) {
            LOGGER.error("Failed to switch to alert", e);
            throw e;
        }
    }

    /**
     * Returns a TakesScreenshot object, which can be used to capture screenshots.
     *
     * @return a TakesScreenshot object
     */
    public TakesScreenshot getTakesScreenshot() {
        try{
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            LOGGER.info("TakesScreenshot created successfully");
            return takesScreenshot;
        } catch (Throwable  e) {
            LOGGER.error("Failed to create TakesScreenshot object", e);
            throw e;
        }
    }

    /**
     * Returns JavascriptExecutor with the current WebDriver instance.
     *
     * @return JavascriptExecutor
     */
    public JavascriptExecutor getJavascriptExecutor() {
        LOGGER.info("Get JavascriptExecutor");
        return (JavascriptExecutor) driver;
    }

}
