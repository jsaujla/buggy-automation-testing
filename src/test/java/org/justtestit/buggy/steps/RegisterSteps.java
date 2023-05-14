package org.justtestit.buggy.steps;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

/**
 * This class contains the implementation of register step definitions that correspond to feature files.
 * It extends the PageInitializer class to access the page objects and driver.
 */
public class RegisterSteps extends PageInitializer {

    //********** LOGGER OBJECT DECLARATION/INITIALIZATION **********
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterSteps.class);

    private String dynamicLoginUserName;
    private String loginUserPassword;

    /**
     * Constructor to initialize the RegisterSteps class.
     *
     * @param dependencyContainer An instance of the DependencyContainer class
     */
    public RegisterSteps(DependencyContainer dependencyContainer) {
        super(dependencyContainer);
    }

    //********** STEP DEFINITION METHODS **********

    @Given("I am at Buggy register page")
    public void i_am_at_Buggy_register_page() {
        LOGGER.info("Given I am at Buggy register page");
        getRegisterPage().open(propertiesManager.getProperty("base.url"));
    }

    @When("I register with valid data")
    public void i_register_with_valid_data(DataTable dataTable) {
        LOGGER.info("When I register with valid data");
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        Faker faker = new Faker();
        dynamicLoginUserName = faker.name().username();
        loginUserPassword = rows.get(0).get("password");
        getRegisterPage().register(
                dynamicLoginUserName,
                rows.get(0).get("firstName"),
                rows.get(0).get("lastName"),
                loginUserPassword,
                rows.get(0).get("confirmPassword"));
    }

    @Then("I should see the notification message: {string}")
    public void i_should_see_the_notification_message(String notificationMessage) {
        LOGGER.info("Then I should see the notification message: {string}");
        Assert.assertTrue(getRegisterPage().isRegistrationSuccessfulMessageDisplayed(notificationMessage), "Notification message '" + notificationMessage + "' not displayed");
    }

    @When("I login with registered user")
    public void i_login_with_registered_user() {
        LOGGER.info("When I login with registered user");
        getHomePage().login(dynamicLoginUserName, loginUserPassword);
    }

    @Then("I should see the valid user info under user profile page")
    public void i_should_see_the_valid_user_info_under_user_profile_page(DataTable dataTable) {
        LOGGER.info("Then I should see the valid user info under user profile page");
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        getDashboardPage().clickProfileLink();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(getProfilePage().getLoginValue(), dynamicLoginUserName);
        softAssert.assertEquals(getProfilePage().getFirstNameValue(), rows.get(0).get("firstName"));
        softAssert.assertEquals(getProfilePage().getLastNameValue(), rows.get(0).get("lastName"));
        softAssert.assertAll();
    }

    @When("I try to register with existing user")
    public void i_try_to_register_with_existing_user(DataTable dataTable) {
        LOGGER.info("When I try to register with existing user");
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        getRegisterPage().register(
                rows.get(0).get("login"),
                rows.get(0).get("firstName"),
                rows.get(0).get("lastName"),
                rows.get(0).get("password"),
                rows.get(0).get("confirmPassword"));
    }

    @Then("I should see the error message: {string}")
        public void i_should_see_the_error_message(String errorMessage) {
        LOGGER.info("Then I should see the error message: {string}");
        Assert.assertTrue(getRegisterPage().isUserAlreadyExistsErrorMessageDisplayed(errorMessage), "Error message '" + errorMessage + "' not displayed");
    }

}
