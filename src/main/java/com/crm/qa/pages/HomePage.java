package com.crm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class HomePage extends PageBase {

	WebDriver driver;
	Actions action;

	@FindBy(xpath = "//div[@role='listbox']/i[@class='settings icon']")
	public WebElement settingsIcon;

	@FindBy(xpath = "//a[@href='/home']")
	public WebElement homeLink;

	@FindBy(xpath = "//a[@href='/calendar']")
	public WebElement calenderLink;

	@FindBy(xpath = "//a[@href='/contacts']")
	public WebElement contactsLink;

	@FindBy(xpath = "//a[@href='/companies']")
	public WebElement companiesLink;

	@FindBy(xpath = "//a[@href='/deals']")
	public WebElement dealsLink;

	@FindBy(xpath = "//a[@href='/contacts']")
	public WebElement contacts;
	
	@FindBy(xpath="//a[@href='/contacts']/following-sibling::button")
	public WebElement addContactBtn;

	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickCalendarLink() {
		wait.until(ExpectedConditions.elementToBeClickable(calenderLink)).click();
	}

	public void clickDealsLink() {
		dealsLink.click();
	}

	public CalendarPage getCalendarPage() {
		clickCalendarLink();
		return new CalendarPage(this.driver);
	}

	public DealsPage getDealsPage() {
		clickDealsLink();
		return new DealsPage(this.driver);
	}

	public String getPageHeader() {
		wait.until(ExpectedConditions.visibilityOf(pageHeaderTitle));
		return pageHeaderTitle.getText();
	}
	
	public void clickAddContactBtn() {
		action.moveToElement(contacts).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(addContactBtn)).click();	
	}
	
	public CreateContactsPage getCreateContactsPage() {
		clickAddContactBtn();
		return new CreateContactsPage(this.driver);
	}

}
