/**
 * 
 */
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountCreationPage;
import pageObjects.AddressPage;
import pageObjects.LoadHomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

/**
  * Test case validates for the Account creation and in Addition it adds the Home Address 
 */
public class TC004_AccountCreationPageTest extends BaseClass 
{
	
	LoadHomePage hm;
	LoginPage lp;
	AccountCreationPage createAcc;
	AddressPage addressPage;
	
	@Test(groups = {"sanity"}, dataProvider = "newAcountDetailsData", dataProviderClass = DataProviders.class)
	public void verifyCreateAccountPageTest(String email, String gender, String firstName, String lastName, String password, String day, 
											String month, String year, String company, String address1, String address2, String city, 
											String state, String zipcode, String country, String homePhone, String mobilephone)
	{
		logger.info("**** Starting TC004_AccountCreationPageTest ****");
		try
		{
			logger.info("**Navigating to HomePage clicking on Sign In**"); 
			//Step 1: Navigate to Home Page and Click Sign In
			navigateToHomePage();
			
			logger.info("** Enter Register email address on LoginPage **"); 
			//Step 2: Enter email and Click 'Create Account'
			registerEmail(email);
			
			logger.info("** Enter Account details of Register Form - Account Creation Page **"); 
			// Step 3: Enter Account Details and Register
			fillAccountCreationForm(gender, firstName, lastName, password, day, month, year);
			
			logger.info("**Entering the Address info form**"); 
			//Step 4: Add Address and Save
			enterAddressDetails(company, address1, address2, city, state, zipcode, country, homePhone, mobilephone);
		}
		catch (Exception e) 
		{			
			logger.error("** Test Case failed **", e);
			Assert.fail("Exception occured: "+e.getMessage());
		}
		logger.info("**** Finishing TC004_AccountCreationPageTest ****");
	}
	
	public void navigateToHomePage()
	{
		try
		{
			hm = new LoadHomePage(driver);
			hm.clickSignIn();
			
		}
		catch (Exception e) 
		{
			logger.error("Error on HomePage or while Navigating to Login Page ");
			throw e;
		}	
	}

	public void registerEmail(String email)
	{
		try
		{
			lp = new LoginPage(driver);
			lp.registerEmail(email);
			lp.clickCreateAnAccount();
			
			//check for error message on Login Page
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
			
		}
		catch (Exception e) {
			logger.error("Error on LoginPage while navigating to Registration page");
			throw e;
		}
		
	}
	
	public void fillAccountCreationForm(String gender, String firstName, String lastName, String password, String day, String month, String year)
	{
		try
		{
			createAcc = new AccountCreationPage(driver);
			Assert.assertNotNull(createAcc, "Failed to initialize AccountCreationPage.");
			
			boolean createAccPageStatus = createAcc.validateAccountCreationPage();
			//Assert.assertEquals(createAccStatus, true, "Account Creation Failed");
			Assert.assertTrue(createAccPageStatus, "Account Creation Page Load Failed");
			logger.info("** Account Creation Page loaded successfully validated **");
			
			createAcc.selectTitle(gender); //Selecting title
			createAcc.enterFirstName(firstName);
			createAcc.enterLastName(lastName);
			createAcc.enterPassword(password);
			createAcc.selectDOB(day, month, year);
			createAcc.clickRegister();
			logger.info("**Entered All information for Account Registration**");
			
			if(createAcc.isErrorDisplayed())
			{
				String errorMessageAccountPage = createAcc.getErrorMessageText();
				logger.error("Error on Account Creation Page: "+errorMessageAccountPage);
				Assert.fail("Account Creation Error: "+errorMessageAccountPage);
			}
			else 
			{
	            logger.info("No error, continuing to the account creation page.");
			}
			
			Assert.assertTrue(createAcc.validateAccountCreated(), "Your account has been created - Successfully "); ;
			logger.info("**** Your account has been created - Successfully ****");
			
			createAcc.clickAddMyFirstAddress();
			logger.info("Navigated to Address Page");
			
		}
		catch (Exception e) 
		{
			logger.error("Error on Personal Information Form - Account Registration Page");
			throw e;
		}		
	}
	
	public void enterAddressDetails(String company, String address1, String address2, String city, String state, String zipcode, 
									String country, String homePhone, String mobilephone)
	{
		try
		{
			addressPage = new AddressPage(driver);
			addressPage.enterCompany(company);
			addressPage.enterAddress1(address1);
			addressPage.enterAddress2(address2);
			addressPage.enterCity(city);
			addressPage.selectState(state);
			addressPage.enterPostalCode(zipcode);
			addressPage.selectCountry(country);
			addressPage.enterHomeNumber(homePhone);
			addressPage.enterMobileNumber(mobilephone);
			addressPage.enterAddressReference("My Address1");
			addressPage.clickSave();
			logger.info("**** Address information provided in the Address form ****");
			
			if(addressPage.isErrorDisplayed())
			{
				String errorMessageAddressPage = addressPage.getErrorMessageText();
				logger.error("Error on Address Page: "+errorMessageAddressPage);
				Assert.fail("Address Page Error: "+errorMessageAddressPage);
			}
			else 
			{
	            logger.info("No error, continuing to address page validation.");
			}
			
			Assert.assertTrue(addressPage.validateAddressList(), "Address Added to the list of Addresses");	
			logger.info("*** Address Add to Address Page Validated *** ");
			
			addressPage.clickSignOut();
			logger.info("** Signed Out Successfully **");
		}
		catch (Exception e) 
		{
			logger.error("Error on Address Form - Address Page");
			throw e;
		}
		
		
	}
	
	
	

}
