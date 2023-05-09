package commons.web;

/**
 * This class contains static objects/variables for web driver related constants such as the paths for different browser driver executables.
 */
public final class WebDriverConstants {

    // Prevent instantiation of this class
    private WebDriverConstants() {}

    protected static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/browser-drivers/chromedriver.exe";
    protected static final String FIREFOX_DRIVER_PATH = System.getProperty("user.dir") + "/browser-drivers/geckodriver.exe";
    protected static final String EDGE_DRIVER_PATH = System.getProperty("user.dir") + "/browser-drivers/MicrosoftWebDriver.exe";
    protected static final String IE_DRIVER_PATH = System.getProperty("user.dir") + "/browser-drivers/IEDriverServer.exe";

}
