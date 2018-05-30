package Insightsc3m.Tests;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
 
public class ContentTesting 
{
	public static WebDriver driver;
	// Providing insights login credentials
	public static String username="nagasumm"; //User Name
	public static String password="Altria@543"; //Password
	public static String accountnumber="046683"; //Account Number	
	
	//Click events
	public static void login(String username, String password, String accountnumber) throws InterruptedException
	{
		  driver.get("https://qa1.insightsc3m.com/");//Application URL
		  driver.findElement(By.id("UserIdentifierClaim")).sendKeys(username);//Passing UserName Id
		  Thread.sleep(3000);
		  driver.findElement(By.id("UserPasswordClaim")).sendKeys(password);//Passing Password Id
		  Thread.sleep(3000);
		  driver.findElement(By.id("lnklogin")).click();// Login Click Event
		  driver.findElement(By.id("txtSearch")).sendKeys(accountnumber);//Entering Account Number
		  driver.findElement(By.id("btnGo")).click();//Go icon click event
	 }
	
	public static void NavigatetoReportCenter() {
		driver.findElement(By.xpath("//*[@id='btnReportsMaster']")).click();// click on Reports Master in left channel
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) throws IndexOutOfBoundsException, BiffException, IOException, RowsExceededException, WriteException, InterruptedException 
	{
		ExcelReadWrite.readPath = "D:\\Selenium Tool\\Excel Inputs\\Content.xls"; //Read Path
		ExcelReadWrite.writePath = "D:\\Selenium Tool\\Excel Outputs\\ContentResult.xls"; //Write Path
		
		ExcelReadWrite.CopyContentToOutputFile();
		
		System. setProperty("webdriver.chrome.driver", "D:\\Selenium Tool\\chromedriver.exe"); //Run the application in Chrome browser
		driver = new ChromeDriver();
		driver.manage().window().maximize(); //Maximize the browser
		
		login(username, password, accountnumber); //login and account selection
		Thread.sleep(10000);
		
		NavigatetoReportCenter();		
		
		Sheet currentSheet = ExcelReadWrite.GetSheet();
		String idSelector = "";                                              
		
		for(int row = 1; row < currentSheet.getRows(); row++) 
		{
			ExcelReadWrite.LastRowIndex++;
			 String module = ExcelReadWrite.GetCellData(currentSheet, row, 0);			
			 
			 switch(module) {
			 
			 case "My Reporting Center Header" :
				 idSelector = "//*[@id='div-RWS_HDR-2']/h1";
				 break;
				 
			 case "My Reporting Center - Payments Header" :
				 idSelector = "//*[@id='divReport_RC001']/article/div[2]/h5";
				 break;
				 
			 case "My Reporting Center - Payments Content" :
				 idSelector = "//*[@id='divReport_RC001']/article/div[2]/p[1]";
				 break;
				 
			 case "My Reporting Center - Payments Link" :
				 idSelector = "//*[@id='divReport_RC001']/article/div[2]/p[2]";
				 break;
				 
			 case "My Reporting Center - Price Promotions Header" :
				 idSelector = "//*[@id='divReport_RC002']/article/div[2]/h5";
				 break;
				 
			 case "My Reporting Center - Price Promotions Content" :
				 idSelector = "//*[@id='divReport_RC002']/article/div[2]/p[1]";
				 break;
				 
			 case "My Reporting Center - Price Promotions Link" :
				 idSelector = "//*[@id='divReport_RC002']/article/div[2]/p[2]/a";
				 break;
				 
			 case "My Reporting Center - Product Promotions Header" :
				 idSelector = "//*[@id='divReport_RC003']/article/div[2]/h5";
				 break;
				 
			 case "My Reporting Center - Product Promotions Content" :
				 idSelector = "//*[@id='divReport_RC003']/article/div[2]/p[1]";
				 break;
				 
			 case "My Reporting Center - Product Promotions Link" :
				 idSelector = "//*[@id='divReport_RC003']/article/div[2]/p[2]/a";
				 break;
				 
			 case "My Reporting Center - Mobile Coupon Redemption Header" :
				 idSelector = "//*[@id='divReport_RC015']/article/div[2]/h5";
				 break;
				 
			 case "My Reporting Center - Mobile Coupon Redemption Content" :
				 idSelector = "//*[@id='divReport_RC015']/article/div[2]/p[1]";
				 break;
				 
			 case "My Reporting Center - Mobile Coupon Redemption Link" :
				 idSelector = "//*[@id='divReport_RC015']/article/div[2]/p[2]/a";
				 break;
				 
			 case "My Reporting Center - Special Return Programs Header" :
				 idSelector = "//*[@id='divReport_RC008']/article/div[2]/h5";
				 break;
				 
			 case "My Reporting Center - Special Return Programs Content" :
				 idSelector = "//*[@id='divReport_RC008']/article/div[2]/p[1]";
				 break;
				 
			 case "My Reporting Center - Special Return Programs Link" :
				 idSelector = "//*[@id='divReport_RC008']/article/div[2]/p[2]";
				 break;
				 
			 case "My Reporting Center - Initiative Summary Header" :
				 idSelector = "//*[@id='divReport_RC005']/article/div[2]/h5";
				 break;
				 
			 case "My Reporting Center - Initiative Summary Content" :
				 idSelector = "//*[@id='divReport_RC005']/article/div[2]/p[1]";
				 break;
				 
			 case "My Reporting Center - Initiative Summary Link" :
				 idSelector = "//*[@id='divReport_RC005']/article/div[2]/p[2]/a";
				 break;
				 
			 case "My Reporting Center - Pricing Opportunities Header" :
				 idSelector = "//*[@id='divReport_RC012']/article/div[2]/h5";
				 break;
				 
			 case "My Reporting Center - Pricing Opportunities Content" :
				 idSelector = "//*[@id='divReport_RC012']/article/div[2]/p[1]";
				 break;
				 
			 case "My Reporting Center - Pricing Opportunities Link" :
				 idSelector = "//*[@id='divReport_RC012']/article/div[2]/p[2]";
				 break;
				 
			 case "My Reporting Center - Retail Elections Header" :
				 idSelector = "//*[@id='divReport_RC013']/article/div[2]/h5";
				 break;
				 
			 case "My Reporting Center - Retail Elections Content" :
				 idSelector = "//*[@id='divReport_RC013']/article/div[2]/p[1]";
				 break;
				 
			 case "My Reporting Center - Retail Elections Link" :
				 idSelector = "//*[@id='divReport_RC013']/article/div[2]/p[2]/a";
				 break;
				 
			 case "My Reporting Center - Wholesale Elections Header" :
				 idSelector = "//*[@id='divReport_RC016']/article/div[2]/h5";
				 break;
				 
			 case "My Reporting Center - Wholesale Elections Content" :
				 idSelector = "//*[@id='divReport_RC016']/article/div[2]/p[1]";
				 break;
				 
			 case "My Reporting Center - Wholesale Elections Link" :
				 idSelector = "//*[@id='divReport_RC016']/article/div[2]/p[2]";
				 break;
				 
			 case "My Reporting Center - Scan Data Submissions Header" :
				 idSelector = "//*[@id='divReport_RC017']/article/div[2]/h5";
				 break;
				 
			 case "My Reporting Center - Scan Data Submissions Content" :
				 idSelector = "//*[@id='divReport_RC017']/article/div[2]/p[1]";
				 break;
				 
			 case "My Reporting Center - Scan Data Submissions Link" :
				 idSelector = "//*[@id='divReport_RC017']/article/div[2]/p[2]/a";
				 break;
				 
			 case "My Reporting Center - PCMA Maintenance Qualifier Report Header" :
				 idSelector = "//*[@id='divReport_RC020']/article/div[2]/h5";
				 break;
				 
			 case "My Reporting Center - PCMA Maintenance Qualifier Report Content" :
				 idSelector = "//*[@id='divReport_RC020']/article/div[2]/p[1]";
				 break;
				 
			 case "My Reporting Center - PCMA Maintenance Qualifier Report Link" :
				 idSelector = "//*[@id='divReport_RC020']/article/div[2]/p[2]/a";
				 break;
			 }			 
			 
			 String ActualMyReportingCenter=driver.findElement(By.xpath(idSelector)).getText();			 
			 ExcelReadWrite.CheckContentText(currentSheet, row, idSelector, ActualMyReportingCenter);
		}
		
		ExcelReadWrite.PieChartReport();		
		ExcelReadWrite.WriteExcel();
		driver.close();
		ExcelReadWrite.OpenOutputExcel();
	}
	
 }
	
	
	



	

