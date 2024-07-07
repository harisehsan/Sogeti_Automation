package stepdefinitions;

import base.BaseUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Hooks extends BaseUtil {

    private String scenarioName;
    private BaseUtil base;


    public Hooks(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) throws Exception {
        scenarioName = scenario.getName();
        if (!scenarioName.contains("API")) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        }
        else {
            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .setBaseUri("https://api.zippopotam.us/")
                    .addFilter(new RequestLoggingFilter())
                    .addFilter(new ResponseLoggingFilter())
                    .build();
        }
    }

    @After
    public void TearDownTest(Scenario scenario) throws Exception {
        if (!scenarioName.contains("API"))
            driver.quit();
    }
}