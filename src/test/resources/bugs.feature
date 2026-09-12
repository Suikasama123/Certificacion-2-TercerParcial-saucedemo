Feature: SauceDemo Bugs Automation

  Background:
    Given I am in sauce demo web page
    When I set the user name text box with "standard_user"
    And I set the password text box with "secret_sauce"
    And I click on the login button
    Then The home pages should be displayed

  @checkoutVacio
  Scenario: Checkout sin productos en carrito
    When I click on the cart icon
    Then cart should be empty
    And cart badge should be empty
    When I click on the checkout button
    Then user should be on checkout step one page
    And checkout form fields should be visible

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

  @logoutBack
  Scenario: Validar logout y 3 backs con Epic sadface
    When I add product "Sauce Labs Backpack" to cart via home
    And I click on the cart icon
    And I click on the checkout button
    And I open menu and logout
    Then user should see errors after 3 backs
      | Epic sadface: You can only access '/checkout-step-one.html' when you are logged in. |
      | Epic sadface: You can only access '/cart.html' when you are logged in.             |
      | Epic sadface: You can only access '/inventory.html' when you are logged in.         |

  @checkoutDatos
  Scenario Outline: Checkout con datos aleatorios sin limite y sin validacion postal
    When I add product "Sauce Labs Backpack" to cart via home
    And I click on the cart icon
    And I click on the checkout button
    And I fill checkout form with firstName "<firstName>", lastName "<lastName>" and postal "<postal>"
    Then form should keep values without truncation and postal contains "<postalCheck>"
    When I click on continue button in checkout
    Then user should be on checkout overview and no error is shown

    Examples:
      | firstName      | lastName | postal                       | postalCheck |
      | AAAAAAAAAAAAAA | BBBBBBBB | XYZ-!@#_ABC_999999999999     | XYZ         |
