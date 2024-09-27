/**
 * 
 */
package pageObjects;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 */
public class SearchResultPage extends BasePage{

	public SearchResultPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//a[@class='product_img_link']/img[@title='Blouse']")
	WebElement imgProduct;

	public boolean isProductDisplayed() 
	{
		// Define FluentWait with a timeout of 15 seconds and polling every 500 milliseconds
		Wait<WebDriver> wait =new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(15)) //Set a timeout of 15 seconds
				.pollingEvery(Duration.ofMillis(500)) //Poll every 500 milliseconds to check the condition.
				.ignoring(NoSuchElementException.class) //Ignore NoSuchElementException and StaleElementReferenceException.
				.ignoring(org.openqa.selenium.StaleElementReferenceException.class);
		try
		{
			driver.navigate().refresh();
			// Wait until the product image is visible
			wait.until(ExpectedConditions.visibilityOf(imgProduct));
			return imgProduct.isDisplayed();
			
		}
		 catch (Exception e) 
		{
		        // Log or handle the exception as needed
		        System.out.println("Exception occurred while checking product display: " + e.getMessage());
		        return false;
		}
	}

	public void clickOnProduct() 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(imgProduct));
		imgProduct.click();
	}
	
	
	

}
