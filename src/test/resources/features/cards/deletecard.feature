Feature: Delete a card in a list
  As a Trello user
  I want to delete a card on a list

  Background:
    Given The user is member of the board "API Challenge 19"

Scenario: Delete a card
  Given The user wants to delete the card "For Testing" in the list "DONE" on the board "API Challenge 19"
  When The user sends a petition to delete the card
  Then The Trello API should delete the card