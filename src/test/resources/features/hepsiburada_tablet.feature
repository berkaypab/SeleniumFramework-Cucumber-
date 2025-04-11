Feature: Hepsiburada Tablet Purchase

  As a customer of Hepsiburada website,
  I should be able to browse tablets, filter them, and add the most expensive one to cart,
  So that I can purchase it

  @bvt @sanity
  Scenario: Add the most expensive Apple tablet (13.3 inch) to cart
    Given I navigate to Hepsiburada homepage
    When I navigate to AllCategories and Electronic category
    And I filter by brand Apple and screen size 13,3 inç
    And I select the most expensive product from the results
    And I click on Add to Cart button on the product details page
    Then I should see the product is added to the cart
    And The price in the cart should match the price on the product details page


  @bvt @sanity
  Scenario: Test for parallel execution
    Given I navigate to Hepsiburada homepage

  @sanity @bvt
  Scenario: Test for parallel execution
    Given I navigate to Hepsiburada homepage

  @sanity @bvt
  Scenario: Test for parallel execution
    Given I navigate to Hepsiburada homepage
