package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SogetiAutomationPageObject {

//    @FindBy(id = "L2AGLb") public WebElement accept_cookie;
//    @FindBy(name = "q") public WebElement search_bar;
//
//    @FindBy(name = "btnK") public WebElement google_search_btn;

    @FindBy(className = "declineCookie") public WebElement decline_cookie_btn;
    @FindBy(xpath = "//*[@id='main-menu']//span[contains(text(),'Services')]") public WebElement services_menu;
    @FindBy(xpath = "//a[contains(@class,'subMenuLink')][normalize-space()='Automation']") public WebElement automation_subMenu;
    @FindBy(xpath = "//span[normalize-space()='Automation']") public WebElement automation_lbl;

    @FindBy(xpath = "//*[text()='First Name']/following-sibling::input") public WebElement first_Name_txtBox;

    @FindBy(xpath = "//*[text()='Last Name']/following-sibling::input") public WebElement last_Name_txtBox;

    @FindBy(xpath = "//*[text()='Email']/following-sibling::input") public WebElement email_txtBox;

    @FindBy(xpath = "//*[text()='Phone']/following-sibling::input") public WebElement phone_txtBox;

    @FindBy(xpath = "//*[text()='Company']/following-sibling::input") public WebElement company_txtBox;

    @FindBy(xpath = "//*[text()='Message']/following-sibling::textarea") public WebElement message_txtBox;

    @FindBy(xpath = "//*[text()='Country']/following-sibling::div") public WebElement country_drpDown;

    @FindBy(tagName = "select") public WebElement getCountry_lst;

    @FindBy(xpath = "//label[text()='I agree']") public WebElement i_agree_chkBox;

    @FindBy(xpath = "//iframe[@title='reCAPTCHA']") public WebElement captcha_iframe;

    @FindBy(className = "recaptcha-checkbox-border") public WebElement captcha_chkBox;

    @FindBy(name="submit") public WebElement submit_btn;

    @FindBy(xpath = "//p[text()='Thank you for contacting us.']") public WebElement thankyou_message_lbl;

    @FindBy(xpath = "//span[@aria-label='Worldwide']") public WebElement worldWide_lbl;

    @FindBy(id = "country-list-id") public WebElement country_lst;



}
