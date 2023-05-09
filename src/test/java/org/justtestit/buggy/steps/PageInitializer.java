package org.justtestit.buggy.steps;

import commons.properties.PropertiesManager;
import commons.web.WebDriverManager;
import org.justtestit.buggy.pages.HomePage;
import org.justtestit.buggy.pages.ProfilePage;
import org.justtestit.buggy.pages.RegisterPage;
import org.justtestit.buggy.pages.DashboardPage;

/**
 * This class is responsible for initializing page object classes and providing the page objects to step classes.
 * All page object classes should be initialized through this class.
 */
public class PageInitializer {

    //********** OBJECT DECLARATION **********
    protected final WebDriverManager webDriverManager;
    protected final PropertiesManager propertiesManager;

    private HomePage homePage;
    private DashboardPage dashboardPage;
    private RegisterPage registerPage;
    private ProfilePage profilePage;

    /**
     * Constructor to initialize the PageInitializer class.
     *
     * @param dependencyContainer An instance of the DependencyContainer class
     */
    public PageInitializer(DependencyContainer dependencyContainer) {
        this.webDriverManager = dependencyContainer.webDriverManager;
        this.propertiesManager = dependencyContainer.propertiesManager;
    }

    /**
     * Method to initialize HomePage and return the instance.
     *
     * @return An instance of the HomePage class
     */
    protected HomePage getHomePage() {
        if(homePage == null) {
            return homePage = new HomePage(webDriverManager);
        } else {
            return homePage;
        }
    }

    /**
     * Method to initialize DashboardPage and return the instance.
     *
     * @return An instance of the DashboardPage class
     */
    protected DashboardPage getDashboardPage() {
        if(dashboardPage == null) {
            return dashboardPage = new DashboardPage(webDriverManager);
        } else {
            return dashboardPage;
        }
    }

    /**
     * Method to initialize RegisterPage and return the instance.
     *
     * @return An instance of the RegisterPage class
     */
    protected RegisterPage getRegisterPage() {
        if(registerPage == null) {
            return registerPage = new RegisterPage(webDriverManager);
        } else {
            return registerPage;
        }
    }

    /**
     * Method to initialize ProfilePage and return the instance.
     *
     * @return An instance of the ProfilePage class
     */
    protected ProfilePage getProfilePage() {
        if(profilePage == null) {
            return profilePage = new ProfilePage(webDriverManager);
        } else {
            return profilePage;
        }
    }

}
