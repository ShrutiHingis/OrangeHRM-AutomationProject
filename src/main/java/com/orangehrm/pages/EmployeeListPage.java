package com.orangehrm.pages;

import com.orangehrm.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeeListPage {

    private WebDriver driver;
    private WaitHelper waitHelper;

    private By employeeIdSearchField =
            By.xpath("//label[normalize-space()='Employee Id']/../following-sibling::div/input");

    private By searchButton =
            By.xpath("//button[normalize-space()='Search']");

    private By editButton =
            By.xpath("//button[.//i[contains(@class,'bi-pencil-fill')]]");

    private By deleteButton =
            By.xpath("//button[.//i[contains(@class,'bi-trash')]]");

    private By confirmDeleteButton =
            By.xpath("//button[normalize-space()='Yes, Delete']");

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    public void enterEmployeeId(String employeeId) {
        waitHelper.waitForVisibility(employeeIdSearchField)
                .sendKeys(employeeId);
    }

    public void clickSearch() {
        waitHelper.waitForClickable(searchButton)
                .click();
    }

    public boolean isEmployeeFound(String employeeId) {

        By employeeRow =
                By.xpath("//div[contains(@class,'oxd-table-row')]" +
                        "[.//div[contains(@class,'oxd-table-cell')]" +
                        "[normalize-space()='" + employeeId + "']]");

        By noRecordsFound =
                By.xpath("//*[normalize-space()='No Records Found']");

        try {
            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(driver ->
                    !driver.findElements(employeeRow).isEmpty()
                            || !driver.findElements(noRecordsFound).isEmpty()
            );

            return !driver.findElements(employeeRow).isEmpty();

        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickEdit() {
        waitHelper.waitForClickable(editButton)
                .click();
    }

    public void clickDelete() {
        waitHelper.waitForClickable(deleteButton)
                .click();
    }

    public void confirmDelete() {
        waitHelper.waitForClickable(confirmDeleteButton)
                .click();
    }
}
