package Insightsc3m.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GridComparision {

	
	public static WebDriver driver=null;
	// Providing insights login credentials
	public static String username="mgt046683dua"; //User Name
	public static String password="Welcome@1"; //Password
	
	
	
	
	public void login(String username, String password) throws InterruptedException
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
	 }
	
	
	public static void main(String[] args) throws InterruptedException {
		
		
		GridComparision gc=new GridComparision();
		gc.login(username, password);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement elemnetIP = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='liInvoicesAndPayments']/a")));
		elemnetIP.click();
		Thread.sleep(6000);
		driver.findElement(By.className("checkbox-group")).click();
		Thread.sleep(5000);
			
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
		 jse.executeScript("window.scrollBy(0,700);");
		// jse.executeScript("window.scrollBy(0,-300);");
		 
		// driver.findElement(By.id("dwnld-format-SummaryExcel")).click();
		// Thread.sleep(4000);
		 driver.findElement(By.xpath("//*[@id='btn-dwnld']")).click();
		 
		 Thread.sleep(6000);
		 driver.quit();
		 
		 
		System.out.println("Invoice Number");
		 
		System.out.println("Invoice Type");
		  
		System.out.println("Invoice Amount");
	}
	
	
	
	
	
}
