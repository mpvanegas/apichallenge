Feature: Get the information of a List
  As a Trello user
  I want to get the information of a List on a Board

  Scenario: Get the Lists on the Board "API Challenge 19"
    Given The user wants to see the Lists on the "API Challenge 19"
    When The user sends a petition to get the Lists on a Board
    Then The Trello API should response with the list of the Lists on o Board