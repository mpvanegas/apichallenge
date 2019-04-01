Feature: Create a new list in a board
  As a Trello user
  I want to create a list on a board

  Scenario: Create a new list on a Board
    Given The user is member of the board "API Challenge 19"
    And The board has the list "TODO"
    And The board has the list "IN PROGRESS"
    And The user wants to create a new list "QA" between the list "IN PROGRESS" and the list "DONE" in the board "API Challenge 19"
    When The user sends a petition to create a list on the board
    Then The Trello API should create a list on the board