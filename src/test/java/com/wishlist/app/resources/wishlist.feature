Feature: Wishlist management

  Scenario: Add a product to wishlist
    Given a product with id "prod1" and a client with id "cli1"
    When I send a POST request to "/wishlist" with the product and client ids
    Then the response status should be 201 CREATED

  Scenario: Remove a product from wishlist
    Given a product with id "prod1" in the wishlist of a client with id "cli1"
    When I send a DELETE request to "/wishlist/cli1/prod1"
    Then the response status should be 204 NO CONTENT

  Scenario: Get a wishlist of a client
    Given a client with id "cli1" with a wishlist
    When I send a GET request to "/wishlist/cli1"
    Then the response status should be 200 OK

  Scenario: Check if a product is in the wishlist of a client
    Given a product with id "prod1" in the wishlist of a client with id "cli1"
    When I send a GET request to "/wishlist/cli1/prod1"
    Then the response status should be 200 OK and body should be true
