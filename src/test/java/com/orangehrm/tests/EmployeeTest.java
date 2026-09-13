package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.*;
import com.orangehrm.utils.JsonReader;
import com.orangehrm.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tools.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;

public class EmployeeTest extends BaseTest {

    @BeforeClass
    public void loadConfig() throws IOException {
        PropertiesReader.loadProperties();
    }

    @Test
    public void addEmployee() throws Exception {

        // Open OrangeHRM
        driver.get(
                PropertiesReader.getProperty("url")
        );

        // Login
        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                PropertiesReader.getProperty("username"),
                PropertiesReader.getProperty("password")
        );

        // Verify Dashboard
        DashboardPage dashboardPage =
                new DashboardPage(driver);

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard is not displayed after login"
        );

        // Navigate to Add Employee
        PIMPage pimPage =
                new PIMPage(driver);

        pimPage.clickPIM();
        pimPage.clickAddEmployee();

        // Read employee data from JSON
        String filePath =
                "src/test/resources/test-data/Employee.json";

        JsonNode employeeData =
                JsonReader.readJsonFile(filePath);

        String firstName =
                employeeData.get("firstName").asString();

        String lastName =
                employeeData.get("lastName").asString();

        String employeeId =
                employeeData.get("employeeId").asString();

        String jobTitle =
                employeeData.get("jobTitle").asString();

        String employmentStatus =
                employeeData.get("employmentStatus").asString();

        // Profile picture path
        String profilePicturePath =
                new File(
                        "src/test/resources/test-data/images/Emp-Pic1.jpg"
                ).getAbsolutePath();

        // Add Employee
        AddEmployeePage addEmployeePage =
                new AddEmployeePage(driver);

        addEmployeePage.enterFirstName(firstName);

        addEmployeePage.enterLastName(lastName);

        addEmployeePage.enterEmployeeId(employeeId);

        // Check duplicate Employee ID
        if (addEmployeePage.isDuplicateEmployeeIdDisplayed()) {

            System.out.println(
                    "Employee ID already exists: " + employeeId
            );

            System.out.println(
                    "Employee creation stopped because Employee ID "
                            + employeeId
                            + " already exists."
            );

            return;
        }

        // Upload profile picture
        addEmployeePage.uploadProfilePicture(
                profilePicturePath
        );

        // Save Employee
        addEmployeePage.clickSave();

        // Verify Employee Details page
        EmployeeDetailsPage employeeDetailsPage =
                new EmployeeDetailsPage(driver);

        employeeDetailsPage.waitForEmployeeDetailsPage();

        // Update Job Details
        employeeDetailsPage.updateJobDetails(
                jobTitle,
                employmentStatus
        );

        // Verify Job Title
        Assert.assertEquals(
                employeeDetailsPage.getJobTitle(),
                jobTitle,
                "Job Title was not updated correctly"
        );

        // Verify Employment Status
        Assert.assertEquals(
                employeeDetailsPage.getEmploymentStatus(),
                employmentStatus,
                "Employment Status was not updated correctly"
        );

        System.out.println(
                "Employee Job Details updated successfully."
        );

        // Navigate to Employee List
        employeeDetailsPage.clickEmployeeList();

        EmployeeListPage employeeListPage =
                new EmployeeListPage(driver);

        // Search Employee
        employeeListPage.enterEmployeeId(employeeId);

        employeeListPage.clickSearch();

        // Verify Employee exists before deletion
        Assert.assertTrue(
                employeeListPage.isEmployeeFound(employeeId),
                "Employee was not found before deletion. "
                        + "Employee ID: " + employeeId
        );

        // Delete Employee
        employeeListPage.clickDelete();

        employeeListPage.confirmDelete();

        // Search again after deletion
        employeeListPage.clickSearch();

        // Verify Employee no longer exists
        Assert.assertFalse(
                employeeListPage.isEmployeeFound(employeeId),
                "Employee still exists after deletion. "
                        + "Employee ID: " + employeeId
        );

        System.out.println(
                "Employee deleted successfully: "
                        + employeeId
        );

        // Logout
        dashboardPage =
                new DashboardPage(driver);

        dashboardPage.logout();

        // Verify Login page is displayed
        LoginPage logoutLoginPage =
                new LoginPage(driver);

        Assert.assertTrue(
                logoutLoginPage.isLoginPageDisplayed(),
                "Login page is not displayed after logout"
        );

        System.out.println(
                "Logout successful. Login page is displayed."
        );

        // Verify Session Invalidation
        driver.get(
                PropertiesReader.getProperty("url")
                        + "web/index.php/dashboard/index"
        );

        LoginPage sessionLoginPage =
                new LoginPage(driver);

        Assert.assertTrue(
                sessionLoginPage.isLoginPageDisplayed(),
                "Session is still active after logout"
        );

        System.out.println(
                "Session invalidated successfully."
        );
    }
}
