Feature: Checkout a product
  Background:
    Given I am in sauce demo web page
    When I set the user name text box with "standard_user"
    And I set the password text box with "secret_sauce"
    Then I click on the login button

    Scenario: User can checkout a purchase
      Given The home pages should be displayed
      When I add the product "Sauce Labs Backpack" to the cart
      Then I verify that the cart icon displays "1"
      When I click on the cart icon
      And I click on the checkout button
      And I fill the checkout information with
        |Jorge|Perez|12345|
      And I click on the continue button
      When I click on finish button
      Then A message that says "Thank you for your order!" should be displayed

