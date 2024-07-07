package stepdefinitions;

import Pages.SogetiAutomationPage;

import base.BaseUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;

public class SogetiAutomationSteps {

    private BaseUtil base;

    private Response response;

    private String country;

    private String postalCode;

    SogetiAutomationPage sogetiAutomationPage;

    public SogetiAutomationSteps(BaseUtil base)  {
        this.base = base;
        sogetiAutomationPage = new SogetiAutomationPage(BaseUtil.driver);
    }

    @Given("I goto the Sogeti website")
    public void iGotoTheSogetiWebsite() throws InterruptedException {
        sogetiAutomationPage.navigateToSite();
    }

    @And("I hover the Services Link")
    public void iHoverTheServicesLink() throws InterruptedException {
        sogetiAutomationPage.iHoverTheServicesLink();
    }

    @When("I click the automation Link")
    public void iClickTheAutomationLink() {
      sogetiAutomationPage.clickOnAutomation();
    }

    @And("I verify that automation screen is displayed and automation text is visible")
    public void iVerifyThatAutomationScreenIsDisplayedAndAutomationTextIsVisible() {
      sogetiAutomationPage.verifyTheAutomationScreenAndTextVisibility();
    }

    @And("I hover again the services link")
    public void iHoverAgainTheServicesLink() throws InterruptedException {
        sogetiAutomationPage.iHoverTheServicesLink();
    }

    @Then("I verify that services and automation are selected")
    public void iVerifyThatServicesAndAutomationAreSelected() {
        sogetiAutomationPage.VerifyServicesandAutomationAreSelected();

    }

    @And("I fill the form with Random Generated data")
    public void iFillTheFormWithRandomGeneratedData() throws InterruptedException {
        sogetiAutomationPage.fillTheForm();
    }

    @And("I select the I agree checkbox")
    public void iCheckTheIAgreeCheckbox() {
        sogetiAutomationPage.selectIAgree();
    }

    @And("I click submit button")
    public void iClickSubmitButton() {
        sogetiAutomationPage.selectSubmitButton();
    }

    @Then("I verify Thank you Message")
    public void iVerifyThankYouMessage() {
//        sogetiAutomationPage.verifyThankyouMessage();
    }

    @When("I click on Worldwide dropdown link in the page header")
    public void iClickOnWorldwideDropdownLinkInThePageHeader() {
        sogetiAutomationPage.clickWorldWideLink();
    }

    @Then("I verify all the links are working")
    public void iVerifyAllTheLinksAreWorking() {
        sogetiAutomationPage.verifyAllCountrySpecificSogetilinks();

    }


    @Given("I send a GET request to {string}")
    public void iSendAGETRequestTo(String url) {
        response = given().when().get(url);
    }

    @And("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("the response content type should be {string}")
    public void theResponseContentTypeShouldBe(String contentType) {
        response.then().contentType(contentType);
    }

    @And("the response time should be less than {long} second")
    public void theResponseTimeShouldBeLessThanSecond(long timeInSeconds) {
        response.then().time(lessThan(timeInSeconds), TimeUnit.SECONDS);
    }

    @And("the country should be {string}")
    public void theCountryShouldBe(String country) {
        response.then().body("country", equalTo(country));
    }

    @And("the state should be {string}")
    public void theStateShouldBe(String state) {
        response.then().body("state", equalTo(state));
    }

    @Then("for post code {string} the place name should be {string}")
    public void forPostCodeThePlaceNameShouldBe(String postCode, String placeName) {
        response.then().body("places.find { it['post code'] == '%s' }['place name']", withArgs(postCode), equalTo(placeName));
    }

    @Given("I have the country {string} and postal code {string}")
    public void iHaveTheCountryAndPostalCode(String country, String postalCode) {
        this.country = country;
        this.postalCode = postalCode;
    }

    @When("I send a request to Zippopotam API")
    public void iSendARequestToZippopotamAPI() {
        response = given()
                .pathParam("country", country)
                .pathParam("postal-code", postalCode)
                .when()
                .get("/{country}/{postal-code}");
    }
    @And("the place name should be {string}")
    public void thePlaceNameShouldBe(String arg0) {
    }
}
