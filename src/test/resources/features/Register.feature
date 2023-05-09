@regression
Feature: User Registration

  Background:
    Given I am at Buggy register page

  @smoke
  Scenario Outline: Verify valid registration
    When I register with valid data
      | login   | firstName   | lastName   | password   | confirmPassword   |
      | <login> | <firstName> | <lastName> | <password> | <confirmPassword> |
    Then I should see the notification message: "Registration is successful"
    When I login with registered user
    And I should see the relevant user first name: "<firstName>"
    And I should see the valid user info under user profile page
      | login   | firstName   | lastName   |
      | <login> | <firstName> | <lastName> |
      Examples:
      | login   | firstName | lastName | password   | confirmPassword |
      | DYNAMIC | John      | Smith    | Password1! | Password1!      |

  Scenario Outline: Verify registration with existing user
    When I try to register with existing user
      | login   | firstName   | lastName   | password   | confirmPassword   |
      | <login> | <firstName> | <lastName> | <password> | <confirmPassword> |
    Then I should see the error message: "User already exists"
    Examples:
      | login     | firstName | lastName | password   | confirmPassword |
      | test.user | Ricky     | Ponting  | Password1! | Password1!      |
      | test.user | Ricky     | NewLName | Password1! | Password1!      |
      | test.user | NewFName  | Ponting  | Password1! | Password1!      |
      | test.user | NewFName  | NewLName | Password1! | Password1!      |
      | test.user | Ricky     | Ponting  | NewPass1!  | NewPass1!       |
      | test.user | NewFName  | NewLName | NewPass1!  | NewPass1!       |
