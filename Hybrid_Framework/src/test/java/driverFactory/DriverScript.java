package driverFactory;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import commomfunctions.functionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript {

	public static WebDriverWait driver;
	String inputpath ="./FileInput/DataEngine.xlsx";
	String outputpath = "./FileOutput/HybridResults.xlsx";
	ExtentReports report;
	ExtentTest logger ; 

	@Test

	public void startTest() throws Throwable 
	{

		String moduleStatus = "";

		ExcelFileUtil xl = new ExcelFileUtil(inputpath);

		String Testcases = "MasterTestCase";

		for(int i=1;i<=xl.RowCount(Testcases); i++) 
		{
			if(xl.getcellData(Testcases, i, 2).equalsIgnoreCase("y")) 
			{
				String TCModule = xl.getcellData(Testcases, i, 1);

				for(int j=1; j<=xl.RowCount(TCModule); j++) 
				{
//					System.out.println("pass the test");
					String Description = xl.getcellData(TCModule, j, 0);
					String Object_Type = xl.getcellData(TCModule, j, 1);
					String Locator_Type = xl.getcellData(TCModule, j, 2);
					String Locator_Value = xl.getcellData(TCModule, j, 3);
		              String Test_Data = xl.getcellData(TCModule, j, 4);
					try 
					{
						if(Object_Type.equalsIgnoreCase("startBrowser")) 
						{
							functionLibrary.startBrowser();
						}
						if(Object_Type.equalsIgnoreCase("openUrl")) 
						{
							functionLibrary.openUrl();
						}
						if(Object_Type.equalsIgnoreCase("waitForElement")) 
						{
							functionLibrary.waitForWebelement(Locator_Type, Locator_Value, Test_Data);
						}
						if(Object_Type.equalsIgnoreCase("typeAction")) 
						{
							functionLibrary.typeAction(Locator_Type, Locator_Value, Test_Data);
						}
						if(Object_Type.equalsIgnoreCase("clickAction")) 
						{
							functionLibrary.clickAction(Locator_Value, Test_Data);
						}
						if(Object_Type.equalsIgnoreCase("validateTitle")) 
						{
							functionLibrary.validateTitle(Test_Data);
						}
						if(Object_Type.equalsIgnoreCase("closeBrowser")) 
						{
							functionLibrary.closeBrowser();
						}
						xl.setCellData(TCModule, j, 5, "pass", outputpath);

						moduleStatus = "True";

					}
					catch (Exception e) 
					{
						System.out.println(e.getMessage());
						xl.setCellData(TCModule, j, 5, "Fail", outputpath);
						moduleStatus = "False";
					}
					if(moduleStatus.equalsIgnoreCase("True")) 
					{
						xl.setCellData(Testcases, i, 3, "Pass", outputpath);
					}
					else
					{

						xl.setCellData(Testcases, i, 3, "Fail", outputpath);

					}

				}

			}
			else
			{
				xl.setCellData(Testcases, i, 3, "Blocked", outputpath);

			}
		}

	}






























}
