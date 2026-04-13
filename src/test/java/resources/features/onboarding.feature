Feature: Onboarding

  @smoke @android @monefy @onboarding
  Scenario: View onboarding slides
    Given the user is on the onboarding screen
    When the user views the slides
    Then the user views the offer page