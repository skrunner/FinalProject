package com.org.mavenProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginMethodsTest {

	WebDriver driver;

	public LoginMethodsTest(WebDriver driver) {
		this.driver = driver;
	}

	
	@FindBy(id = "user_login")
	WebElement username;

	@FindBy(id = "user_pass")
	WebElement password;

	@FindBy(id = "wp-submit")
	WebElement submit_Button;
	
	By submit_Button1 = By.id("wp-submit");

	public void word_press(String uid, String Pass) {
		username.sendKeys(uid);
		password.sendKeys(Pass);
		submit_Button.click();
	}
	
}