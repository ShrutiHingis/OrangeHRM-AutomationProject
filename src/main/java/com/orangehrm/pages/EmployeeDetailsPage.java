package com.orangehrm.pages;

import com.orangehrm.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeeDetailsPage {

    private WebDriver driver;
    private WaitHelper waitHelper;

    // Personal Details page heading
    private By personalDetailsHeading =
            By.xpath("//h6[normalize-space()='Personal Details']");

    // Employee List tab
    private By employeeListMenu =
            By.xpath("//a[normalize-space()='Employee List']");

    // Job tab
    private By jobTab =
            By.xpath("//a[normalize-space()='Job']");

    // Job Title dropdown
    private By jobTitleDropdown =
            By.xpath("//label[normalize-space()='Job Title']/following::div[contains(@class,'oxd-select-text')][1]");

    // Employment Status dropdown
    private By employmentStatusDropdown =
            By.xpath("//label[normalize-space()='Employment Status']/following::div[contains(@class,'oxd-select-text')][1]");

    // Save button
    private By saveButton =
            By.xpath("//button[@type='submit' and normalize-space()='Save']");

    // Selected Job Title
    private By selectedJobTitle =
            By.xpath("//label[normalize-space()='Job Title']/following::div[contains(@class,'oxd-select-text-input')][1]");

    // Selected Employment Status
    private By selectedEmploymentStatus =
            By.xpath("//label[normalize-space()='Employment Status']/following::div[contains(@class,'oxd-select-text-input')][1]");


    public EmployeeDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }


    // Wait until Personal Details page is displayed
    public void waitForEmployeeDetailsPage() {
        waitHelper.waitForVisibility(personalDetailsHeading);
    }


    // Click Employee List
    public void clickEmployeeList() {
        waitHelper.waitForClickable(employeeListMenu).click();
    }


    // Click Job tab
    public void clickJobTab() {
        waitHelper.waitForClickable(jobTab).click();
    }


    // Select Job Title using value from JSON
    public void selectJobTitle(String jobTitle) {

        By jobTitleOption =
                By.xpath("//div[@role='option']//span[normalize-space()='"
                        + jobTitle + "']");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        waitHelper.waitForClickable(jobTitleDropdown).click();

        waitHelper.waitForClickable(jobTitleOption).click();
    }


    // Select Employment Status using value from JSON
    public void selectEmploymentStatus(String employmentStatus) {

        By employmentStatusOption =
                By.xpath("//div[@role='option']//span[normalize-space()='"
                        + employmentStatus + "']");

        waitHelper.waitForClickable(employmentStatusDropdown).click();

        waitHelper.waitForClickable(employmentStatusOption).click();
    }


    // Click Save
    public void clickSave() {
        waitHelper.waitForClickable(saveButton).click();
    }


    // Update Job Details
    public void updateJobDetails(
            String jobTitle,
            String employmentStatus) {

        clickJobTab();

        selectJobTitle(jobTitle);

        selectEmploymentStatus(employmentStatus);

        clickSave();

        // Temporary wait to visually verify the update
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    // Get selected Job Title
    public String getJobTitle() {
        return waitHelper.waitForVisibility(selectedJobTitle)
                .getText();
    }


    // Get selected Employment Status
    public String getEmploymentStatus() {
        return waitHelper.waitForVisibility(selectedEmploymentStatus)
                .getText();
    }
}
