package commomfunctions;


import java.io.FileInputStream;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class functionLibrary {
	public static Properties conpro;
	public static WebDriver driver ;
	
public static WebDriver startBrowser() throws Throwable
{
	conpro = new Properties();
	conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
	
	if (conpro.getProperty("Browser").equalsIgnoreCase("chrome")) 
	{
		driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    
	}
	else if (conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
		
	}
	else
	{
		Reporter.log("Browser value is not matching" , true );
	}
	return driver;
}
	

// Method for Launching Url 

public static void openUrl() 
{
	driver.get(conpro.getProperty("Url"));
}

// Method for waitForElement

public static void waitForWebelement(String LocatorType, String LocatorValue , String TestData )
{
	WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(TestData)));
	
	if(LocatorType.equalsIgnoreCase("xpath")) 
	{
		mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
	}
	if(LocatorType.equalsIgnoreCase("id")) 
	{
		mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
	}
	if(LocatorType.equalsIgnoreCase("name")) 
	{
		mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
	}
	
}

public static void typeAction(String Locatortype, String LocatorValue , String TestData )
{
	
	if(Locatortype.equalsIgnoreCase("xpath")) 
	{
		driver.findElement(By.xpath(LocatorValue)).clear();
		driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);
	}
	if(Locatortype.equalsIgnoreCase("id")) 
	{
		driver.findElement(By.xpath(LocatorValue)).clear();
		driver.findElement(By.id(LocatorValue)).sendKeys(TestData);
	}
	if(Locatortype.equalsIgnoreCase("name")) 
	{
		driver.findElement(By.name(LocatorValue)).clear();
		driver.findElement(By.name(LocatorValue)).sendKeys(TestData);
	}
}

public static void clickAction(String Locatortype, String LocatorValue) 
{
	
	if(Locatortype.equalsIgnoreCase("xpath")) 
	{
		driver.findElement(By.xpath(LocatorValue)).click();
	}
	if(Locatortype.equalsIgnoreCase("name")) 
	{
		driver.findElement(By.name(LocatorValue)).click();
	}
	if(Locatortype.equalsIgnoreCase("id")) 
	{
		driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
		
	}
}

public static void validateTitle(String Exp_title) 
{
	String Actual_title = driver.getTitle();
	
	try {
	Assert.assertEquals(Exp_title, Actual_title , "Title is not matching");
	}catch (AssertionError a) 
	{
		System.out.println(a.getMessage());
	}
	
}

public static void closeBrowser() 
{
	driver.quit();
}

public static String generateDate()
{ 
	Date date = new Date(0);
	
	DateFormat df = new SimpleDateFormat("YYYY_MM_DD");
	return df.format(date);
	
}


























	
}
