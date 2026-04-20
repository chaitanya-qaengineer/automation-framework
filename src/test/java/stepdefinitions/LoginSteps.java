
package stepdefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import pages.LoginPage;
import utils.DriverFactory;
import org.testng.Assert;


public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @When("user enters username and password")
    public void user_enters_credentials() {
        loginPage.login("standard_user", "wrong_password");
    }

    @Then("user should be logged in")
    public void user_should_be_logged_in() {
        String currentUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"), "Login failed");
    }
    }
