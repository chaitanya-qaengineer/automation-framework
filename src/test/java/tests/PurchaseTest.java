package tests;

import org.openqa.selenium.WebDriver; 
import org.testng.Assert; 
import org.testng.annotations.*; 
import pages.LoginPage; 
import pages.InventoryPage; 
import utils.DriverFactory;

public class PurchaseTest {

	WebDriver driver;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setup() {
		driver = DriverFactory.getDriver();
		driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
	}
    
     @Test
     public void completePurchaseTest() {
    	 loginPage.login("standard_user", "secret_sauce");
pause(2);
    	 InventoryPage inventory = new InventoryPage(driver);
    	 inventory.clickAddToCart();
    	 pause(2);
    	 inventory.clickCart();
    	 pause(2);
    	 Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
     }
     public void pause(int seconds) {
    	    try {
    	        Thread.sleep(seconds * 1000);
    	    } catch (Exception e) {}
    	}
     
     @AfterMethod 
     public void teardown() { 
    	 DriverFactory.quitDriver(); 
    	 }
      
        
	}
	

