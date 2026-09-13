package com.orangehrm.pages;

import com.orangehrm.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeeListPage {

    private WebDriver driver;
    private WaitHelper waitHelper;

    private By employeeIdSearchField =
            By.xpath("//label[normalize-space()='Employee Id']/../following-sibling::div/input");

    private By searchButton =
            By.xpath("//button[normalize-space()='Search']");

    private By employeeTable =
            By.cssSelector("div.oxd-table-body");

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    public void enterEmployeeId(String employeeId) {
        waitHelper.waitForVisibility(employeeIdSearchField)
                .sendKeys(employeeId);
    }

    public void clickSearch() {
        waitHelper.waitForClickable(searchButton).click();
    }

    public boolean isEmployeeFound() {

        try {
            waitHelper.waitForVisibility(employeeTable);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
