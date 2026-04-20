package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.DriverFactory;

public class SampleTest {

    @Test
    public void loginTest() {

        WebDriver driver = DriverFactory.getDriver();

        driver.get("https://www.saucedemo.com");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        System.out.println("Login attempted");

        DriverFactory.quitDriver();
    }
}