package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.crm.qa.base.TestBase;

public class PageBase extends TestBase{
	
	@FindBy(xpath = "//div[contains(@class,'ui header')]/span")
	public WebElement pageHeaderTitle;
	
	@FindBy(css=".ui.secondary.page-header")
	WebElement pageHeader;
	
	public String getPageHeaderTitle() {
		return wait.until(ExpectedConditions.visibilityOf(pageHeaderTitle)).getText();
	}
	
	public WebElement getPageHeaderElement() {
		return pageHeader;
	}
	
	public PageBase() {
		PageFactory.initElements(driver, this);
	}

}
