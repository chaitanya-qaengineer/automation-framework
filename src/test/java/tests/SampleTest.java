package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.LoginPage;
import utils.DriverFactory;

public class SampleTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginTest() {

        loginPage.login("standard_user", "secret_sauce");

        // Validation
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Login failed");

        System.out.println("Login successful");
    }

    @AfterMethod
    public void teardown() {
        DriverFactory.quitDriver();
    }
}