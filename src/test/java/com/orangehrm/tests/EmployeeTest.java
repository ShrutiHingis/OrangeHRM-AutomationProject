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
        driver.get(PropertiesReader.getProperty("url"));

        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                PropertiesReader.getProperty("username"),
                PropertiesReader.getProperty("password")
        );

        // Verify Dashboard
        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard is not displayed after login"
        );

        // Go to PIM
        PIMPage pimPage = new PIMPage(driver);

        pimPage.clickPIM();
        pimPage.clickAddEmployee();

        // Read employee data from JSON
        String filePath = "src/test/resources/test-data/Employee.json";

        JsonNode employeeData = JsonReader.readJsonFile(filePath);

        String firstName = employeeData.get("firstName").asString();
        String lastName = employeeData.get("lastName").asString();
        String employeeId = employeeData.get("employeeId").asString();

        // Profile picture path
        String profilePicturePath =
                new File("src/test/resources/test-data/images/Emp-Pic1.jpg")
                        .getAbsolutePath();

        // Add Employee
        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);

        addEmployeePage.addEmployee(
                firstName,
                lastName,
                employeeId,
                profilePicturePath
        );
        // Verify Employee Details
        EmployeeListPage employeeListPage =
                new EmployeeListPage(driver);

        String actualFirstName = employeeListPage.getFirstName();
        String actualLastName = employeeListPage.getLastName();
        String actualEmployeeId = employeeListPage.getEmployeeId();

        System.out.println("First Name: " + actualFirstName);
        System.out.println("Last Name: " + actualLastName);
        System.out.println("Employee ID: " + actualEmployeeId);

        Assert.assertEquals(
                actualFirstName,
                firstName,
                "First Name does not match"
        );

        Assert.assertEquals(
                actualLastName,
                lastName,
                "Last Name does not match"
        );

        Assert.assertEquals(
                actualEmployeeId,
                employeeId,
                "Employee ID does not match"
        );
    }
}
