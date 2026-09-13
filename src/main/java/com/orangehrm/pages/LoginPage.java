package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.orangehrm.utils.WaitHelper;

public class LoginPage {

    private WebDriver driver;
    private WaitHelper waitHelper;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    public void enterUsername(String username) {
        waitHelper.waitForVisibility(usernameField)
                .sendKeys(username);
    }

    public void enterPassword(String password) {
        waitHelper.waitForVisibility(passwordField)
                .sendKeys(password);
    }

    public void clickLogin() {
        waitHelper.waitForClickable(loginButton)
                .click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginPageDisplayed() {
        return waitHelper
                .waitForVisibility(usernameField)
                .isDisplayed();
    }

}

