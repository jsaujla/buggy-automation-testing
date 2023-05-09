package org.justtestit.buggy.pages;

import commons.web.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class represents the Dashboard page.
 * It contains instance variables to store locators related to the Dashboard page.
 * It contains methods for performing actions and retrieving information related to the Dashboard page.
 * The class uses the WebDriverManager object to interact with the browser and the By class to locate elements on the page.
 */
public class DashboardPage {

    //********** OBJECT DECLARATION / INSTANCE VARIABLE **********
    private final WebDriverManager webDriverManager;

    //********** STATIC PAGE LOCATORS **********
    private final By logoutLink = By.linkText("Logout");
    private final By profileLink = By.linkText("Profile");

    /**
     * Creates a new instance of DashboardPage with a specified WebDriverManager object.
     *
     * @param webDriverManager the WebDriverManager object to be used for WebDriver operations
     */
    public DashboardPage(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
    }

    //********** DYNAMIC PAGE LOCATORS **********
    private By userFirstName(String firstName) {
        return By.xpath("//*[contains(text(),'" + firstName + "')]");
    }

    //********** PAGE ACTION METHODS **********

    /**
     * Returns whether the user's first name is displayed on the page or not.
     *
     * @param firstName The first name of the user
     * @return true if the user's first name is displayed on the page, false otherwise
     */
    public boolean isUserFirstNameDisplayed(String firstName) {
        return webDriverManager.waitUntilVisibilityThenGetWebElement(userFirstName(firstName)).isDisplayed();
    }

    /**
     * Clicks on the profile link on the page.
     */
    public void clickProfileLink() {
        webDriverManager.waitUntilVisibilityAndEnabledThenGetWebElement(profileLink).click();
    }

    /**
     * Clicks on the logout link on the page.
     */
    public void clickLogout() {
        webDriverManager.waitUntilVisibilityAndEnabledThenGetWebElement(logoutLink).click();
    }

    /**
     * Returns whether the logout link is invisible on the page or not.
     *
     * @return true if the logout link is invisible on the page, false otherwise
     */
    public boolean isLogoutLinkInvisible() {
        return webDriverManager.getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(logoutLink));
    }

}
