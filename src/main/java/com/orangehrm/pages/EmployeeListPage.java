package com.orangehrm.pages;

import com.orangehrm.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeeListPage {

    private WebDriver driver;
    private WaitHelper waitHelper;

    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By employeeIdField =
            By.xpath("//label[normalize-space()='Employee Id']/following::input[1]");

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    public String getFirstName() {
        return waitHelper.waitForVisibility(firstNameField)
                .getAttribute("value");
    }

    public String getLastName() {
        return waitHelper.waitForVisibility(lastNameField)
                .getAttribute("value");
    }

    public String getEmployeeId() {
        return waitHelper.waitForVisibility(employeeIdField)
                .getAttribute("value");
    }
}
