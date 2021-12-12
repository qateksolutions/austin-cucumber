Feature: Mortgage Calculator

  @CalculateApr
  Scenario: Calculate Real APR Rate
    Given user is in mortgage calculator home page
    And user navigate to Real Apr page
    When user click on calculate button upon entering the data
    |HomePrice|DownPayment|InterestRate|
    |200000   |15000      |3           |
    Then the real apr rate is "3.130%"