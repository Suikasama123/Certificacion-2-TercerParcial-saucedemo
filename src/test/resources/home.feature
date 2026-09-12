Feature: Home Page tests

  Background: User login into sauce demo
    Given I am in sauce demo web page
    When I set the user name text box with "standard_user"
    And I set the password text box with "secret_sauce"
    Then I click on the login button


    Scenario Outline: Verify that all the products are displayed in the home page
      When The home pages should be displayed
      Then The product "<product>" should be displayed
      Examples:
       |product                 |
       |Sauce Labs Backpack     |
       |Sauce Labs Bike Light   |
       |Sauce Labs Bolt T-Shirt |
       |Sauce Labs Fleece Jacket|

    Scenario Outline: Verify that products can be added/removed to the cart
      When The home pages should be displayed
      And I add the product "<product>" to the cart
      Then I verify that the cart icon displays "1"
      And I remove the product "<product>" from the cart
      Then I verify that the cart icon displays ""
      Examples:
      |product|
      |Sauce Labs Backpack     |
      |Sauce Labs Bike Light   |
      |Sauce Labs Bolt T-Shirt |
      |Sauce Labs Fleece Jacket|



