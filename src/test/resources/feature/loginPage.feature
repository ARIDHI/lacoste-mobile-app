@lOGINPAGE
Feature: Login tests

  Background:
    Given I am on the login page

  Scenario Outline: Login with different credentials
    When I login with "<username>" credentials
    Then The app should display "<expectedResult>"

    Examples:
      | username             | expectedResult      |                      |
      | test-standard_user   | success_login       | #  valid credentials |
      | test-locked_out_user | invalid_user        | #  locked out user   |
      | test-problem_user    | invalid_credentials | #  problem user      |


  Scenario: Logout out from application
    When I login with "test-standard_user" credentials
    And  I logout from application
    Then The app should display "success_logout"

