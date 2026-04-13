Feature: Dashboard Validation

  @smoke @android @monefy @dashboard
  Scenario: Verify default dashboard
    Given the user is on the offer screen
    When the user closes the offer
    Then the dashboard is displayed