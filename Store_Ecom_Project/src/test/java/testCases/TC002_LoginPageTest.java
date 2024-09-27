/**
 * 
 */
package testCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoadHomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/**
 * Verify user login with valid credentials and handle alert if present
 */
public class TC002_LoginPageTest extends BaseClass{
	
	@Test(dataProvider = "LoginCredentials", dataProviderClass = DataProviders.class,
			groups = {"smoke", "sanity"})
    public void verifyLogin(String email, String password) 
	{
		logger.info("**** Starting TC002_LoginPageTest **** ");
        try {
	            // Navigate to Home Page and click Sign In
	            LoadHomePage hm = new LoadHomePage(driver);
	            hm.clickSignIn();
	            logger.info("** Navigated to Login Page");
	
	            // Perform Login
	            LoginPage lp = new LoginPage(driver);
	            lp.loginEmail(email);
	            lp.loginPassword(password);
	            lp.clickSignIn();
	            logger.info("Clicked Sign in");
	            
	            if(lp.isErrorDisplayed())
				{
					String errorMessageLoginPage = lp.getErrorMessageText();
					logger.error("Error on Login Page: " +errorMessageLoginPage);
					Assert.fail("Login Page Error: "+errorMessageLoginPage);
				}
				else 
				{
		            logger.info("No error, continuing to the login page.");
				}
	            logger.info("** Navigated to My Account Page **");
	
	            // Handle Alert if present, continue execution if no alert
	            try 
	            {
	                Alert alert = driver.switchTo().alert();
	                alert.accept(); // Accept the alert (click OK)
	                logger.info("** Alert Handled **" +alert);
	            } 
	            catch (NoAlertPresentException e) 
	            {
	                // Continue execution if no alert is present (silent handling)
	            }
	
	            // Validate successful login to My Account page
	            MyAccountPage myAccountPage = new MyAccountPage(driver);
	            boolean isLoginSuccessful = myAccountPage.validateMyAccount();
	            Assert.assertTrue(isLoginSuccessful, "Login Failed");
	            logger.info("** Validated My Account Page **");
	
	            // Sign out from My Account
	            myAccountPage.clickSignOut();
	            logger.info("** Signed out from My Account Page **");

        	} 
        	catch (Exception e) 
        	{
            // Catch any other unexpected exceptions and fail the test
            Assert.fail("Test failed due to unexpected error: " + e.getMessage());
        	}
        
        logger.info("**** Finishing TC002_LoginPageTest **** ");
    }

}
