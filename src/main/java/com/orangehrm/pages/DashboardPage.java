package com.orangehrm.pages;

import com.orangehrm.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    private WebDriver driver;
    private WaitHelper waitHelper;

    private By dashboardHeading = By.xpath("//h6[normalize-space()='Dashboard']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    public boolean isDashboardDisplayed() {
        return waitHelper.waitForVisibility(dashboardHeading).isDisplayed();
    }
}

