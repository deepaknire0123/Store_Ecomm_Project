/**
 * 
 */
package pageObjects;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page object class for Login/Authentication.
 * Also includes creating a new account.
 */
public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) // Constructors are not inherited - Hence explicit constructor written
	{
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement txtLoginEmail;
	
	@FindBy(xpath = "//input[@id='passwd']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//span[normalize-space()='Sign in']")
	WebElement btnSignIn;
	
	@FindBy(xpath = "//input[@id='email_create']")
	WebElement txtRegistrationEmail;
	
	@FindBy(xpath = "//span[normalize-space()='Create an account']")
	WebElement btnCreateAccount;
	
	public void loginEmail(String email)
	{
		txtLoginEmail.sendKeys(email);
	}
	public void loginPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	public void clickSignIn()
	{
		btnSignIn.click();
	}
	
	By errorMessageLocator = By.xpath("//div[@class='alert alert-danger']/ol");
	
	
	public void registerEmail(String regemail)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(txtRegistrationEmail));
		txtRegistrationEmail.sendKeys(regemail);
	}
	public void clickCreateAnAccount()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(btnCreateAccount));
		btnCreateAccount.click();
	}
	
	public boolean isErrorDisplayed() 
	{
        try {
            // Check if the error message element exists on the page without waiting for visibility
            if (!driver.findElements(errorMessageLocator).isEmpty()) 
            {
                // If the element exists, check if it's visible
                return driver.findElement(errorMessageLocator).isDisplayed();
            }
            // If the element doesn't exist, return false
            return false;
        } 
        catch (NoSuchElementException e) 
        {
            return false;  // No error message means everything is fine
        }
        catch (Exception e) 
        {
			return false;
		}
    }

    public String getErrorMessageText() 
    {
    	try 
    	{
            return driver.findElement(errorMessageLocator).getText();
        } 
    	catch (NoSuchElementException e) 
    	{
            return "No error message found";
        }
    }

}
