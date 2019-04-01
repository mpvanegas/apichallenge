Feature: Get the available boards of a user
  As a Trello user
  I want to view the boards I am in
  So I can have their information

  Scenario: Get boards of a user
    Given The user wants to view the boards he is in
    When The user sends a petition to view the boards
    Then The Trello API should show the information of the boards