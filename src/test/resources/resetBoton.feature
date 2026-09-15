Feature: Validar estado botones tras reset App State

  Background:
    Given I am in sauce demo web page
    When I set the user name text box with "standard_user"
    And I set the password text box with "secret_sauce"
    And I click on the login button
    Then The home pages should be displayed

  @resetBoton
  Scenario: Validar estado botones tras reset App State
    When I add following products to cart
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    Then inventory should have 6 items
    And buttons should show "Remove" for products
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    When I remove product "Sauce Labs Bike Light" from cart via home
    Then cart badge should be "1"
    When I add product "Sauce Labs Bike Light" to cart via home
    Then cart badge should be "2"
    When I do reset app state from home
    Then cart badge should be empty
    And buttons should show "Add to cart" for products
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
