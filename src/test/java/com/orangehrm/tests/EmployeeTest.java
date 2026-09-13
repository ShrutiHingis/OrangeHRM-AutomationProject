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

        // Navigate to PIM > Add Employee
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

        // Profile picture path
        String profilePicturePath =
                new File(
                        "src/test/resources/test-data/images/Emp-Pic1.jpg"
                ).getAbsolutePath();

        // Add Employee
        AddEmployeePage addEmployeePage =
                new AddEmployeePage(driver);

        boolean employeeCreated =
                addEmployeePage.addEmployee(
                        firstName,
                        lastName,
                        employeeId,
                        profilePicturePath
                );

        // Handle duplicate Employee ID
        if (!employeeCreated) {

            System.out.println(
                    "Employee creation stopped because Employee ID "
                            + employeeId
                            + " already exists."
            );

            return;
        }

        // Wait for Employee Details page
        EmployeeDetailsPage employeeDetailsPage =
                new EmployeeDetailsPage(driver);

        employeeDetailsPage.waitForEmployeeDetailsPage();

        // Navigate to Employee List
        employeeDetailsPage.clickEmployeeList();

        // Search employee by Employee ID
        EmployeeListPage employeeListPage =
                new EmployeeListPage(driver);

        employeeListPage.enterEmployeeId(employeeId);

        employeeListPage.clickSearch();

        // Temporary pause for visual verification
        Thread.sleep(5000);

        // Verify employee
        boolean employeeFound =
                employeeListPage.isEmployeeFound();

        if (employeeFound) {

            System.out.println(
                    "Employee found successfully: "
                            + firstName
                            + " "
                            + lastName
                            + " | Employee ID: "
                            + employeeId
            );

        } else {

            System.out.println(
                    "Employee not found: "
                            + firstName
                            + " "
                            + lastName
                            + " | Employee ID: "
                            + employeeId
            );
        }

        Assert.assertTrue(
                employeeFound,
                "Employee was not found in Employee List. Employee ID: "
                        + employeeId
        );
    }
}
