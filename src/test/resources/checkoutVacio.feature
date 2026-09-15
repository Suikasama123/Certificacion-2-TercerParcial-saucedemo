Feature: Checkout sin productos en carrito

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
