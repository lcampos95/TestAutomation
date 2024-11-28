Feature: Shopping Cart Functionality

  Scenario: Login and add items to the shopping cart
    Given I open the "https://www.demoblaze.com/index.html" homepage
    When I log in with username "userxxx" and password "password123"
    And I add the following items to the cart:
      | Category | Product Name      |
      | Phones   | Samsung galaxy s6 |
    Then I should see results containing "Playwright"
