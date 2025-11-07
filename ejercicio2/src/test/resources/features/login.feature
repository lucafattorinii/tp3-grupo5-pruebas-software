@web
Feature: SauceDemo Login
  As a SauceDemo user
  I want to log in to the application
  So that I can access the products

  @successful-login
  Scenario: Successful login with valid credentials
    Given I am on the SauceDemo login page
    When I enter username "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should be redirected to the products page
    And I should see the title "Products"

  @failed-login
  Scenario: Failed login with invalid credentials
    Given I am on the SauceDemo login page
    When I enter username "invalid_user" and password "invalid_password"
    And I click the login button
    Then I should see an error message
