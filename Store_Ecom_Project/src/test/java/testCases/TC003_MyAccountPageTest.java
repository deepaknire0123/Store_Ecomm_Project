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

/**
 * Test case for My Account page
 * Validates for Order History and My Credit slip button presence separately
 */
public class TC003_MyAccountPageTest extends BaseClass{
	
	LoadHomePage hm;
    LoginPage lp;
    MyAccountPage myAcc;

    @Test(groups = {"smoke"})
    public void verifyMyAccount() 
    {
    	logger.info("******* Starting TC003_MyAccountPageTest *******");
    	try
    	{
	        logger.info("** Navigating to Home Page and Clicking Sign In **");
	        hm = new LoadHomePage(driver);
	        hm.clickSignIn();
	
	        logger.info("** Logging In **");
	        lp = new LoginPage(driver);
	        lp.loginEmail(p.getProperty("email"));
	        lp.loginPassword(p.getProperty("password"));
	        lp.clickSignIn();
	
	        // Handling alert if present
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
	        
	        // Initialize MyAccountPage object
	        myAcc = new MyAccountPage(driver);
			
			// Validate successful login to My Account page
			boolean accountLogin = myAcc.validateMyAccount();
			Assert.assertEquals(accountLogin, true,"Login Failed");
			//Assert.assertTrue(accountLogin);
			logger.info("*** Login Success ***");
			
			//validate order history
			boolean orderHistoryStatus = myAcc.validateOrderHistory();
			Assert.assertEquals(orderHistoryStatus, true,"Order History Not Preset");
			//Assert.assertTrue(orderHistoryStatus);
			logger.info("*** Validated Order History ***");
			
			//Validate My credit slip
			boolean myCreditSlipStatus = myAcc.validateMyCreditSlip();
			//Assert.assertEquals(myCreditSlipStatus, true, "My Credit slip not preset");
			if(myCreditSlipStatus == true)
				Assert.assertTrue(true);
			else
			{
				logger.error("Test Failed");
				logger.debug("debug logs");
				Assert.assertTrue(false);
			}
			logger.info("*** Validated My Credit ***");
	
			// Sign out from My Account
			myAcc.clickSignOut();
			logger.info("*** Signing Out ***");
		}
		catch(Exception e) 
    	{
			Assert.fail();
		}
		logger.info("******* Finishing TC003_MyAccountPageTest *******");	
    }
}
