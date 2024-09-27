/**
 * 
 */
package pageObjects;


import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 */
public class AddToCartPage extends BasePage{

	public AddToCartPage(WebDriver driver) 
	{
		super(driver);
	}
	
	private static final Logger logger = LogManager.getLogger(AddToCartPage.class);

	@FindBy(xpath = "//input[@id='quantity_wanted']")
	WebElement txtQuantity;
	
	@FindBy(xpath = "//select[@name='group_1']")
	WebElement drpDwonSize;
	
	@FindBy(xpath = "//span[normalize-space()='Add to cart']")
	WebElement btnAddToCart;
	
	@FindBy(xpath = "//p[@class='fancybox-error']")
	WebElement msgInvalidQuantity;
	
	@FindBy(xpath = "//a[@title='Close']")
	WebElement btnNullQualityClose;
	
	@FindBy(xpath = "//span[@title='Close window']")
	WebElement btnCloseAddCartModalWindow;
	
	@FindBy(xpath = "//span[normalize-space()='Proceed to checkout']")
	WebElement btnProceedToCheckOut;
	
	@FindBy(xpath = "//h2[normalize-space()='Product successfully added to your shopping cart']")
	WebElement msgProductAddedToCart;
	
	@FindBy(xpath = "//a[@title='View my shopping cart']") 
	WebElement lnkViewCart;
	
	@FindBy(xpath = "//div[@class='shopping_cart']//span[contains(@class, 'ajax_cart_quantity') and number(text())=0]")
	WebElement cartProductNumber;
	
	//it will just check if the text is 0
//	@FindBy(xpath = "//div[@class='shopping_cart']//span[@class='ajax_cart_quantity' and text()='0']")
//	WebElement cartProductNumber;
	
	@FindBy(xpath = "//a[@class='ajax_cart_block_remove_link']")
	WebElement btnRemoveProductCart;
	
	
	public void enterQuantity(String quantity)
	{		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(txtQuantity));
		txtQuantity.clear();
		txtQuantity.sendKeys(quantity);
	}
	
	public void selectSize(String size)
	{
		Select selSize = new Select(drpDwonSize);
		selSize.selectByVisibleText(size);
	}
	
	public void clickAddToCart()
	{
		btnAddToCart.click();
	}
	
	public void clickCloseAddCartModalWindow()
	{
		btnCloseAddCartModalWindow.click();
	}
	
	public boolean verifyInvalidQuantity()
	{
		try {
	        // Wait for the "Invalid Quantity" message to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        WebElement waitInvalidQuantity = wait.until(ExpectedConditions.visibilityOf(msgInvalidQuantity));
	        
	        // Check if the element is displayed
	        return waitInvalidQuantity.isDisplayed();
	    } 
	    catch (Exception e) {
	        // Log error if the message did not display or if there was any exception
	        logger.error("Null Quantity Message Did not display: " + e.getMessage());
	        return false;
	    }
	}
	
	public void closeModalWindow()
	{
		btnNullQualityClose.click();
	}
	
	public void clickProceedToCheckOut()
	{
		//Solution 1
		btnProceedToCheckOut.click();
		
		//Solution 2
		//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnProceedToCheckOut).click());
		
		//solution 3
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].click();", btnProceedToCheckOut);
		
		//sol 4
		//Actions act = new Actions(driver);
		//act.moveToElement(btnProceedToCheckOut).click().perform();
				
		//sol 5
		//btnProceedToCheckOut.sendKeys(Keys.RETURN);
		
		//sol 6
		//btnProceedToCheckOut.submit();
		
	}

	// Method to click the 'Proceed to Checkout' button with JavaScript Executor as fallback
//    public void clickProceedToCheckOut() {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.elementToBeClickable(btnProceedToCheckOut)).click();
//        } catch (Exception e) {
//            // Fallback to JavaScriptExecutor if normal click doesn't work
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].click();", btnProceedToCheckOut);
//        }
//    }
	
	public boolean verifyProductAddedtoCart() 
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement ele =  wait.until(ExpectedConditions.visibilityOf(msgProductAddedToCart));
			return ele.isDisplayed();
		}
		catch (NoSuchElementException e) 
		{
			return false;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean isProductInCart()
	{
		try
		{
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//			WebElement ele =  wait.until(ExpectedConditions.visibilityOf(cartProductNumber));
//			return ele.isDisplayed();
			
			return cartProductNumber.isDisplayed();
		}
		catch (NoSuchElementException e) 
		{
			return false;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public void emptyCart() 
	{
	    try
	    {       
	        // Initialize Actions class for mouse over operations
	        Actions act = new Actions(driver);
	        
	        // Perform mouse hover over the "Add to Cart" button
	        act.moveToElement(lnkViewCart).perform();
	        
	        // Wait for the remove button to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.elementToBeClickable(btnRemoveProductCart)).click();
	        
	        // Move to the remove button and click it
	       // act.moveToElement(btnRemoveProductCart).click().perform();
	        logger.info("Cart emptied successfully.");
	        //driver.navigate().refresh();
	        
	        // Wait for the cart to update (you may need to wait for an element that reflects the updated cart state)
	       //wait.until(ExpectedConditions.visibilityOf(cartProductNumber));
	        
	     // Wait for the cart to update (use a condition that confirms the cart is empty)
	        //wait.until(ExpectedConditions.textToBePresentInElement(cartProductNumber, "0"));
	        
	        // Check if the cart is empty
//	        if (Integer.parseInt(cartProductNumber.getText()) == 0) 
//	        {
//	            logger.info("Cart emptied successfully.");
//	        } 
//	        else 
//	        {
//	            logger.warn("Cart was not emptied. Current quantity: " + cartProductNumber.getText());
//	        }
	    }
	    catch (Exception e) 
	    {
	    	logger.warn("Cart was not emptied. Current quantity: " + cartProductNumber.getText());
	        logger.error("Failed to empty the cart: " + e.getMessage());
	    }
	}
	

	
	
	
}
