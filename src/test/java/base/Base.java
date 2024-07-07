package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Base extends BaseUtil {

    protected void waitUntilPageReady(Duration DEFAULT_TIMEOUT){
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    protected String getPageURL()
    {
        return driver.getCurrentUrl();
    }

    protected void mouseHover(WebElement ele) throws InterruptedException {
                        Actions action = new Actions(driver);
                        action.moveToElement(ele).
                                build().perform();

    }
    protected void waitUntilVisible(WebElement ele, Duration DEFAULT_TIMEOUT) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(ele));
    }

    protected void goToPage(String url)
    {
        driver.navigate().to(url);
    }

    public static String rgbaToHex(String rgba) {
        Pattern pattern = Pattern.compile("rgba?\\((\\d+),\\s*(\\d+),\\s*(\\d+),\\s*(\\d*\\.\\d+|\\d+)\\)");
        Matcher matcher = pattern.matcher(rgba);
        if (matcher.matches()) {
            int r = Integer.parseInt(matcher.group(1));
            int g = Integer.parseInt(matcher.group(2));
            int b = Integer.parseInt(matcher.group(3));
            float a = Float.parseFloat(matcher.group(4));
            int alpha = Math.round(a * 255);
            return String.format("#%02x%02x%02x%02x", r, g, b, alpha);
        }
        return null;
    }
}
