Feature: MDH-eTag PROD Smoke Suite

  @smoke
  Scenario: MDH Login Functionality
    Given User launch chrome browser
    And User clicks MDH login button
    When User enters valid sgid "SGID"
    Then User clicks Next button
    When User enters valid password "PSW"
    Then User clicks Verify button
    #Then User should able to view the "MDH" dashboard
    #And User should able to view the "ETAG" dashboard