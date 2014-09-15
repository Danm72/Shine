Feature: Navigate feature

  Scenario: When the screen loads I can navigate tabs
    When I press "Barcelona"
    Then I see "Monday"

  Scenario: When the screen loads I can click menu items
      When I press "Options"
      Then I see "Refresh"

    Scenario: When the screen loads I can navigate tabs
      When I press "Dublin"
      Then I see "Tuesday"
