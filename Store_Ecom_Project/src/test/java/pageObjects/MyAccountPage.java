/**
 * 
 */
package pageObjects;

import java.lang.module.FindException;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 
 */
public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//h1[normalize-space()='My account']")
	WebElement msgMyAccount;
	
	@FindBy(xpath = "//span[normalize-space()='Order history and details']")
	WebElement btnOrderHistory;
	
	@FindBy(xpath = "//span[normalize-space()='My credit slips']")
	WebElement btnMyCreditSlip;
	
	@FindBy(xpath = "//a[@title='Log me out']")
	WebElement lnkSignOut;
	
	public boolean validateMyAccount()
	{
		return msgMyAccount.isDisplayed();
	}

	public void clickSignOut() 
	{
		lnkSignOut.click();
	}

	public boolean validateOrderHistory() 
	{
		return btnOrderHistory.isDisplayed();
	}

	public boolean validateMyCreditSlip() 
	{
		return btnMyCreditSlip.isDisplayed();
	}

}
