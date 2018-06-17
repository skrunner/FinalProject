package com.org.mavenProject;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UtilityClass {
	WebDriver driver;
	static ExtentReports report;
	static ExtentTest logger;

	public UtilityClass(WebDriver driver) {
		this.driver = driver;
	}

	public static void implicit_wait(WebDriver driver, long ImplicitTime) {

		driver.manage().timeouts().implicitlyWait(ImplicitTime, TimeUnit.SECONDS);
	}

	public static String captureScreenshot(WebDriver driver) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String msg = "Success";
		try {
			// now copy the screenshot to desired location using copyFile
			// //method
			FileUtils.copyFile(src, new File("F:\\Software\\Java Selenium\\com.maven.project\\Output\\test.png"));

		} catch (Exception ex) {
			msg = ex.getMessage();
		}
		return msg;
	}

	/*
	 * To implement the logger functionality to get the report generated in HTML
	 * Idea is to use the excel sheet to generate the log step by step 
	 */
	public static String logReport(String testName){
		try{
		report= new ExtentReports("F:\\Software\\Java Selenium\\com.maven.project\\Output\\test.html");
		logger=report.startTest(testName);

		String[][] Sheet2_data = TestMethods.fetchExcelData(
				"F:\\Software\\Java Selenium\\com.maven.project\\Data\\Test1_Backup - Copy.xlsx", "Sheet2");
		for (int i = 1; i <= Sheet2_data.length; i++){
		String step_status = Sheet2_data[i][6];
		if (step_status.equalsIgnoreCase("success")){
			logger.log(LogStatus.INFO, step_status);
		}
		else if(step_status.equalsIgnoreCase("fail")){
			report.endTest(logger);
			report.flush();
		}
		
		}
	}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return testName;
	}
	
	public static String verifyText(WebDriver driver){
		String msg = "success";
		try{
		String[][] Sheet2_data = TestMethods.fetchExcelData(
				"F:\\Software\\Java Selenium\\com.maven.project\\Data\\Test1_Backup - Copy.xlsx", "Sheet2");

		String expectedText = Sheet2_data[5][6];
		String actualText = driver.findElement(By.xpath("s.//*[@id='main-body']/div/div/div[1]/div/h1")).getText();
		
		assertEquals(expectedText, actualText);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return msg;
		}
}
