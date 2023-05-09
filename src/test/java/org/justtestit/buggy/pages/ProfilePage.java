package org.justtestit.buggy.pages;

import commons.web.WebDriverManager;
import org.openqa.selenium.By;

/**
 * This class represents the Profile page.
 * It contains instance variables to store locators related to the Profile page.
 * It contains methods for performing actions and retrieving information related to the Profile page.
 * The class uses the WebDriverManager object to interact with the browser and the By class to locate elements on the page.
 */
public class ProfilePage {

    //********** OBJECT DECLARATION / INSTANCE VARIABLE **********
    private final WebDriverManager webDriverManager;

    //********** STATIC PAGE LOCATORS **********
    private final By loginTextbox = By.id("username");
    private final By firstNameTextbox = By.name("firstName");
    private final By lastNameTextbox = By.id("lastName");

    /**
     * Creates a new instance of ProfilePage with a specified WebDriverManager object.
     *
     * @param webDriverManager the WebDriverManager object to be used for WebDriver operations
     */
    public ProfilePage(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    //********** PAGE ACTION METHODS **********

    /**
     * Retrieves the value of the "login" text box.
     *
     * @return The value of the "login" text box
     */
    public String getLoginValue() {
        return webDriverManager.waitUntilVisibilityThenGetWebElement(loginTextbox).getAttribute("value");
    }

    /**
     * Retrieves the value of the "first name" text box.
     *
     * @return The value of the "first name" text box
     */
    public String getFirstNameValue() {
        return webDriverManager.waitUntilVisibilityThenGetWebElement(firstNameTextbox).getAttribute("value");
    }

    /**
     * Retrieves the value of the "last name" text box.
     *
     * @return The value of the "last name" text box
     */
    public String getLastNameValue() {
        return webDriverManager.waitUntilVisibilityThenGetWebElement(lastNameTextbox).getAttribute("value");
    }

}
