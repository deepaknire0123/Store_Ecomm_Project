/**
 * 
 */
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 
 */
public class ShippingPage extends BasePage{

	public ShippingPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='cgv']")
	WebElement chkTerms;
	
	@FindBy(xpath = "//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")
	WebElement btnProceedToCheckOut;
	
	public void clickTermsCheckBox()
	{
		chkTerms.click();
	}
	
	public void clickProceedToCheckOut()
	{
		btnProceedToCheckOut.click();
	}

}
