Feature: Enter Income

  @smoke @android @monefy @income
  Scenario Outline: Verify income amount is updated correctly
    Given the user is on the dashboard screen
    When the user clicks the add income button
    Then the new income screen is displayed
    When the user enters an income amount of "<amount>"
    Then the income amount of "<amount>" is displayed
    When the user enters an income note of "<income note>"
    And the user clicks choose category
    And the user selects an income category of "<category>"
    Then the dashboard balance is updated from "<balance>" balance, with "<amount type>" amount type, of "<currency>" currency and "<amount>" amount

    Examples:
      | balance | amount type | currency | amount |income note|category|
      | 0.00    | income      | £        | 100    |June Salary|Salary  |
      | 0.00    | income      | £        | 0.10   |Interest   |Deposits|
