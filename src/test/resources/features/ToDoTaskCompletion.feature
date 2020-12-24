Feature: Todo task

  Scenario: Verify that all the users of City Grofers complete more than half of their to-do tasks
    Given User belongs to the city "GROFERS"
    And User has the Todo tasks
    Then User "Completed" task percentage should be "greater than" "50"%