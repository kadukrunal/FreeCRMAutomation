package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactsPage extends PageBase{
	
	WebDriver driver;
	
	@CacheLookup
	@FindBy(name="first_name")
	WebElement firstName;
	
	@CacheLookup
	@FindBy(name="last_name")
	WebElement lastName;
	
//	@FindBy(css="ui.linkedin.button")
//	WebElement saveBtn;
	
	public CreateContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickSaveBtn() {
		getPageHeaderElement().findElement(By.xpath("//button[@class='ui linkedin button']")).click();
		//saveBtn.click();
	}
	
	public void createContactRequiredFields(String firstname, String lastname) {
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		clickSaveBtn();
	}

}
