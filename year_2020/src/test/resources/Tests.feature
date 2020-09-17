@Tests
Feature: Tests

  @Test
  Scenario: [1] Successful login check
    Given Open home page
    And Click 'Enter' button
    And Login with "user_with_valid_password"
    When 'Submit' button click
    Then Verify mail box is opened

  @Test @run
  Scenario: [2] Successful letter sending check
    Given Open home page
    And Click 'Enter' button
    And Login with "user_with_valid_password"
    When 'Submit' button click
    And 'Write' button click
    And Send email to: "dartar123456@yandex.ru"
    And Verify 'Letter was send' text is present
