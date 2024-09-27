/**
 * 
 */
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.*;
import testBase.BaseClass;
import utilities.DataProviders;

/**
 * Test case for successfully adding the product to the Cart.
 */
public class TC006_AddToCartPageTest extends BaseClass{
	
	LoadHomePage hm;
	SearchResultPage searchResult;
	AddToCartPage addCart;
	
	@Test(dataProvider = "searchProduct", dataProviderClass = DataProviders.class, groups = {"sanity", "regression"})
	public void verifyProductAddedToCart(String productName, String productSize, String productQuantity)
	{
		logger.info("*** Starting TC006_AddToCartPageTest Test ***");
		try
		{
			// Load home page and search for product
			hm = new LoadHomePage(driver);
			hm.enterSearchProduct(productName);
			hm.clickSearchProduct();
			logger.info("Product Search Completed: ");
						
			// Initialize search result page
			searchResult = new SearchResultPage(driver);
						
			// Verify product availability
			boolean productAvailStatus = searchResult.isProductDisplayed();
			Assert.assertTrue(productAvailStatus, "Product is Not available in the search result");
			logger.info("Product is available.");
						
			// Click on the product
			searchResult.clickOnProduct();
			logger.info("Clicked on the product for information.");
			
			//Initialize Add to Cart Page
			addCart = new AddToCartPage(driver);
			
			addCart.selectSize(productSize);
			logger.info("Selected Product Size: "+productSize);
			
			addCart.enterQuantity(productQuantity);
			 logger.info("Product Quantity Entered: " + productQuantity);
			
			addCart.clickAddToCart();
			logger.info("Clicked on Add to Cart");
			
			long startTime = System.currentTimeMillis();
			logger.info("Start time after product added to the cart: "+startTime);
			
			if(Integer.parseInt(productQuantity)<=0)
			{
				if(addCart.verifyInvalidQuantity())
				{
					logger.info("NULL QUANTITY:  Message Displayed ");
					logger.info("Entered Invalid Quantity: "+productQuantity);
					addCart.closeModalWindow();
					Assert.fail("Null Quantity - Invalid Quantity");
				}
				else if(addCart.verifyProductAddedtoCart())
				{
					logger.info("**BUG REPORT** Attempted to add product with invalid quantity: "+productQuantity);
					addCart.clickCloseAddCartModalWindow();
					addCart.emptyCart();
					Assert.fail("Test Failed: Product added to cart with Negative/INVALID quantity.");
				}
			}
			
			boolean addCartStatus = addCart.verifyProductAddedtoCart();
			Assert.assertTrue(addCartStatus, "Selected Product Not Added to the Cart");
			logger.info("Product Added to the Cart successfully");
			
			addCart.clickCloseAddCartModalWindow();
			logger.info("Closed the Modal Window of Add Cart");
			
			//addCart.clickProceedToCheckOut();
			//logger.info("Checked out from Add to Cart Page");
			
			if(!addCart.isProductInCart())
			{
				addCart.emptyCart();
				logger.info("Cart has been emptied successfully");
			}
			else 
			{
			    logger.info("Product is still in the cart; no need to empty.");
			}
			
			long endTime = System.currentTimeMillis();
			logger.info("Start time after product added to the cart: "+endTime);
			
			logger.info("Time taken to remove product from cart: " + (endTime - startTime) + " ms");
		}
		catch (Exception e) 
		{
			logger.error("Error occured while adding the product: " +e.getMessage());
			Assert.fail("Test failed due to exception: " +e.getMessage());
		}
		
		logger.info("*** Finishing TC006_AddToCartPageTest Test ***");
		
	}

}
