@HOMEPAGE
Feature: HomePage test

  Background:
    Given I am on the login page
    When  I login with "test-standard_user" credentials
    Then  The app should display "success_login"


  Scenario: Simple purchase flow
    When I open product catalog
    And  I add product to cart
    Then I expect adding successfully the product
    And  I remove product from cart

