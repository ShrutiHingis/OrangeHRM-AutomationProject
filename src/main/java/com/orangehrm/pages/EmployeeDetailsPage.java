package com.orangehrm.pages;

import com.orangehrm.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeeDetailsPage {

    private WebDriver driver;
    private WaitHelper waitHelper;

    private By personalDetailsHeading =
            By.xpath("//h6[normalize-space()='Personal Details']");

    private By employeeListTab =
            By.xpath("//a[normalize-space()='Employee List']");

    public EmployeeDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    public void waitForEmployeeDetailsPage() {
        waitHelper.waitForVisibility(personalDetailsHeading);
    }

    public void clickEmployeeList() {
        waitHelper.waitForClickable(employeeListTab).click();
    }
}
