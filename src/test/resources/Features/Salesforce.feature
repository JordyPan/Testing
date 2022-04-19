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

  @JordyBookStore
  Scenario Outline: Multiplication
    Given User logs into Salesforce APP
    And User is at "Jordy BookSotre" App
    When User enters "<number1>" and "<number2>" for Multiplication

    Examples:
      | number1 | number2 |
      |   87    |   23    |
      |   2    |   2    |

  @JordyBookStore @BookTab
  Scenario Outline: Add a new book
    Given User logs into Salesforce APP
    And User is at "Jordy BookSotre" App
    When User clicks on book tab
    And User fills in name "<BookName>" and type "<Type>" and Publish Date of today
    Then User saves the new Book and checks whether new book created

    Examples:
      | BookName | Type |
      | Jordy's Book | Science |







