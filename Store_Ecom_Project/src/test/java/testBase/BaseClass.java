/**
 * 
 */
package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * 
 */
public class BaseClass {
	
	public static WebDriver driver;
	public static Logger logger;
	public Properties p;
	
	@BeforeClass(groups = {"smoke", "sanity", "regression"})
	@Parameters({"browser"})
	public void setup(String br) throws IOException
	{
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default : System.out.println("Invalid browser..."); return; // It will exit form the execution
		}
		
		logger.info("===================== Browser launched successfully ===========================");	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		driver.get(p.getProperty("url"));
		
	}
	
	@AfterClass(groups = {"smoke", "sanity", "regression"})
	public void tearDown()
	{
		if(driver != null)
		{
			driver.quit();
			logger.info("====================== Browser closed successfully ==============================");
		}
	}
	
//	public String captureScreen (String tname) throws IOException 
//	{
//		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//		
//		TakesScreenshot takesScreenshot = (TakesScreenshot) driver; 
//		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
//		
//		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
//		File targetFile=new File(targetFilePath);
//		
//		sourceFile.renameTo(targetFile); //copy the source file into target file
//		
//		return targetFilePath; //returning where exactly screenshot is located
//	}
	
	public String captureScreen (String tname) throws IOException 
	{
		logger.info("Attempting to capture screenshot...");
		// Generate timestamp for the file name
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		// Capture screenshot
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver; 
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		// Define the target file path and name
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		// Copy the screenshot to the target location using Files.copy()
		//Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		sourceFile.renameTo(targetFile); //copy the source file into target file
		
		logger.info("Screenshot captured at: " + targetFilePath);
		
		return targetFilePath; //returning where exactly screenshot is located
	}

}
