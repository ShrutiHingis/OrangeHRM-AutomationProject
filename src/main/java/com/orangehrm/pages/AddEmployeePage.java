package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class AddEmployeePage {

    private WebDriver driver;

    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By employeeIdField = By.xpath("//label[text()='Employee Id']/../following-sibling::div/input");
    private By profilePictureInput = By.cssSelector("input[type='file']");
    private By saveButton = By.cssSelector("button[type='submit']");

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterEmployeeId(String employeeId) {
        WebElement element = driver.findElement(employeeIdField);

        element.click();
        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(employeeId);

        System.out.println(
                "Employee ID entered: " +
                        element.getAttribute("value")
        );
    }

    public void uploadProfilePicture(String filePath) {
        driver.findElement(profilePictureInput).sendKeys(filePath);
    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }

    public void addEmployee(String firstName, String lastName, String employeeId, String filePath) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmployeeId(employeeId);
        uploadProfilePicture(filePath);
        clickSave();
    }
}
