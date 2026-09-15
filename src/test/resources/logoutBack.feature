Feature: Validar logout y 3 backs con Epic sadface

  Background:
    Given I am in sauce demo web page
    When I set the user name text box with "standard_user"
    And I set the password text box with "secret_sauce"
    And I click on the login button
    Then The home pages should be displayed

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
