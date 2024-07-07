package Pages;

import base.Base;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageObject.SogetiAutomationPageObject;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class SogetiAutomationPage extends Base {
    WebDriver driver;
    SogetiAutomationPageObject sogetiAutomationPageObject = new SogetiAutomationPageObject();

    public SogetiAutomationPage(WebDriver driver) {
    //    super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, sogetiAutomationPageObject);
    }

    public void navigateToSite() throws InterruptedException {
        goToPage("https://www.sogeti.com/");
        if (sogetiAutomationPageObject.decline_cookie_btn.isDisplayed())
            sogetiAutomationPageObject.decline_cookie_btn.click();
//        waitUntilPageReady(Duration.ofMinutes(1));
    }

    public void iHoverTheServicesLink() throws InterruptedException {
         mouseHover(sogetiAutomationPageObject.services_menu);
    }

    public void clickOnAutomation()
    {
        sogetiAutomationPageObject.automation_subMenu.click();
    }

    public void verifyTheAutomationScreenAndTextVisibility()
    {
        waitUntilPageReady(Duration.ofMinutes(1));
        Assert.assertTrue(getPageURL().contains("automation"), "Not successfully navigated to the automation screen");
        Assert.assertTrue(sogetiAutomationPageObject.automation_lbl.isDisplayed(),"Automation text is not visible");
    }


    public void VerifyServicesandAutomationAreSelected()
    {
       Assert.assertEquals(rgbaToHex(sogetiAutomationPageObject.services_menu.getCssValue("color")),"#ff304cff","Services menu is not selected!");
       Assert.assertEquals(rgbaToHex(sogetiAutomationPageObject.automation_subMenu.getCssValue("color")),"#ff304cff","Automation submenu is not selected!");
    }

    public void fillTheForm() {
        Faker faker = new Faker();
        sogetiAutomationPageObject.first_Name_txtBox.sendKeys(faker.name().firstName());
        sogetiAutomationPageObject.last_Name_txtBox.sendKeys(faker.name().lastName());
        sogetiAutomationPageObject.email_txtBox.sendKeys(faker.internet().emailAddress());
        sogetiAutomationPageObject.phone_txtBox.sendKeys(faker.phoneNumber().phoneNumber());
        sogetiAutomationPageObject.company_txtBox.sendKeys(faker.company().name());
        sogetiAutomationPageObject.message_txtBox.sendKeys(faker.lorem().sentence());
        sogetiAutomationPageObject.country_drpDown.click();
        waitUntilVisible(sogetiAutomationPageObject.getCountry_lst,Duration.ofSeconds(20));
        Select select = new Select(sogetiAutomationPageObject.getCountry_lst);
        List<WebElement> options = select.getOptions();
        Random random = new Random();
        int randomIndex = random.nextInt(options.size());
        select.selectByIndex(randomIndex);
        sogetiAutomationPageObject.country_drpDown.click();
    }

    public void selectIAgree()
    {
      sogetiAutomationPageObject.i_agree_chkBox.click();
      driver.switchTo().frame(sogetiAutomationPageObject.captcha_iframe);
      sogetiAutomationPageObject.captcha_chkBox.click();
      driver.switchTo().defaultContent();
    }

    public void selectSubmitButton()
    {
      sogetiAutomationPageObject.submit_btn.click();
    }

//    public void verifyThankyouMessage() //This check is not performed as due to captcha which is required to avoid automation
//    {
//        Assert.assertTrue(sogetiAutomationPageObject.thankyou_message_lbl.isDisplayed(),"Either Thank you message is not displayed or form was not submitted!");
//    }

    public void clickWorldWideLink()
    {
       sogetiAutomationPageObject.worldWide_lbl.click();
    }

    public void verifyAllCountrySpecificSogetilinks() {
        boolean teststatus = true;
        StringBuilder errorMessage = new StringBuilder();
        List<WebElement> links = sogetiAutomationPageObject.country_lst.findElements(By.tagName("a"));
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            Response response = RestAssured.get(String.valueOf(url));
            int statusCode = response.getStatusCode();
            if (statusCode != 200) {
                teststatus = false;
                errorMessage.append(url+" Not working and having status code: "+statusCode+"\n");
                }
            }
        Assert.assertTrue(teststatus, String.valueOf(errorMessage));
    }
}