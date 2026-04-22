package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.LoginPage;
import utils.DriverFactory;
import utils.ExcelUtil;
import utils.DBUtil;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;
    
    @DataProvider(name = "loginData")
    public Object[][] getData() {

        String filePath = System.getProperty("user.dir") +
                "/src/test/resources/testdata/LoginData.xlsx";

        return ExcelUtil.getTestData(filePath, "Sheet1");
    }
    
    @DataProvider(name = "productData")
    public Object[][] getProductData() {

        String path = System.getProperty("user.dir") +
                "/src/test/resources/testdata/PurchaseData.xlsx";

        return ExcelUtil.getTestData(path, "Sheet1");
    }
    
    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    
    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {

        loginPage.login(username, password);

        System.out.println("Testing with: " + username);

        if (username.equals("standard_user") || username.equals("problem_user")) {
            // expected success
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        } else {
            // expected failure
            Assert.assertTrue(driver.getPageSource().contains("Epic sadface"));
        }
    }
    @AfterMethod
    public void teardown() {
        DriverFactory.quitDriver();
    }
}