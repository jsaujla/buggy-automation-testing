@regression
Feature: User Login

  Background:
    Given I am at Buggy login page

  Scenario: Verify valid login
    When I login Buggy with user: "test.user" and password: "Password1!"
    Then I should reach to user dashboard
    And I should see the relevant user first name: "Ricky"

  @smoke
  Scenario: Verify logout
    When I login Buggy with user: "test.user" and password: "Password1!"
    Then I should reach to user dashboard
    And I should see the relevant user first name: "Ricky"
    When I click on Logout
    Then I should logged out successfully

  Scenario Outline: Verify invalid login
    When I login Buggy with user: "<login>" and password: "<password>"
    Then I should see error message: "<errorMessage>"
    And I am still on Home page
    Examples:
      | login       | password        | errorMessage                |
      | invaliduser | invalidpassword | Invalid username/password   |
      | test.user   | invalidpassword | Invalid username/password   |
      | test.user   |                 | Please fill out this field. |
      | invaliduser | Password1!      | Invalid username/password   |
      |             | Password1!      | Please fill out this field. |
      |             |                 | Please fill out this field. |
