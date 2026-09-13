package com.orangehrm.pages;

import com.orangehrm.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class AddEmployeePage {

    private WebDriver driver;
    private WaitHelper waitHelper;

    private By firstNameField =
            By.name("firstName");

    private By lastNameField =
            By.name("lastName");

    private By employeeIdField =
            By.xpath("//label[text()='Employee Id']/../following-sibling::div/input");

    private By profilePictureInput =
            By.cssSelector("input[type='file']");

    private By saveButton =
            By.cssSelector("button[type='submit']");

    private By employeeIdAlreadyExistsMessage =
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
                "Employee ID entered: "
                        + element.getAttribute("value")
        );
    }

    public boolean isEmployeeIdAlreadyExists() {

        try {

            waitHelper.waitForVisibility(
                    employeeIdAlreadyExistsMessage
            );

            return true;

        } catch (Exception e) {

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

        enterFirstName(firstName);

        enterLastName(lastName);

        enterEmployeeId(employeeId);

        // Check whether Employee ID already exists
        if (isEmployeeIdAlreadyExists()) {

            System.out.println(
                    "Employee ID already exists: "
                            + employeeId
            );

            return false;
        }

        // Continue employee creation
        uploadProfilePicture(filePath);

        clickSave();

        return true;
    }
}
