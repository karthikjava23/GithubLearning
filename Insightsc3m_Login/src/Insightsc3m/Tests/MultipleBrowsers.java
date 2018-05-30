package Insightsc3m.Tests;

import java.awt.AWTException;

import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;


public class MultipleBrowsers
{
	public static WebDriver driver=null;
	// Providing insights login credentials
	public static String username="hanumav"; //User Name
	public static String password="Oct#2017"; //Password
	public static String browser="firefox";
	public static void login(String username, String password, String pBrowser) throws Exception
	{
		if(pBrowser.equalsIgnoreCase("firefox")){
		
				System.setProperty("webdriver.firefox.marionette", "D:\\Selenium Tool\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			
			else if(pBrowser.equalsIgnoreCase("chrome")){
			
				System.setProperty("webdriver.chrome.driver", "D:\\Selenium Tool\\chromedriver.exe");
				
				driver = new ChromeDriver();
			}
			else if(pBrowser.equalsIgnoreCase("Edge")){
				
				System.setProperty("webdriver.edge.driver", "D:\\Selenium Tool\\MicrosoftWebDriver.exe");
				
				driver = new EdgeDriver();
			}
			else if(pBrowser.equalsIgnoreCase("IE")){
				
				System.setProperty("webdriver.ie.driver", "D:\\Selenium Tool\\IEDriverServer.exe");
				
				
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			
				capabilities.setCapability (InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				
				driver = new InternetExplorerDriver(capabilities);

				
			}
			else{
				
				throw new Exception("Browser is not correct");
			}
	
		
		
		  driver.manage().window().maximize();
		
		  driver.get("https://qa1.insightsc3m.com/");//Application URL
		  driver.findElement(By.id("UserIdentifierClaim")).sendKeys(username);//Passing UserName Id
		  Thread.sleep(3000);
		  driver.findElement(By.id("UserPasswordClaim")).sendKeys(password);//Passing Password Id
		  Thread.sleep(3000);
		  driver.findElement(By.id("lnklogin")).click();// Login Click Event
	
	 }
	

	public static void main(String[] args) throws Exception {
		browser = "chrome";
		ImageCapturing.login(username, password);
		ImageCapturing.copyImageToLocalDrive();
		Thread.sleep(10000);
		browser = "Edge";
		ImageCapturing.login(username, password);
		Thread.sleep(10000);
		ImageCapturing.copyImageToLocalDrive();
	
}
	

// Take a screenshot
	public static void copyImageToLocalDrive() throws InterruptedException, IOException, AWTException {
	

		
	//WebElement logo=driver.findElement(By.xpath("//a[contains(@id,\"aHomeCard_OtherResources_ProductUPCList\")]//span"));
	WebElement logo=driver.findElement(By.xpath("//*[@id='aHomeCard_Programs&Policies_RetailTradePrograms']/span[1]"));
	
	Screenshot screenshot=new AShot().takeScreenshot(driver,logo);
	ImageIO.write(screenshot.getImage(),"PNG", new File("D:\\Selenium Tool\\Excel Inputs\\Expected Images\\Home Page Product UPC List Imagetesting("+browser+").png"));
	Thread.sleep(2000);
	driver.quit();
   //ImageIO.write(screenshot.getImage(),"PNG", new File("D:\\Selenium Tool\\Excel Inputs\\Expected Images\\Home Page Retail Trade Programs Image.png"));
           
}

}

































