Feature: Shopping Cart Functionality

  @Success
  Scenario: Login and add items to the shopping cart
    Given I open the "https://www.demoblaze.com/index.html" homepage
    When I log in with username "userxxx" and password "password123"
    And I add the following items to the cart:
      | Category | Product Name      |
      | Phones   | Samsung galaxy s6 |
    And I confirm the purchase
    Then I should see the confirmation message "Thank you for your purchase!"

  @Error
  Scenario: Login and add items to the shopping cart
    Given I open the "https://www.demoblaze.com/index.html" homepage
    When I log in with username "userxxx" and password "password123"
    And I add the following items to the cart:
      | Category | Product Name      |
      | Phones   | Samsung galaxy s6 |
    Then I do not confirm the purchase "Please fill out Name and Creditcard."


