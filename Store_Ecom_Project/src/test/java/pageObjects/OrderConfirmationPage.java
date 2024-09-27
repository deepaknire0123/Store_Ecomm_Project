/**
 * 
 */
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 */
public class OrderConfirmationPage extends BasePage{

	public OrderConfirmationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//p[@class='alert alert-success']")
	WebElement msgOrderConfirmed;
	
	@FindBy(xpath = "//a[normalize-space()='View your order history']")
	WebElement lnkViewOrderHistory;
	
	public boolean validateOrderConfirmation()
	{
		return msgOrderConfirmed.isDisplayed();
	}
	
	public void clickViewOrderHistory()
	{
		lnkViewOrderHistory.click();
	}

}
