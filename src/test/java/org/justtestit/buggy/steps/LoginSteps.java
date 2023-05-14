package org.justtestit.buggy.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

/**
 * This class contains the implementation of login step definitions that correspond to feature files.
 * It extends the PageInitializer class to access the page objects and driver.
 */
public class LoginSteps extends PageInitializer {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSteps.class);

    /**
     * Constructor to initialize the LoginSteps class.
     *
     * @param dependencyContainer An instance of the DependencyContainer class
     */
    public LoginSteps(DependencyContainer dependencyContainer) {
        super(dependencyContainer);
    }

    //********** STEP DEFINITION METHODS **********

    @Given("I am at Buggy login page")
    public void i_am_at_Buggy_login_page() {
        LOGGER.info("Given I am at Buggy login page");
        getHomePage().open(propertiesManager.getProperty("base.url"));
    }

    @When("I login Buggy with user: {string} and password: {string}")
        public void i_login_Buggy_with_user_and_password(String username, String password) {
        LOGGER.info("When I login Buggy with user: {string} and password: {string}");
        getHomePage().login(username, password);
    }

    @Then("I should reach to user dashboard")
    public void i_should_reach_to_user_dashboard() {
        LOGGER.info("Then I should reach to user dashboard");
        // Not adding any step here because of website limitation. There is no change in application URL and page title.
    }

    @Then("I should see the relevant user first name: {string}")
    public void i_should_see_the_relevant_user_first_name(String firstName) {
        LOGGER.info("Then I should see the relevant user first name: {string}");
        Assert.assertTrue(getDashboardPage().isUserFirstNameDisplayed(firstName), "User first name '" + firstName + "' not displayed");
    }

    @Then("I should see error message: {string}")
    public void i_should_see_error_message(String errorMessage) {
        LOGGER.info("Then I should see error message: {string}");
        if(errorMessage.equals("Please fill out this field.")) {
            //TODO Implementation to verify error message visibility
            // Currently, not implemented code to verify error message because it can't be found in the DOM
            // For the time being, relying on a check 'i_am_still_on_Home_page' in this case

            // Added 1 second wait to complete the navigation. If any case user moved to new page (in negative case)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Assert.assertTrue(getHomePage().isInvalidLoginErrorMessageDisplayed(errorMessage), "Error message '" + errorMessage + "' not displayed");
        }
    }

    @Then("I am still on Home page")
    public void i_am_still_on_Home_page() {
        LOGGER.info("Then I am still on Home page");
        Assert.assertTrue(getHomePage().isLoginButtonDisplayed(), "Login button not displayed");
    }

    @When("I click on Logout")
    public void i_click_on_Logout() {
        LOGGER.info("When I click on Logout");
        getDashboardPage().clickLogout();
    }

    @Then("I should logged out successfully")
    public void i_should_logged_out_successfully() {
        LOGGER.info("Then I should logged out successfully");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(getHomePage().isLoginButtonDisplayed(), "Login button not displayed");
        softAssert.assertTrue(getDashboardPage().isLogoutLinkInvisible(), "Logout link is visible");
        softAssert.assertAll();
    }

}
