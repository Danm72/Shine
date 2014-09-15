Feature: Navigate feature

  Scenario: When the screen loads I can go to details
    When I press "Monday"
    Then I see "Speed"

   Scenario: When the screen loads I can go to details
     When I press "Tuesday"
     Then I see "Humidity"

    Scenario: When I see the main screen and swipe
     Then /^I swipe left$/
     Then I see "Wednesday"