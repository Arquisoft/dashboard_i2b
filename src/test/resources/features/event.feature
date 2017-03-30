#language: en
Feature: Send an event in Kafka and receive it properly
  Event has to be received in our side
  Event data has to be contained in the database

  Scenario: proposal event is received
    Given a list of proposals:
    | author | category | votes | minimalSupport | created |
    | "Test author" | "Test category" | 50 | 20 | Mar 31, 2017|
    Then database contains at least one Proposal