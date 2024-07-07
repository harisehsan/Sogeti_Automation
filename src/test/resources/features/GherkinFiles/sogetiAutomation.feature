@sogeti_automation
Feature: Sogeti Automation testing

  @verify_the_automation_selection
  Scenario: Verify the Automation Selection
    Given I goto the Sogeti website
    And I hover the Services Link
    When I click the automation Link
    And I verify that automation screen is displayed and automation text is visible
    And I hover again the services link
    Then I verify that services and automation are selected

  @verify_the_automation_contact_us_form
  Scenario: Verify the automation contact us form
    Given I goto the Sogeti website
    And I hover the Services Link
    When I click the automation Link
    And I fill the form with Random Generated data
    And I select the I agree checkbox
    And I click submit button
#   Then I verify Thank you Message
# Due to captcha (which is used to avoid automation, This step cannot be executed as captacha says select Images with specific element. A human interaction is required to bypass this)

  @verify_country_specific_sogeti_links
  Scenario: verify country specific Sogeti links
    Given I goto the Sogeti website
    When I click on Worldwide dropdown link in the page header
    Then I verify all the links are working

  @verify_details_of_stuttgart_in_Baden-Württemberg
  Scenario: Verify API details of Stuttgart in Baden-Württemberg, Germany
    Given I send a GET request to "de/bw/stuttgart"
    And the response status code should be 200
    And the response content type should be "application/json"
    And the response time should be less than 1 second
    And the country should be "Germany"
    And the state should be "Baden-Württemberg"
    Then for post code "70597" the place name should be "Stuttgart Degerloch"

  @verify_the_place_name_for_given_country_and_postal_code
  Scenario Outline: Verify Zippopotam API Place Name for given Country and Postal Code
    Given I have the country "<country>" and postal code "<postalCode>"
    When I send a request to Zippopotam API
    And the response status code should be 200
    And the response content type should be "application/json"
    And the response time should be less than 1 second
    Then the place name should be "<placeName>"

    Examples:
      | country | postalCode | placeName       |
      | us      | 90210      | Beverly Hills   |
      | us      | 12345      | Schenectady     |
      | ca      | B2R        | Waverley        |

