package org.justtestit.buggy.steps;

import commons.properties.PropertiesManager;
import commons.web.WebDriverManager;

/**
 * This class is used to store shared object instances and make them available to Hooks, PageInitializer and Steps classes.
 */
public class DependencyContainer {

    /**
     * The instance of PropertiesManager class used to access and manage test configuration properties.
     */
    protected PropertiesManager propertiesManager;

    /**
     * The instance of WebDriverManager class used to manage WebDriver instance and its configuration.
     */
    protected WebDriverManager webDriverManager;

}
