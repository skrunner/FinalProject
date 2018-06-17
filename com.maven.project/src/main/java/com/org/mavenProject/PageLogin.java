package com.org.mavenProject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PageLogin extends TestMethods {

	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;
	LoginMethodsTest login_page;
	UtilityClass util;
	PageLogin pl;


	@Test(priority = 1, enabled = false)
	public void launchUrl() {
		driver = BrowserPage.SelectBrowser("chrome", "http://phptravels.org/clientarea.php");
		login_page = PageFactory.initElements(driver, LoginMethodsTest.class);
	}

	@Test(priority = 1, enabled = false)
	public void ValidLogin() {
		try {
			login_page.word_press("admin", "demo123");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 1, enabled = false)
	public void HybridFramework() throws IOException {
		String[][] data = fetchExcelData("C:\\Users\\Suraj Sharma\\Desktop\\Test1.xlsx", null);
		System.out.println("Test executed");
		SendKeys(driver, data[1][2], data[1][3], data[1][4]);
		SendKeys(driver, data[2][2], data[2][3], data[2][4]);
		clickButton(driver, data[3][2], data[3][3]);
	}

	@Test(priority = 1, enabled = true)
	public void Master_function() throws IOException {
		try {

			report = new ExtentReports("F:\\Software\\Java Selenium\\com.maven.project\\Output\\test.html");
			String[][] Sheet1_data = fetchExcelData(
					"F:\\Software\\Java Selenium\\com.maven.project\\Data\\Test1_Backup - Copy.xlsx", "Sheet1");
			String[][] Sheet2_data = fetchExcelData(
					"F:\\Software\\Java Selenium\\com.maven.project\\Data\\Test1_Backup - Copy.xlsx", "Sheet2");

			int rownums = 0;
			String result = "";
			for (int i = 1; i <= Sheet1_data.length; i++) {
				String flag = Sheet1_data[i][2];
				String TestCaseName = Sheet1_data[i][1];
				String testCaseUrl = Sheet1_data[i][4];
				// String URL = Sheet1_data[i][4];
				// SetURL(URL);
				// implicit_wait(driver, 60);

				if (!flag.equals("") && flag.equals("Y")) {
					List<Integer> rownum = TestMethods.findRow(Sheet1_data[i][0].toString());
					for (int k = 0; k < rownum.size(); k++) {
						rownums = rownum.get(k);

						switch (Sheet2_data[rownums][1]) {
						case "sendkeys":
							result = SendKeys(driver, Sheet2_data[rownums][2], Sheet2_data[rownums][3],
									Sheet2_data[rownums][4]);
							logger.log(LogStatus.INFO, "Data has been sent to the input field");

							break;

						case "clickButton":
							result = clickButton(driver, Sheet2_data[rownums][2], Sheet2_data[rownums][3]);
							logger.log(LogStatus.INFO, "Button or link has been clicked");
							break;

						case "mouseOver":
							result = mouseOver(driver, Sheet2_data[rownums][2], Sheet2_data[rownums][3]);
							logger.log(LogStatus.INFO, "mouseOver or link has been clicked");
							break;

						case "captureScreenshot":
							UtilityClass.captureScreenshot(driver);
							logger.log(LogStatus.INFO, "Screenshot has been captured");
							break;
							
						case "startExtentTest":
							logger = report.startTest(TestCaseName);
							logger.log(LogStatus.INFO, "Extent report has been started");
							break;
							
						case "endExtentTest":
							report.endTest(logger);
							report.flush();
							break;
							
						case "launchUrl":
							driver = BrowserPage.SelectBrowser("chrome", testCaseUrl);
							logger.log(LogStatus.INFO, "Browser launch is successfull");

							break;

						case "closeUrl":
							driver.close();
							logger.log(LogStatus.INFO, "Driver is closed successfully");
							break;
							
						case "verifyText":
							result = UtilityClass.verifyText(driver);

						default:
							break;

						}
						UpdateSheetStatus("Sheet2", result, rownums, 6);
					}
				}
				UpdateSheetStatus("Sheet1", "PASSED", i, 3);
			}
		} catch (Exception e) {
			UpdateSheetStatus("Sheet3", e.getMessage(), 1, 1);
			// need to check later>> Sheet3 is not creating with error
		} finally {
			driver.get("F:\\Software\\Java Selenium\\com.maven.project\\Output\\test.html");
			driver.quit();

		}
	}
}
