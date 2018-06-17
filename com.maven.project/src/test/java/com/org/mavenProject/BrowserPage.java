package com.org.mavenProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserPage {
	static WebDriver driver;

	public static WebDriver SelectBrowser(String browser, String url) {

		if (browser.equalsIgnoreCase("Mozilla")) {

			System.setProperty("webdriver.gecko.driver",
					"F:\\Software\\Library Package\\Mozilla Driver\\geckodriver.exe");
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			dc.setCapability("marionette", true);
			driver = new FirefoxDriver(dc);

		} else if (browser.equalsIgnoreCase("Chrome")) {

			System.setProperty("webdriver.chrome.driver",
					"F:\\Software\\Library Package\\Chrome package\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browser.equalsIgnoreCase("ie")) {

			System.setProperty("webdriver.ie.driver",
					"F:\\Software\\Library Package\\InterExplorer package\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.get(url);
		return driver;
	}

}
