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
 * Test case contains searching, verification, and clicking on the product.
 */
public class TC005_SearchResultPageTest extends BaseClass{
	LoadHomePage hm;
    SearchResultPage searchResult;
	
	@Test(groups = {"smoke"})
    public void verifyProductAvailability() 
	{
        logger.info("*** Starting TC005_SearchResultPageTest ***");
        
        try {
            // Load home page and search for product
            hm = new LoadHomePage(driver);
            hm.enterSearchProduct("Blouse"); // Consider renaming this to 'searchProduct' (typo)
            hm.clickSearchProduct();
            logger.info("Product Search Completed: ");
            
            // Initialize search result page
            searchResult = new SearchResultPage(driver);
            
            // Verify product availability
            boolean productAvailStatus = searchResult.isProductDisplayed();
            Assert.assertTrue(productAvailStatus, "Product is not available in the search result.");
            logger.info("Product is available.");
            
            // Click on the product
            searchResult.clickOnProduct();
            logger.info("Clicked on the product for information.");
        } 
        catch (Exception e) 
        {
            logger.error("Error occurred during product search: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
        
        logger.info("*** Finished TC005_SearchResultPageTest ***");
    }

}
