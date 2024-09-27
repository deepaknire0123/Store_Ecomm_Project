/**
 * 
 */
package testCases;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.*;
import testBase.BaseClass;

/**
 * Order page test case - Validating the price of the product based on quantity and shipping price.
 */
public class TC007_OrderPageTest extends BaseClass{
	
	LoadHomePage hm;
	SearchResultPage searchResult;
	AddToCartPage addCart;
	OrderPage orderPage;
	
	@BeforeMethod(groups = {"regression"}) //alwaysRun = true 
	public void initialize() {
		hm = new LoadHomePage(driver);
		searchResult = new SearchResultPage(driver);
		addCart = new AddToCartPage(driver);
		orderPage = new OrderPage(driver);
	}
	
	@Test(groups = {"regression"})
	public void verifyTotalPrice() 
	{
		logger.info("*** Starting TC007_OrderPageTest test ***");
		try {
			// Search, select product, and validate price in one method
			searchAndSelectProduct("Blouse", "L", "2");
			
			// Proceed to checkout
			orderPage.clickOnCheckOut();
			logger.info("Clicked on Proceed to checkout");
		} 
		catch (Exception e) 
		{
			logger.error("Error occurred during price calculation: " + e.getMessage());
			Assert.fail("Test failed due to an exception: " + e.getMessage());
		}
		
		logger.info("*** Finishing TC007_OrderPageTest test ****");
	}
	
	// Combine search, select, and price validation in one method
	private void searchAndSelectProduct(String productName, String ProductSize, String ProductQuantity) {
		// Search for product
		logger.info("Searching for Product: " + productName);
		hm.enterSearchProduct(productName);		
		hm.clickSearchProduct();
		logger.info("Clicked on product search button");
		
		// Verify product availability
		Assert.assertTrue(searchResult.isProductDisplayed(), "Product Not Available in the Search Result");
		logger.info("Product available: " + productName);
		
		searchResult.clickOnProduct();
		logger.info("Product clicked for more information: " + productName);
		
		// Select the size and quantity
		logger.info("Selecting size: " + ProductSize + " and quantity: " + ProductQuantity);
		addCart.selectSize(ProductSize);
		addCart.enterQuantity(ProductQuantity);
		
		// Add to Cart
		addCart.clickAddToCart();
		logger.info("Product Added to Cart");
		
		Assert.assertTrue(addCart.verifyProductAddedtoCart(), "Product was not Successfully Added to the Cart");
		logger.info("Product successfully Added to the Cart: " + productName);
						
		addCart.clickProceedToCheckOut();
		logger.info("Clicked on Proceed to checkout from the Add to Cart ");
		
		// Validate total price
		validateTotalPrice(ProductQuantity);
	}
	
	// This method is now called directly within searchAndSelectProduct
	private void validateTotalPrice(String ProductQuantity) 
	{
		// Get unit price, shipping price, and total actual price
		double unitPrice = orderPage.getUnitPrice();
		double shippingPrice = orderPage.getShippingPrice();
		double totalActualPrice = orderPage.getTotalPrice();
				
        logger.info("Unit price: " + unitPrice);
        logger.info("Shipping price: " + shippingPrice);
        logger.info("Total actual price: " + totalActualPrice);
		
        // Calculate expected total price
        BigDecimal quantity = new BigDecimal(ProductQuantity);
        BigDecimal expectedTotalPrice = BigDecimal.valueOf(unitPrice).multiply(quantity).add(BigDecimal.valueOf(shippingPrice));
        
		logger.info("Expected Total price: " + expectedTotalPrice.doubleValue());
		
		// Verify the actual price matches the expected price
		Assert.assertEquals(totalActualPrice, expectedTotalPrice.doubleValue(), "Product total price does not match the expected value");
		logger.info("Total Price successfully validated");
	}
	

}
