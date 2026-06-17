Feature: Login

  @android @ios @login
  Scenario: Successful login
    Given the user is on the login screen
    When the user logs in with username "testuser" and password "Password123"