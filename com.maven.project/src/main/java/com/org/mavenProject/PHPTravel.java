package com.org.mavenProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PHPTravel {
	WebDriver driver;
	LoginMethodsTest login_page;

	@BeforeMethod
	public void beforemethod() {
		SetURL("http://phptravels.org/clientarea.php");
	}
	
	public void SetURL(String URL) {
		driver = BrowserPage.SelectBrowser("chrome", URL);
		login_page = PageFactory.initElements(driver, LoginMethodsTest.class);
	}

	@Test
	public void OrderPHPTravels() throws Exception{
		
		driver.findElement(By.xpath(".//*[@id='Primary_Navbar-Store']/a")).click();
		driver.findElement(By.xpath(".//*[@id='Primary_Navbar-Store-Order_PHPTRAVELS']/a")).click();
		
		driver.findElement(By.xpath(".//*[@id='pid4']")).click();
		driver.findElement(By.xpath(".//*[@id='order-boxes']/div[3]/form/div[2]/button")).click();
		
		driver.findElement(By.xpath(".//*[@id='a8']")).click();
		driver.findElement(By.xpath(".//*[@id='order-boxes']/form/div[3]/button")).click();
		Thread.sleep(500);
	}
	
	@AfterMethod
	public void quitDriver() {
		driver.quit();
	}
	
	
	
}
