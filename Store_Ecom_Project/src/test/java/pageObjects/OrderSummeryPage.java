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
public class OrderSummeryPage extends BasePage{

	public OrderSummeryPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//span[normalize-space()='I confirm my order']")
	WebElement btnConfirmOrder;
	
	public void clickConfirmOrder()
	{
		btnConfirmOrder.click();
	}

}
