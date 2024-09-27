package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

/*
 * This class implements actions upon test case status
 */

public class ExtentReportManager implements ITestListener
{
	
	public ExtentSparkReporter sparkReporter; //UI of the report
	public ExtentReports extent; // Configuration info on the report
	public ExtentTest test; // creating entries in the report and updating the status of the test methods
	String reportName;	
	
	
	
	public void onStart(ITestContext testContext)// testContext- Method that got executed
	{		
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	    Date dt = new Date(); //java.util.Date; - to generate the date
	    String currentdatetimestamp = df.format(dt);// - pass the date object
	    */
		
		//The above steps in single line
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		reportName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/"+reportName); // specify location of the report
		
		sparkReporter.config().setDocumentTitle("E-Commerce Store Automation Report");
		sparkReporter.config().setReportName("Store Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Online E-comm Store"); 
		extent.setSystemInfo("Module", "Admin"); 
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name")); //dynamic data
		extent.setSystemInfo("Environemnt", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os"); //Capture the parameters from XML file -dynamically
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		
		//captures the groups in the xml and diplay in report
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups(); //Getting all the groups from the xml in list
		if(!includedGroups.isEmpty()) 
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}	
	}
	
	
	public void onTestSuccess(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName()); //Creating the new entry - getting the class name 
		test.assignCategory(result.getMethod().getGroups()); // to display test method and groups in report 
		test.log(Status.PASS, result.getName()+" got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());//from the result we are getting the class from class getting the name
		test.assignCategory(result.getMethod().getGroups());// from the result getting the test methd - which category the test belongs to
		
		//Attaching the error message
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		//Attaching the screenshot of the message
		try 
		{
			//Create object of Base class since its not static - called the caturescree() - pass the name of the method failed
			String imgPath = new BaseClass().captureScreen(result.getName()); //location of the image
			test.addScreenCaptureFromPath(imgPath); //test - represting the report
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace(); //Display the exception method
		}	


	}
	
	public void onTestSkipped(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());   
	}
	
	public void onFinish (ITestContext testContext) 
	{
		extent.flush(); // it will consolidate all the info and generate the report
		
		//Extra stmts - we need to open the report manually, Hence write the code as soon as script executed it opens the report
		
		String pathOfExtentReport = System.getProperty("user.dir")+ "/reports/"+reportName; //path of the report
		File extentReport = new File(pathOfExtentReport); //extent report file
		
		try {
		Desktop.getDesktop().browse (extentReport.toURI()); //Opens the browser automatically
		} catch (IOException e) { //if does not it will throw an exception
			e.printStackTrace();
		}
	}
	
	

}