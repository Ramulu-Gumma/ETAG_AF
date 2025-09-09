package com.Core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.Drivers.DriverManager;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    
    /** ---------- Basic Actions ---------- **/

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void type(WebElement element, String text) {
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** ---------- Dropdowns ---------- **/

    protected void selectByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    protected void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    protected void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    /** ---------- JavaScript Helpers ---------- **/

    protected void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    protected void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void scrollBy(int x, int y) {
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    /** ---------- Advanced Actions ---------- **/

    protected void hover(WebElement element) {
        actions.moveToElement(element).perform();
    }

    protected void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }

    protected void rightClick(WebElement element) {
        actions.contextClick(element).perform();
    }

    protected void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }

    /** ---------- Screenshots ---------- **/

 // Add this method to your BasePage.java
    protected String takeScreenshot(String name) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "target/screenshots/" + name + "_" + timestamp + ".png";
        try {
            Files.createDirectories(new File("target/screenshots/").toPath());
            Files.copy(src.toPath(), new File(path).toPath());
            
            // Also attach to Cucumber scenario if available
            try {
                byte[] screenshotBytes = Files.readAllBytes(src.toPath());
                // This requires scenario object, better handled in Hooks
            } catch (Exception e) {
                // Silent catch for optional attachment
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /** ---------- Wait Helpers ---------- **/

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForInvisibility(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitForPageTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }

    protected void waitForElements(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}