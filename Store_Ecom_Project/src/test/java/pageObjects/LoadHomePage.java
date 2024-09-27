/**
 * 
 */
package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 */
public class LoadHomePage extends BasePage{

	public LoadHomePage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='search_query_top']")
	WebElement txtSearchProduct;
	
	@FindBy(xpath = "//button[@name='submit_search']")
	WebElement btnSearch;
	
	@FindBy(xpath = "//img[@alt='My Shop']")
	WebElement imgStoreLogo;
	
	@FindBy(xpath = "//a[normalize-space()='Sign in']")
	WebElement lnkSignIn;
	
	public void enterSearchProduct(String Product)
	{
		if(Product == null || Product.trim().isEmpty())
		{
			System.out.println("Invalid Product Name Provided");
			return;
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(txtSearchProduct));
		txtSearchProduct.clear();
		txtSearchProduct.sendKeys(Product);
	}
	
	public void clickSearchProduct()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch));
		btnSearch.click();
	}
	
	public boolean validateLogoDisplayed()
	{
		return imgStoreLogo.isDisplayed();
	}
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	public void clickSignIn()
	{
		lnkSignIn.click();
	}
}
