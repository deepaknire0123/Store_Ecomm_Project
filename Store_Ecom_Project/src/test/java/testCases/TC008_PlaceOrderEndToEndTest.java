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
import utilities.DataProviders;

/**
 * Test case for end-to-end product ordering flow.
 * The user should be able to search, add a product, and complete an order.
 */
public class TC008_PlaceOrderEndToEndTest extends BaseClass{
	
	LoadHomePage hm;
	SearchResultPage searchResult;
	AddToCartPage addCart;
	OrderPage orderPage;
	LoginPage lp;
	AddressPage addressPage;
	ShippingPage shippingPage;
	PaymentPage paymentPage;
	OrderSummeryPage orderSummery;
	OrderConfirmationPage orderConfimation;
	OrderHistoryPage orderHistory;
	
	
	@BeforeMethod(groups = {"regression"})
	public void initialize()
	{
		
		logger.info("*****************Initializing page objects in @BeforeMethod***********************");
		hm = new LoadHomePage(driver);
		searchResult = new SearchResultPage(driver);
		addCart = new AddToCartPage(driver);
		orderPage = new OrderPage(driver);
		lp = new LoginPage(driver);
		addressPage = new AddressPage(driver);
		shippingPage = new ShippingPage(driver);
		paymentPage = new PaymentPage(driver);
		orderSummery = new OrderSummeryPage(driver);
		orderConfimation = new OrderConfirmationPage(driver);
		orderHistory = new OrderHistoryPage(driver);
		logger.info("Page objects initialized successfully");
	}
	
	@Test(groups = "regression", dataProvider = "placeOrderProductDetail", dataProviderClass = DataProviders.class)
	public void placeOrderEndToEndTest(String productName, String productSize, String productQuantity, String email,String password)
	{
		logger.info("******** Starting TC008_EndToEndTest *********");
		try
		{
			// Search, select product, and validate price in one method
			searchAndAddProductToCart(productName, productSize, productQuantity);
			
			// Proceed to checkout
			orderPage.clickOnCheckOut();
			logger.info("Proceed to checkout after confirming order to cart ");
			
			//Enter the login details
			lp.loginEmail(email);
			lp.loginPassword(password);
			lp.clickSignIn();
			if(lp.isErrorDisplayed())
			{
				String errorMessageLoginPage = lp.getErrorMessageText();
				logger.error("Error on Login Page: " +errorMessageLoginPage);
				
				if(!addCart.isProductInCart())
				{
					addCart.emptyCart();
					logger.info("Cart has been emptied successfully");
				}
				else 
				{
				    logger.info("Product is still in the cart; no need to empty.");
				}
				Assert.fail("Login Page Error: "+errorMessageLoginPage);
			}
			else 
			{
	            logger.info("No error, continuing to the login page.");
			}
			logger.info("Logged in Successfully");
			
			//Navigating to address page
			addressPage.clickProceedToCheckOut();
			logger.info("Proceeding from Address page to Shipping.");
			
			//Navigating to shipping page 
			shippingPage.clickTermsCheckBox();
			logger.info("Agreed to the terms and conditions of the shipping ");
			shippingPage.clickProceedToCheckOut();
			logger.info("Proceeding from Shipping page to Payment.");
			
			//Navigating to payment page
			paymentPage.clickOnPaymentMethod();
			logger.info("Selected payment method");
			
			//Navigating to Order summary
			orderSummery.clickConfirmOrder();
			logger.info("Order confirmed from order summery");
			
			//Navigating to Order confirmation Page
			orderConfimation.validateOrderConfirmation();
			logger.info("Product order validated successfully.");
			
			//View Order History
			orderConfimation.clickViewOrderHistory();
			logger.info("Order History Displayed");
			
			//Navigate back to Home Page
			orderHistory.clickHome();
			logger.info("Navigated to Home Page");
			
			//Sign Out
			addressPage.clickSignOut();
			logger.info("Logged Out of the Account");
						
		}
		catch (Exception e) {
			logger.error("Error occurred during the product order: "+e.getMessage());
			Assert.fail("Test failed due to an exception: " +e.getMessage());
		}
		logger.info("********* Finishing TC008_EndToEndTest test **********");
	}
	
	// Combine search, select, and price validation in one method
		private void searchAndAddProductToCart(String productName, String productSize, String productQuantity) throws InterruptedException
		{
			// Search for product
			logger.info("Searching for Product: "+productName);
			hm.enterSearchProduct(productName);		
			hm.clickSearchProduct();
			logger.info("Product search completed");
			
			//verify product availability
			Assert.assertTrue(searchResult.isProductDisplayed(), "Product Not Available in the Search Result");
			logger.info("Product Found: " +productName);
			
			searchResult.clickOnProduct();
			logger.info("Product selected from Search Result Page: " +productName);
			
			//Select the size and Quantity
			logger.info("Selecting size: " +productSize+ " and quantity: " +productQuantity);
			addCart.selectSize(productSize);
			addCart.enterQuantity(productQuantity);
			
			//Add to Cart
			addCart.clickAddToCart();
			logger.info("Product added to cart: " + productName);
			
			if(Integer.parseInt(productQuantity)<=0)
			{
				if(addCart.verifyInvalidQuantity())
				{
					logger.info("NULL QUANTITY:  Message Displayed ");
					logger.info("Entered Invalid Quantity: "+productQuantity);
					addCart.closeModalWindow();
					Thread.sleep(2000);
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
			
			// Validate the product was added successfully
			Assert.assertTrue(addCart.verifyProductAddedtoCart(), "Product was not Successfully Added to the Cart");
			logger.info("Product successfully Added to the Cart");
			
			addCart.clickProceedToCheckOut();
			logger.info("Clicked on Proceed to checkout from the Add to Cart ");
			
			// Validate total price
			validateTotalPrice(productQuantity);
		}
		
		//method is now called directly within searchAndSelectProduct
		public void validateTotalPrice(String ProductQuantity)
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
	        
			//double totalExpectedPrice = (unitPrice*(Double.parseDouble(ProductQuantity))) + shippingPrice;
			logger.info("Expected Total price: "+expectedTotalPrice.doubleValue());
			
			// Verify the actual price matches the expected price
			Assert.assertEquals(totalActualPrice, expectedTotalPrice.doubleValue(), "Product total price does not match the expected value");
			logger.info("Total Price successfully validated");
		}

}
