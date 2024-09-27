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
public class OrderHistoryPage extends BasePage{

	public OrderHistoryPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//span[normalize-space()='Home']")
	WebElement btnHome;
	
	public void clickHome()
	{
		btnHome.click();
	}
	
	

}
