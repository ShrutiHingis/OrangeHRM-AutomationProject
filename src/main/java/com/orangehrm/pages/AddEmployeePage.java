package com.orangehrm.pages;

import com.orangehrm.utils.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage {

    private WebDriver driver;
    private WaitHelper waitHelper;

    // First Name
    private By firstNameField = By.name("firstName");

    // Last Name
    private By lastNameField = By.name("lastName");

    // Employee ID
    private By employeeIdField =
            By.xpath("//label[normalize-space()='Employee Id']/../following-sibling::div/input");

    // Profile Picture
    private By profilePictureInput =
            By.cssSelector("input[type='file']");

    // Save button
    private By saveButton =
            By.cssSelector("button[type='submit']");

    // Duplicate Employee ID message
    private By duplicateEmployeeIdMessage =
            By.xpath("//span[normalize-space()='Employee Id already exists']");


    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }


    public void enterFirstName(String firstName) {
        waitHelper.waitForVisibility(firstNameField)
                .sendKeys(firstName);
    }


    public void enterLastName(String lastName) {
        waitHelper.waitForVisibility(lastNameField)
                .sendKeys(lastName);
    }


    public void enterEmployeeId(String employeeId) {

        WebElement element =
                waitHelper.waitForVisibility(employeeIdField);

        element.click();

        element.sendKeys(Keys.CONTROL, "a");

        element.sendKeys(Keys.BACK_SPACE);

        element.sendKeys(employeeId);

        System.out.println(
                "Employee ID entered: " +
                        element.getAttribute("value")
        );
    }


    public boolean isDuplicateEmployeeIdDisplayed() {

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(2));

            wait.until(driver ->
                    !driver.findElements(duplicateEmployeeIdMessage).isEmpty()
            );

            return true;

        } catch (TimeoutException e) {

            return false;
        }
    }


    public void uploadProfilePicture(String filePath) {

        driver.findElement(profilePictureInput)
                .sendKeys(filePath);
    }


    public void clickSave() {

        waitHelper.waitForClickable(saveButton)
                .click();
    }


    public boolean addEmployee(
            String firstName,
            String lastName,
            String employeeId,
            String filePath) {

        // Enter employee details
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmployeeId(employeeId);


        // Check whether Employee ID already exists
        if (isDuplicateEmployeeIdDisplayed()) {

            System.out.println(
                    "Employee ID already exists: " + employeeId
            );

            System.out.println(
                    "Employee creation stopped because Employee ID "
                            + employeeId
                            + " already exists."
            );

            return false;
        }


        // Upload profile picture only if Employee ID is valid
        uploadProfilePicture(filePath);


        // Save employee
        clickSave();


        // Employee creation completed
        return true;
    }
}
