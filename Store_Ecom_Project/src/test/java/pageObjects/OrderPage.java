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
public class OrderPage extends BasePage{

	public OrderPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//li[@class='price']")
	WebElement unitPrice;
	
	@FindBy(xpath = "//td[@id='total_shipping']")
	WebElement totalShippingPrice;
	
	@FindBy(xpath = "//span[@id='total_price']")
	WebElement totalProductPrice;
	
	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']//span[contains(text(),'Proceed to checkout')]")
	WebElement btnProceedToCheckOut;
	
	public double getUnitPrice()
	{
		String unit_Price = unitPrice.getText().replace("$", "").replace(",", "").trim();
		return Double.parseDouble(unit_Price);
	}
	
	public double getShippingPrice()
	{
		String shipPrice =  totalShippingPrice.getText().replace("$", "").replace(",", "").trim();
		return Double.parseDouble(shipPrice);
	}
	
	public double getTotalPrice()
	{
		String totalPrice = totalProductPrice.getText().replace("$", "").replace(",", "").trim();
		return Double.parseDouble(totalPrice);
	}
	
	public void clickOnCheckOut()
	{
		btnProceedToCheckOut.click();
	}


}
