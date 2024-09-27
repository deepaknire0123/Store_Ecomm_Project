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
public class PaymentPage extends BasePage{

	public PaymentPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//a[@title='Pay by bank wire']")
	WebElement btnBankWire;
	
	public void clickOnPaymentMethod()
	{
		btnBankWire.click();
	}

}
