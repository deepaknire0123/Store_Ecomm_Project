/**
 */
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
* BasePage class serves as the parent class for all page object classes.
 * It holds the common functionality and constructor shared across page objects.
 */
public class BasePage 
{
	
	WebDriver driver;
	
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
