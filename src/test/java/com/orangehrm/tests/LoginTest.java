package com.orangehrm.tests;


import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

        @BeforeClass
        public void loadConfig() throws IOException {
            PropertiesReader.loadProperties();
        }

        @Test
        public void verifyLogin() throws InterruptedException {

            driver.get(PropertiesReader.getProperty("url"));

            LoginPage loginPage = new LoginPage(driver);

            loginPage.login(
                    PropertiesReader.getProperty("username"),
                    PropertiesReader.getProperty("password")
            );

            DashboardPage dashboardPage = new DashboardPage(driver);

            Assert.assertTrue(dashboardPage.isDashboardDisplayed());
        }
    }

