package com.crm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends PageBase{
	
	WebDriver driver;
	
	@FindBy(name="email")
	WebElement userNameInput;
	
	@FindBy(name="password")
	WebElement passwordInput;
	
	@FindBy(xpath="//div[text()='Login']")
	WebElement loginBtn;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	public HomePage login(String userName, String password) {
		userNameInput.sendKeys(userName);
		passwordInput.sendKeys(password);
		loginBtn.click();
		return new HomePage(this.driver);
	}

}
