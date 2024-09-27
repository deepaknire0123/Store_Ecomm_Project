/**
 * 
 */
package pageObjects;

import java.nio.channels.SelectableChannel;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 */
public class AddressPage extends BasePage{

	public AddressPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='company']")
	WebElement txtCompany;
	
	@FindBy(xpath = "//input[@id='address1']")
	WebElement txtAddress1;
	
	@FindBy(xpath = "//input[@id='address2']")
	WebElement txtAddress2;

	@FindBy(xpath = "//input[@id='city']")
	WebElement txtCity;

	@FindBy(xpath = "//select[@id='id_state']")
	WebElement drpDownSelectState;

	@FindBy(xpath = "//input[@id='postcode']")
	WebElement txtZipCode;

	@FindBy(xpath = "//select[@id='id_country']")
	WebElement drpDownSelectCountry;

	@FindBy(xpath = "//input[@id='phone']")
	WebElement txtHomePhone;

	@FindBy(xpath = "//input[@id='phone_mobile']")
	WebElement txtMobilePhone;
	
	@FindBy(xpath = "//input[@id='alias']")
	WebElement txtAddressReference;
	
	@FindBy(xpath = "//span[normalize-space()='Save']")
	WebElement btnSave;
	
	@FindBy(xpath = "//div[@class='alert alert-danger']/ol")
	WebElement errorMsgAddress;
	
	@FindBy(xpath = "//strong[normalize-space()='Your addresses are listed below.']")
	WebElement msgAddressList;
	
	@FindBy(xpath = "//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]")
	WebElement btnProceedCheckOut;
	
	@FindBy(xpath = "//a[@title='Log me out']")
	WebElement lnkSignOut;

	public void enterCompany(String companyName) 
	{
		txtCompany.sendKeys(companyName);		
	}

	public void enterAddress1(String address1)
	{
		txtAddress1.sendKeys(address1);	
	}

	public void enterAddress2(String address2) 
	{
		txtAddress2.sendKeys(address2);
	}

	public void enterCity(String city) 
	{
		txtCity.sendKeys(city);
	}

	public void selectState(String state) 
	{
		Select selState = new Select(drpDownSelectState);
		selState.selectByVisibleText(state);
	}

	public void enterPostalCode(String zipCode) 
	{
		txtZipCode.sendKeys(zipCode);
	}

	public void selectCountry(String country) 
	{
		Select selCountry = new Select(drpDownSelectCountry);
		selCountry.selectByVisibleText(country);
	}

	public void enterHomeNumber(String homePhone) 
	{
		txtHomePhone.sendKeys(homePhone);		
	}

	public void enterMobileNumber(String mobilePhone) 
	{
		txtMobilePhone.sendKeys(mobilePhone);
	}

	public void enterAddressReference(String addressReference) 
	{
		txtAddressReference.clear();
		txtAddressReference.sendKeys(addressReference);
	}

	public void clickSave() 
	{
		btnSave.click();
	}

	public boolean isErrorDisplayed() 
	{
		try
		{
			return errorMsgAddress.isDisplayed();
		}
		catch (NoSuchElementException e) 
		{
			return false; //If element not diplayed
		}
		catch (Exception e) 
		{
			// If the element is found but not visible or any other issue, also return false
			return false;
		}
	}

	public String getErrorMessageText() 
	{
		try
		{
			return errorMsgAddress.getText();
		}
		catch (NoSuchElementException e) 
		{
			return "No error message found";
		}
	}

	public boolean validateAddressList() 
	{
		return msgAddressList.isDisplayed();
	}
	
	public void clickProceedToCheckOut()
	{
		btnProceedCheckOut.click();
	}
	
	public void clickSignOut()
	{
		lnkSignOut.click();
	}

}
