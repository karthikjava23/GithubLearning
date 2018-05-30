package Insightsc3m.Tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ImageComparison
{
	public static WebDriver driver=null;
	// Providing insights login credentials
	public static String username="hanumav"; //User Name
	public static String password="Oct#2017"; //Password
	
	public static void login(String username, String password) throws InterruptedException
	{
		
		 System.setProperty("webdriver.chrome.driver", "D:\\Selenium Tool\\chromedriver.exe");		 
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		
		  driver.get("https://qa1.insightsc3m.com/");//Application URL
		  driver.findElement(By.id("UserIdentifierClaim")).sendKeys(username);//Passing UserName Id
		  Thread.sleep(3000);
		  driver.findElement(By.id("UserPasswordClaim")).sendKeys(password);//Passing Password Id
		  Thread.sleep(3000);
		  driver.findElement(By.id("lnklogin")).click();// Login Click Event
		  driver.findElement(By.xpath("//*[@id='btnReportsMaster']")).click();// click on Reports Master in left channel
	 }
	
public static void main(String[] args) throws IndexOutOfBoundsException, BiffException, IOException, RowsExceededException, WriteException, InterruptedException 
{
	ImageComparison.login(username, password);
	
	ImageComparison.compareImage();
	
}


public static void compareImage()  throws IndexOutOfBoundsException, BiffException, IOException, RowsExceededException, WriteException, InterruptedException
{
	ExcelReadWrite.readPath = "D:\\Selenium Tool\\Excel Inputs\\Images.xls"; //Read Path
	ExcelReadWrite.writePath = "D:\\Selenium Tool\\Excel Outputs\\Images.xls"; //Write Path
	ExcelReadWrite.CopyContentToOutputFile();
	Sheet currentSheet = ExcelReadWrite.GetSheet();
	String idSelector = "";                                              
	BufferedImage expectedImage=null;
	ExcelReadWrite.PassValue=0;
	ExcelReadWrite.FailValue=0;
	for(int row = 1; row < currentSheet.getRows(); row++) 
	{
		idSelector = "";
		ExcelReadWrite.LastRowIndex++;
		
		String module = ExcelReadWrite.GetCellData(currentSheet, row, 0);	
		
		switch(module) {

		 case "My Report Center - Payments Image" :
			 idSelector = "//*[@id='divReport_RC001']/article/div[1]/a/img";
			 break;
			 
		 case "My Report Center - Initiative Summary Image" :
			 idSelector = "//*[@id='divReport_RC005']/article/div[1]/a/img";
			 break;
			 
		 case "My Report Center - Mobile Coupon Redemption Image" :
			 idSelector = "//*[@id='divReport_RC015']/article/div[1]/a/img";
			 break;
			 
		 case "My Report Center - Price Promotions Image" :
			 idSelector = "//*[@id='divReport_RC002']/article/div[1]/a/img";
			 break;
			 
		 case "My Report Center - Product Promotions Image" :
			 idSelector = "//*[@id='divReport_RC003']/article/div[1]/a/img";
			 break;
			 
		 case "My Report Center - Special Return Programs Image" :
			 idSelector = "//*[@id='divReport_RC008']/article/div[1]/a/img";
			 break;
			 
			 
		// case "Home Page- Special Return Programs Imae" :
			 
			 //driver.get("https://qa1.insightsc3m.com/Home/Index");
			// idSelector = "//*[@id='aHomeCard_Reports_Payments']/span[1]";
			 //break;

		 }
		
		if(idSelector != "") {		 
			WebElement logo=driver.findElement(By.xpath(idSelector));			
			Screenshot logoImageScreenshot=new AShot().takeScreenshot(driver,logo);
			BufferedImage actualImage=logoImageScreenshot.getImage();
			ImageDiffer imagDiff=new ImageDiffer();
			
			String _path = ExcelReadWrite.GetCellData(currentSheet, row, 1);
			expectedImage=ImageIO.read(new File(_path));
			
			ImageDiff diff=imagDiff.makeDiff(expectedImage,actualImage);
			
			if(diff.hasDiff()) {
				ExcelReadWrite.WriteCellData(ExcelReadWrite.writableSheet, row, 2, "FAIL");
				ExcelReadWrite.PassValue++;
			} else {
				ExcelReadWrite.WriteCellData(ExcelReadWrite.writableSheet, row, 2, "PASS");
				ExcelReadWrite.FailValue++;
			}
		}
		
	}
	ExcelReadWrite.PieChartReport();		
	ExcelReadWrite.WriteExcel();
	driver.close();
	ExcelReadWrite.OpenOutputExcel();
}
}






