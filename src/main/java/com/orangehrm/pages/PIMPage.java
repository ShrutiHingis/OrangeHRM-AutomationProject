package com.orangehrm.pages;

import com.orangehrm.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage {

    private WebDriver driver;
    private WaitHelper waitHelper;

    private By pimMenu = By.xpath("//span[normalize-space()='PIM']");
    private By addEmployeeMenu = By.xpath("//a[normalize-space()='Add Employee']");

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
    }

    public void clickPIM() {
        waitHelper.waitForClickable(pimMenu).click();
    }

    public void clickAddEmployee() {
        waitHelper.waitForClickable(addEmployeeMenu).click();
    }
}
