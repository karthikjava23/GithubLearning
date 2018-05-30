package Insightsc3m.Tests;

import java.awt.AWTException;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;


public class ImageCapturing
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
		
		 
		  //driver.findElement(By.id("btnDocument")).click();// Login Click Event
		  //JavascriptExecutor jse = (JavascriptExecutor)driver;
		 //jse.executeScript("window.scrollBy(0,200);");
		  //jse.executeScript("window.scrollBy(0,-400);");
		
	 }
	

	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
	ImageCapturing.login(username, password);
	ImageCapturing.copyImageToLocalDrive();
	
}

// Take a screenshot
	public static void copyImageToLocalDrive() throws InterruptedException, IOException, AWTException {
	

		
	//WebElement logo=driver.findElement(By.xpath("//a[contains(@id,\"aHomeCard_OtherResources_ProductUPCList\")]//span"));
		
		Thread.sleep(4000);
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
		 jse.executeScript("window.scrollBy(0,200);");
		 Thread.sleep(4000);
	WebElement logo=driver.findElement(By.xpath("//*[@id='aHomeCard_Reports_PricePromotions']/span[1]"));
	Thread.sleep(4000);
	
	Screenshot screenshot=new AShot().takeScreenshot(driver,logo);
	Thread.sleep(4000);
	ImageIO.write(screenshot.getImage(),"PNG", new File("D:\\Selenium Tool\\Excel Inputs\\Expected Images\\Home Page.png"));
	Thread.sleep(2000);
	driver.quit();
   //ImageIO.write(screenshot.getImage(),"PNG", new File("D:\\Selenium Tool\\Excel Inputs\\Expected Images\\Home Page Retail Trade Programs Image.png"));
           
}

}

































