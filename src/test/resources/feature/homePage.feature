Feature: HomePage test

  Background:
    Given I am on the login page
    When  I login with "test-standard_user" credentials
    Then  The app should display "success_login"


  Scenario: Simple purchase flow
    When I open product catalog
    Then The catalog product should not be empty
    And  I select product from item
    Then I expect product to have name and price
    And  I add product to cart
    Then I expect adding successfully the product
    And  I remove product from cart
    Then I expect product to be removed from cart
