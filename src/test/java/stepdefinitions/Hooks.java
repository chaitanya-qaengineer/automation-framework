package stepdefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import utils.DriverFactory;
import utils.ScreenshotUtil;

public class Hooks {

    WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com");
    }

    @After
    public void tearDown(Scenario scenario) {

        System.out.println("Is test failed? " + scenario.isFailed()); // DEBUG

        if (scenario.isFailed()) {
            ScreenshotUtil.captureScreenshot(DriverFactory.getDriver(), scenario.getName());
        }

        DriverFactory.quitDriver();
    }
   }
