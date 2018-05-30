package Insightsc3m.Tests;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Insightsc3m.Tests.ExcelReadWrite;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InsightsLogin {
	
	public String Testcase;
	public WritableSheet writablesh;
	public WritableWorkbook workbookcopy;
	public static String rpath="D:\\Selenium Tool\\Excel Inputs\\Login.xls";
	public static String wpath="D:\\Selenium Tool\\Excel Outputs\\Login.xls";
	public static WebDriver driver;
	public static String msg1;
	
	
	
	public static void main(String[] args) throws IndexOutOfBoundsException, BiffException, IOException, RowsExceededException, WriteException, InterruptedException {
		
		ExcelReadWrite.readPath = rpath; //Read Path
		ExcelReadWrite.writePath = wpath; //Write Path
		ExcelReadWrite.CopyContentToOutputFile();
		Sheet s=ExcelReadWrite.GetSheet();
	
		System. setProperty("webdriver.chrome.driver", "D:\\Selenium Tool\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		ExcelReadWrite.LastRowIndex=1;
		ExcelReadWrite.FailValue=0;
		ExcelReadWrite.NotAvailableValue=0;
		
		for(int row=1;row<s.getRows();row++)
		{
			ExcelReadWrite.LastRowIndex++;
			String username=ExcelReadWrite.GetCellData(s,row,0);	
			String pwd=ExcelReadWrite.GetCellData(s,row,1);
			String accountnumber=ExcelReadWrite.GetCellData(s,row,2);
	
				 
			 InsightsLogin login=new InsightsLogin();
			 String message=login.login(username, pwd);
				 
			 
			 if(message.equalsIgnoreCase("Log Out")) {
					 
					 System.out.println("The Row "+row);
					 ExcelReadWrite.WriteCellData(ExcelReadWrite.writableSheet, row, 3, "PASS", 4);
					 ExcelReadWrite.WriteCellData(ExcelReadWrite.writableSheet, row, 4, msg1, 4);
					 
					 ExcelReadWrite.PassValue++;
					 
					 String accountMessage = login.AccountNumber(accountnumber);
					 Thread.sleep(4000);
					
					 ExcelReadWrite.WriteCellData(ExcelReadWrite.writableSheet, row, 5, accountMessage);
					 Thread.sleep(4000);
					 String loguoutmsg=driver.findElement(By.xpath("//*[@id='liLogout']/a")).getText();
					 System.out.println(" log out messge "+loguoutmsg);
					 Thread.sleep(4000);
					
					 
					 JavascriptExecutor jse = (JavascriptExecutor)driver;
					 jse.executeScript("window.scrollBy(0,700);");
					 jse.executeScript("window.scrollBy(0,-300);");
					 
					 driver.findElement(By.xpath("//*[@id='liLogout']/a/i")).click();
		
					 Thread.sleep(3000);
				  	 driver.findElement(By.xpath("//*[@id='btnLogoutOk']")).click();
					 
				 
				 }
				 else {
					 ExcelReadWrite.WriteCellData(ExcelReadWrite.writableSheet, row, 3, "FAIL", 4);
					 ExcelReadWrite.WriteCellData(ExcelReadWrite.writableSheet, row, 4, message, 4);
					 ExcelReadWrite.FailValue++;
					}
					 
			 
			}
			 			
		ExcelReadWrite.PieChartReport();		
		ExcelReadWrite.WriteExcel();
		driver.close();
		ExcelReadWrite.OpenOutputExcel();
		}
				

	
	public String login(String Username, String Pwd) throws InterruptedException
	{
		String msg;
		
		
		  driver.get("https://qa1.insightsc3m.com/");
		  driver.findElement(By.id("UserIdentifierClaim")).sendKeys(Username);

		  driver.findElement(By.id("UserPasswordClaim")).sendKeys(Pwd);

		  driver.findElement(By.id("lnklogin")).click();

		  try {
			msg1=driver.getCurrentUrl();
			Thread.sleep(3000);
			System.out.println(" current url "+msg1);
			
		    msg=driver.findElement(By.id("liLogout")).getText();
		 System.out.println(" Message :"+msg);
		  
		  
		  
		  }catch(Exception e)
		  {
			msg=driver.findElement(By.id("loginErrrMsg")).getText();	  
		  }
	  	  
		   return msg;
		  
	}
	
	public String AccountNumber(String Accountnumber) throws InterruptedException
	{
		String msg;
			
	try 
	{	
	
		driver.findElement(By.id("txtSearch")).sendKeys(Accountnumber);

		driver.findElement(By.id("btnGo")).click();
		Thread.sleep(4000);
		
		if(driver.findElement(By.xpath("//*[@id='alert-box-alert-container']")).isDisplayed())
		{
			
		Thread.sleep(2000);	
		msg=driver.findElement(By.xpath("//*[@id='alert-box-alert-container']")).getText();
		System.out.println(" Message :"+msg);  
		
		driver.findElement(By.xpath("//*[@id='alert-box-alert']/div/a")).click();

		}
		else {
			
			msg = "Internal user, Entered account number is correct";	  
		}
	}
	catch(Exception e)
	{
		msg = "External user, User can't enter account number";
	}
	return msg;
	}
}
	
