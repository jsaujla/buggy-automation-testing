package commons.web;

import commons.properties.PropertiesManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The WebDriverInitializer class is responsible for initializing a WebDriver instance based on the browser specified
 * in the configuration properties file. It uses the PropertiesManager class to retrieve configuration properties.
 *
 * The class provides a public method initializeWebDriver() to launch/start the web browser window and the WebDriver
 * session. The method returns the WebDriver instance created.
 *
 * @author Jaspal Aujla
 */
public class WebDriverInitializer {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverManager.class);

    //********** OBJECT DECLARATION / INSTANCE VARIABLE **********
    private final PropertiesManager propertiesManager;
    private WebDriver driver;

    /**
     * Constructs a new WebDriverInitializer with the specified properties manager.
     *
     * @param propertiesManager the properties manager to use for configuring the WebDriver
     */
    protected WebDriverInitializer(PropertiesManager propertiesManager) {
        LOGGER.info("Constructing WebDriverInitializer with the specified properties manager");
        this.propertiesManager = propertiesManager;
    }

    /**
     * Launch/start web browser window and WebDriver session.
     *
     * @return The WebDriver instance created
     */
    protected WebDriver initializeWebDriver() {
        LOGGER.info("Initializing WebDriver");
        String browserNameInConfig = propertiesManager.getProperty("web.browser.name").toLowerCase();
        String browserName = System.getProperty("browser.name", browserNameInConfig);

        String headlessInConfig = propertiesManager.getProperty("headless");
        String headless = System.getProperty("headless", headlessInConfig);

        switch(browserName) {
            case "chrome":
                initializeChromeDriver(headless);
                break;
            case "firefox":
                initializeFirefoxDriver(headless);
                break;
            case "edge":
                initializeEdgeDriver(headless);
                break;
            case "ie":
            case "internet explorer":
                initializeIeDriver();
                break;
            case "safari":
                initializeSafariDriver();
                break;
            default:
                LOGGER.error("Value of 'web.browser' in 'config properties' file should be: chrome, firefox, edge, ie or safari");
                throw new IllegalArgumentException("Value of 'web.browser' in 'config properties' file should be: chrome, firefox, edge, ie or safari");
        }
        LOGGER.info("Web browser '" + browserName + "' launched successfully");
        return driver;
    }

    /**
     * Initializes the ChromeDriver with WebDriverManager or with the local executable.
     *
     * @param headless Whether to run Chrome in headless mode (true) or not (false)
     */
    private void initializeChromeDriver(String headless) {
        if(propertiesManager.getPropertyAsBoolean("web.driver.manager")) {
            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        } else {
            if(System.getProperty("webdriver.chrome.driver") == null) {
                System.setProperty("webdriver.chrome.driver", WebDriverConstants.CHROME_DRIVER_PATH);
            }
        }
        try {
            ChromeOptions options = new ChromeOptions();
            if ("true".equals(headless)) {
                options.addArguments("--headless=new");
            }
            driver = new ChromeDriver(options);
        } catch (Throwable e) {
            LOGGER.error("Failed to initialize ChromeDriver", e);
            throw e;
        }
    }

    /**
     * Initializes the FirefoxDriver with WebDriverManager or with the local executable.
     *
     * @param headless Whether to run Firefox in headless mode (true) or not (false)
     */
    private void initializeFirefoxDriver(String headless) {
        if(propertiesManager.getPropertyAsBoolean("web.driver.manager")) {
            io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
        } else {
            if(System.getProperty("webdriver.gecko.driver") == null) {
                System.setProperty("webdriver.gecko.driver", WebDriverConstants.FIREFOX_DRIVER_PATH);
            }
        }
        try {
            FirefoxOptions options = new FirefoxOptions();
            if ("true".equals(headless)) {
                options.addArguments("-headless");
            }
            driver = new FirefoxDriver(options);
        } catch (Throwable e) {
            LOGGER.error("Failed to initialize FirefoxDriver", e);
            throw e;
        }
    }

    /**
     * Initializes the EdgeDriver with WebDriverManager or with the local executable.
     *
     * @param headless Whether to run Edge in headless mode (true) or not (false)
     */
    private void initializeEdgeDriver(String headless) {
        if(propertiesManager.getPropertyAsBoolean("web.driver.manager")) {
            io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
        } else {
            if(System.getProperty("webdriver.edge.driver") == null) {
                System.setProperty("webdriver.edge.driver", WebDriverConstants.EDGE_DRIVER_PATH);
            }
        }
        try {
            EdgeOptions options = new EdgeOptions();
            if ("true".equals(headless)) {
                options.addArguments("--headless=new");
            }
            driver = new EdgeDriver(options);
        } catch (Throwable e) {
            LOGGER.error("Failed to initialize EdgeDriver", e);
            throw e;
        }
    }

    /**
     * Initializes the InternetExplorerDriver with WebDriverManager or with the local executable.
     */
    private void initializeIeDriver() {
        if(propertiesManager.getPropertyAsBoolean("web.driver.manager")) {
            io.github.bonigarcia.wdm.WebDriverManager.iedriver().setup();
        } else {
            if(System.getProperty("webdriver.ie.driver") == null) {
                System.setProperty("webdriver.ie.driver", WebDriverConstants.IE_DRIVER_PATH);
            }
        }
        try {
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            driver = new InternetExplorerDriver(options);
        } catch (Throwable e) {
            LOGGER.error("Failed to initialize InternetExplorerDriver", e);
            throw e;
        }
    }

    /**
     * Initializes the SafariDriver with WebDriverManager or with the local executable.
     */
    private void initializeSafariDriver() {
        if(propertiesManager.getPropertyAsBoolean("web.driver.manager")) {
            io.github.bonigarcia.wdm.WebDriverManager.safaridriver().setup();
        }
        try {
            driver = new SafariDriver();
        } catch (Throwable e) {
            LOGGER.error("Failed to initialize SafariDriver", e);
            throw e;
        }
    }

}
