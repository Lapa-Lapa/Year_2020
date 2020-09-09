@Tests
Feature: Tests

  @Test
  Scenario: [1] Successful login check
    Given Open home page
    And Click 'Enter' button
    And Fill in email "DarTar123456" and password "DarTar123456!"
    When 'Submit' button click
    Then Verify mail box is opened

  @Test @run
  Scenario: [2]Successful letter sending check
    Given Open home page
    And Click 'Enter' button
    And Fill in email "DarTar123456" and password "DarTar123456!"
    When 'Submit' button click
    And 'Write' button click
    And Type "dartar123456@yandex.ru" in "ADDRESSEE" field
    And Type "Subject" in "SUBJECT" field
    And Type "Text" in "LETTER_TEXT" field
    And 'Send' button click
    And Verify 'Letter was send' text is present
