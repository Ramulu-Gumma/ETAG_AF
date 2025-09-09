package com.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Core.BasePage;

public class MDHLoginPage extends BasePage {

    // Locators
    @FindBy(xpath = "//button[@class='btn btn-teal loginbtn']")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@id='identifier']")
    private WebElement sgidInput;

    @FindBy(xpath = "//*[text()='Next']")
    private WebElement nextBtn;

    @FindBy(xpath = "//*[@name='credentials.passcode']")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[text()='Verify']")
    private WebElement verifyBtn;

    @FindBy(xpath = "//*[contains(text(),'MDH')]")
    private WebElement mdhDashboard;

    @FindBy(xpath = "//*[contains(text(),'ETAG')]")
    private WebElement etagDashboard;

    public MDHLoginPage() {
        super();
    }

    // Actions
    public void openLoginPage(String url) {
        driver.get(url);
    }

    public void clickLogin() {
        click(loginBtn);
    }    

    public void enterSgid(String sgid) {
        type(sgidInput, sgid);
    }

    public void clickNext() {
        click(nextBtn);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickVerify() {
        click(verifyBtn);
    }

    public boolean isMdhDashboardVisible() {
        return isDisplayed(mdhDashboard);
    }
    
    public boolean isEtagDashboardVisible() {
        return isDisplayed(etagDashboard);
    }
    
    public boolean isLoginPageDisplayed() {
        return isDisplayed(loginBtn);
    }
}