Feature: Create a new card and add parameters
  As a Trello user
  I want to create a new card on a list

  Background:
    Given The user is member of the board "API Challenge 19"
    And The board has the list "TODO"

  Scenario: Create a new card on a list in a Board
    Given The user wants to create a new card "For Testing" on the list "TODO" on the board "API Challenge 19"
    When The user sends a petition to create a card on the list in the board
    Then The Trello API should create a card on the list in the board

  Scenario: Add a member to the card
    Given The board "API Challenge 19" has a list "TODO" and the list has a card "For Testing"
    And The user wants to add a member to the card "For Testing" on the list "TODO" on the board "API Challenge 19"
    When The user sends a petition to add a member to the card
    Then The Trello API should add a member to the card

  Scenario: Add a comment to the card
    Given The board "API Challenge 19" has a list "TODO" and the list has a card "For Testing"
    And The user wants to add the comment "No comments" to the card "For Testing" on the list "TODO" on the board "API Challenge 19"
    When The user sends a petition to add a comment to the card
    Then The Trello API should add a comment to the card

  Scenario Outline: Move a card from list A to list B
    Given The board "API Challenge 19" has a list "<LIST_A>" and the list has a card "For Testing"
    And The board has the list "<LIST_B>"
    And The user wants to move the card "For Testing" from list "<LIST_A>" to "<LIST_B>" on the board "API Challenge 19"
    When The user sends a petition to move the card
    Then The Trello API should move the card

    Examples:
    |LIST_A     |LIST_B     |
    |TODO       |IN PROGRESS|
    |IN PROGRESS|DONE       |