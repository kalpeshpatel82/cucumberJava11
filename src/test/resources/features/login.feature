Feature: As a user I would like to verify Login Functionality of given URL

  @login
  Scenario: Verify if User can login Successfully
    Given I am on home page of given URL
    When I click on Login URL
    Then I verify if I navigates to Login Page
