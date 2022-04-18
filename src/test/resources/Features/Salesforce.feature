@Salesforce
Feature: Salesforce activities

  @login
  Scenario: User logs into Salesforce
    Given User Lands on Salesforce Main page
    When User Clicks on Login button
    And User enters username and password
    Then user should be logged in

   @LoadApp
  Scenario Outline: User selects an app
    Given User logs into Salesforce APP
    When User selects app "<APP>"
    Then APP should be loaded

    Examples:
      |      APP         |
      | Jordy BookSotre |






