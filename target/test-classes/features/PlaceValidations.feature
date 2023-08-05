Feature: Verify the API suites for places

  @Sanity
  Scenario: Verify adding a new place via AddNewPlace api
    Given Prepare the add new place api request
      | name    | Karnataka                 |
      | address | 29, side layout, cohen 09 |
    When User calls "AddPlaceApi" with "POST" request
    Then Verify the response code is 200
    And Verify the "scope" in the response body is "APP"
    And Verify the "status" in the response body is "OK"

@Regression
  Scenario: Verify deleting a place via AddNewPlace api
    Given Prepare the delete place api request
    When User calls "DeletePlaceApi" with "POST" request
    Then Verify the response code is 200
    And Verify the "status" in the response body is "OK"
