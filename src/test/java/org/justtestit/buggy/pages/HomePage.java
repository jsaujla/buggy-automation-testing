package org.justtestit.buggy.pages;

import commons.web.WebDriverManager;
import org.openqa.selenium.By;

/**
 * This class represents the Home page.
 * It contains instance variables to store locators related to the Home page.
 * It contains methods for performing actions and retrieving information related to the Home page.
 * The class uses the WebDriverManager object to interact with the browser and the By class to locate elements on the page.
 */
public class HomePage {

    //********** OBJECT DECLARATION / INSTANCE VARIABLE **********
    private final WebDriverManager webDriverManager;

    //********** STATIC PAGE LOCATORS **********
    private final By loginTextbox = By.name("login");
    private final By passwordTextbox = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");

    /**
     * Creates a new instance of HomePage with a specified WebDriverManager object.
     *
     * @param webDriverManager the WebDriverManager object to be used for WebDriver operations
     */
    public HomePage(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    //********** DYNAMIC PAGE LOCATORS **********
    private By invalidLoginErrorMessage(String errorMessage) {
        return By.xpath("//*[contains(text(),'" + errorMessage + "')]");
    }

    //********** PAGE ACTION METHODS **********

    /**
     * Opens the specified base URL in the web driver instance.
     *
     * @param baseUrl The URL to be opened
     */
    public void open(String baseUrl) {
        webDriverManager.getDriver().get(baseUrl);
    }

    /**
     * Logs in to the application with the given username and password.
     *
     * @param username The username to be used for login
     * @param password The password to be used for login
     */
    public void login(String username, String password) {
        webDriverManager.waitUntilVisibilityThenGetWebElement(loginTextbox).sendKeys(username);
        webDriverManager.waitUntilVisibilityThenGetWebElement(passwordTextbox).sendKeys(password);
        webDriverManager.waitUntilVisibilityAndEnabledThenGetWebElement(loginButton).click();
    }

    /**
     * Checks whether the specified invalid login error message is displayed.
     *
     * @param errorMessage The invalid login error message to be checked
     * @return true if the error message is displayed, false otherwise
     */
    public boolean isInvalidLoginErrorMessageDisplayed(String errorMessage) {
        return webDriverManager.waitUntilVisibilityThenGetWebElement(invalidLoginErrorMessage(errorMessage)).isDisplayed();
    }

    /**
     * Checks whether the login button is displayed.
     *
     * @return true if the login button is displayed, false otherwise
     */
    public boolean isLoginButtonDisplayed() {
        return webDriverManager.waitUntilVisibilityThenGetWebElement(loginButton).isDisplayed();
    }

}
