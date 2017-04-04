#language: en
Feature: When an event is sent in Kafka, part of the interface should be updated
  Scenario: a proposal event is sent and the interface is updated
    When proposals are sent:
      | author | category | votes | minimalSupport | created |
      | "Test author" | "Test category" | 50 | 20 | Mar 31, 2017|
    Then the database has to be updated with a new proposal
    And the interface has to be updated, including the list of most voted

  Scenario: a comment event is sent and the interface is updated
    When a comment event is sent
      | author | body | created |
      | "Test author" | "Test body. This is a nice proposal, if I do say so myself" | Mar 31, 2017 |
    Then the database has to be updated with a new comment
    And the interface has to be updated, at least the comment count