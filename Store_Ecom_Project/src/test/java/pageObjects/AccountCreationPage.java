/**
 * 
 */
package pageObjects;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 */
public class AccountCreationPage extends BasePage{

	public AccountCreationPage(WebDriver driver) 
	{
		super(driver);
	}
	
//	@FindBy(xpath = "//h3[normalize-space()='Create an account']")
//	WebElement msgLoginPageCreateAccountHeading;
//	
//	@FindBy(xpath = "//input[@id='email_create']")
//	WebElement txtAccountRegEmail;
//	
//	@FindBy(xpath = "//span[normalize-space()='Create an account']")
//	WebElement btnCreateRegAccount;

	@FindBy(xpath = "//h1[normalize-space()='Create an account']")
	WebElement formAccountCreationPageTitle;
	
	@FindBy(xpath = "//div[@id='uniform-id_gender1']")
	WebElement radiobtnTitleMr;
	
	@FindBy(xpath = "//div[@id='uniform-id_gender2']")
	WebElement radiobtnTitleMrs;
	
	@FindBy(xpath = "//input[@id='customer_firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@id='customer_lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@id='passwd']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//select[@id='days']")
	WebElement drpdownDOBDay;
	
	@FindBy(xpath = "//select[@id='months']")
	WebElement drpDownDOBMonth;
	
	@FindBy(xpath= "//select[@id='years']")
	WebElement drpDownDOBYear;
	
	@FindBy(xpath = "//span[normalize-space()='Register']")
	WebElement btnRegister;
	
	@FindBy(xpath = "//div[@class='alert alert-danger']/ol")
	WebElement personalInformationErrorMessage;
	
	@FindBy(xpath = "//p[@class='alert alert-success']")
	WebElement msgAccountCreated;
	
	//================ Navigate to Add My First Address =====
	
	@FindBy(xpath = "//span[normalize-space()='Add my first address']")
	WebElement btnAddFirstAddress;
	
	
	
//	public boolean validateLoginPageAccountCreation()
//	{
//		return msgLoginPageCreateAccountHeading.isDisplayed();	
//	}
//	public void createAccount(String regEmail)
//	{
//		txtAccountRegEmail.sendKeys(regEmail);
//	}
//	public void clickCreateAccount()
//	{
//		btnCreateRegAccount.click();
//	}
	
	//=============== validate for account creation page navigation
	public boolean validateAccountCreationPage()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(formAccountCreationPageTitle)).isDisplayed();
	}
	
	//Entering personal info
	public void selectTitle(String title)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		if(title.equalsIgnoreCase("Mr"))
		{
			if(!radiobtnTitleMr.isSelected())
			{
				wait.until(ExpectedConditions.elementToBeClickable(radiobtnTitleMr)).click();				
			}
			
		}
		else if (title.equalsIgnoreCase("Mrs")) 
		{
			if(!radiobtnTitleMrs.isSelected())
			{
				wait.until(ExpectedConditions.elementToBeClickable(radiobtnTitleMrs)).click();
			}
			
		}
		else 
		{
			throw new IllegalArgumentException("Invalid title: " +title+ ".Expected 'Mr' or 'Mrs'." );
			//Invalid title: Dr. Expected 'Mr' or 'Mrs'. - OUTPUT 
		}
	}
	
	public void enterFirstName(String firstName) 
	{
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.visibilityOf(txtFirstName)).sendKeys(firstName);
	}

	public void enterLastName(String lastName) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(txtLastName)).sendKeys(lastName);
	}

	public void enterPassword(String password) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(txtPassword)).sendKeys(password);
	}
	public void selectDOB(String day, String month, String year)
	{
		//Select selectDay = new Select(drpdownDOBDay);
		//selectDay.selectByValue(day);
		
		//new Select(drpdownDOBDay).selectByValue(day);
		//new Select(drpDownDOBMonth).selectByValue(month);
		//new Select(drpDownDOBYear).selectByValue(year);
		
		//For flexibility  - Can use Overloading as well
		if (day != null) {
	        new Select(drpdownDOBDay).selectByValue(day);
	    }
	    if (month != null) {
	        new Select(drpDownDOBMonth).selectByValue(month);
	    }
	    if (year != null) {
	        new Select(drpDownDOBYear).selectByValue(year);
	    }
	}
	
	public void clickRegister()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnRegister)).click();
	}
	
	public boolean isErrorDisplayed()
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(personalInformationErrorMessage));
			return personalInformationErrorMessage.isDisplayed();
		}
		catch (NoSuchElementException e) 
		{
			return false;
		}
		catch (Exception e) 
		{
			// If the element is found but not visible or any other issue, also return false
			return false;
		}
		
	}
	public String getErrorMessageText()
	{
		try
		{
			return personalInformationErrorMessage.getText();
		}
		catch (NoSuchElementException e) 
		{
			return "No Error Message Found";
		}
	}
	
	
	public boolean validateAccountCreated() //Validating account created message display
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(msgAccountCreated)).isDisplayed();
	}
	
	public void clickAddMyFirstAddress()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnAddFirstAddress)).click();
	}
	
	

}
