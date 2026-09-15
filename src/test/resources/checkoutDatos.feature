Feature: Checkout con datos aleatorios sin limite y sin validacion postal

  Background:
    Given I am in sauce demo web page
    When I set the user name text box with "standard_user"
    And I set the password text box with "secret_sauce"
    And I click on the login button
    Then The home pages should be displayed

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
