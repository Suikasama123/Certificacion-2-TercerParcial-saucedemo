Feature: Validar reset App State sin refresh en carrito

  Background:
    Given I am in sauce demo web page
    When I set the user name text box with "standard_user"
    And I set the password text box with "secret_sauce"
    And I click on the login button
    Then The home pages should be displayed

  @resetCart
  Scenario: Validar reset App State sin refresh en carrito
    When I add following products to cart
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    Then cart badge should be "2"
    When I click on the cart icon
    Then YourCart should have 2 items
    When I do reset app state from cart
    Then cart badge should be empty
    And cart should still have 2 items without refresh
    When I refresh page
    Then cart should be empty
