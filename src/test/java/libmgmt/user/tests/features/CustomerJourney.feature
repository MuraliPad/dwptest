Feature: As an user I need to login to library mgmt system and borrow available book

  Background:
    Given I could login to library management successfully

  Scenario: Borrow and return an available book
    And I could able to list all the books
    Then I choose any book randomly which is available
    And I could able to borrow successfully
    Then I could able to return successfully
    And I could able to view borrowing history

    Scenario: As an library user I wanted to browse books
      And I wanted to search for book "Llama Llama Red Pajama"
      And I wanted to search for available books by author "Watty Piper"
      And I wanted to search availability of the "The Pigeon Wants a Puppy!"