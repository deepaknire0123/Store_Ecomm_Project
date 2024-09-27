/**
 * 
 */
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoadHomePage;
import testBase.BaseClass;

/**
 * Test Case Verifies the Logo and Page title of the Page
 */
public class TC001_LoadHomePageTest extends BaseClass{
	
	LoadHomePage hm;
	
	@Test(groups = {"smoke","sanity"})
	public void verifyLogo()
	{
		logger.info("****Verifying Logo on LoadHomePage*****");
		
		hm = new LoadHomePage(driver);
		Assert.assertTrue(hm.validateLogoDisplayed(), "Logo is not displayed"); ;
		
		logger.info("****Validated Logo on LoadHomePage*****");
	}
	
	
	@Test(groups = {"smoke"})
	public void verifyTitle()
	{
		logger.info("**Verify page title**");
		
		hm = new LoadHomePage(driver);
		Assert.assertEquals(hm.getPageTitle(), "My Shop", "Page title does not match");
		
		logger.info("** Validated Page Title **");
	}

}
