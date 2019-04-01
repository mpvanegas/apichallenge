Feature: Get the information of a user
  As a Trello user
  I want to get the information of my user

  Scenario: Get the information of a user
    Given The user wants their information
    When The user sends a petition to get their information
    Then The Trello API should response with the information of the user