package org.justtestit.buggy.pages;

import commons.web.WebDriverManager;
import org.openqa.selenium.By;

/**
 * This class represents the Register page.
 * It contains instance variables to store locators related to the Register page.
 * It contains methods for performing actions and retrieving information related to the Register page.
 * The class uses the WebDriverManager object to interact with the browser and the By class to locate elements on the page.
 */
public class RegisterPage {

    //********** OBJECT DECLARATION / INSTANCE VARIABLE **********
    private final WebDriverManager webDriverManager;

    //********** STATIC PAGE LOCATORS **********
    private final By loginTextbox = By.id("username");
    private final By firstNameTextbox = By.name("firstName");
    private final By lastNameTextbox = By.id("lastName");
    private final By passwordTextbox = By.id("password");
    private final By confirmPasswordTextbox = By.id("confirmPassword");
    private final By registerButton = By.xpath("//button[text()='Register']");

    /**
     * Creates a new instance of RegisterPage with a specified WebDriverManager object.
     *
     * @param webDriverManager the WebDriverManager object to be used for WebDriver operations
     */
    public RegisterPage(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    //********** DYNAMIC PAGE LOCATORS **********
    private By registrationSuccessfulMessage(String notificationMessage) {
        return By.xpath("//div[contains(text(),'" + notificationMessage + "')]");
    }
    private By userAlreadyExistsErrorMessage(String errorMessage) {
        return By.xpath("//div[contains(text(),'" + errorMessage + "')]");
    }

    //********** PAGE ACTION METHODS **********

    /**
     * Opens the specified base URL concatenated with the "register" path.
     *
     * @param baseUrl The base URL to navigate to.
     */
    public void open(String baseUrl) {
        webDriverManager.getDriver().get(baseUrl + "register");
    }

    /**
     * This method registers a user by filling in the registration form fields with the given parameters, and clicking the register button.
     * @param login The login name of the user to be registered
     * @param firstName The first name of the user to be registered
     * @param lastName The last name of the user to be registered
     * @param password The password of the user to be registered
     * @param confirmPassword The confirmed password of the user to be registered
     */
    public void register(String login, String firstName, String lastName, String password, String confirmPassword) {
        webDriverManager.waitUntilVisibilityThenGetWebElement(loginTextbox).sendKeys(login);
        webDriverManager.waitUntilVisibilityThenGetWebElement(firstNameTextbox).sendKeys(firstName);
        webDriverManager.waitUntilVisibilityThenGetWebElement(lastNameTextbox).sendKeys(lastName);
        webDriverManager.waitUntilVisibilityThenGetWebElement(passwordTextbox).sendKeys(password);
        webDriverManager.waitUntilVisibilityThenGetWebElement(confirmPasswordTextbox).sendKeys(confirmPassword);
        webDriverManager.waitUntilVisibilityAndEnabledThenGetWebElement(registerButton).click();
    }

    /**
     * OThis method checks if the registration successful message is displayed on the registration page.
     *
     * @param notificationMessage The notification message to check for
     * @return Returns true if the registration successful message is displayed, false otherwise
     */
    public boolean isRegistrationSuccessfulMessageDisplayed(String notificationMessage) {
        return webDriverManager.waitUntilVisibilityThenGetWebElement(registrationSuccessfulMessage(notificationMessage)).isDisplayed();
    }

    /**
     * This method checks if the user already exists error message is displayed on the registration page.
     *
     * @param errorMessage The error message to check for
     * @return Returns true if the user already exists error message is displayed, false otherwise
     */
    public boolean isUserAlreadyExistsErrorMessageDisplayed(String errorMessage) {
        return webDriverManager.waitUntilVisibilityThenGetWebElement(userAlreadyExistsErrorMessage(errorMessage)).isDisplayed();
    }

}
