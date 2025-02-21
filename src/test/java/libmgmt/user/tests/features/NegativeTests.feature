Feature: As an tester I wanted to perform some negative scenarios

  Background:
    Given I could login to library management successfully

  Scenario: Borrow book which is not available
    And I could able to list all the books
    When I select book which is not available
    Then system should handle properly
