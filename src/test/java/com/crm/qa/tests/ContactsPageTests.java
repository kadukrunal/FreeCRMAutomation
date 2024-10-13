package com.crm.qa.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.CreateContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestListener;
import com.crm.qa.utils.TestUtil;

@Listeners(TestListener.class)
public class ContactsPageTests extends TestBase {

	HomePage homePage;

	@BeforeMethod
	public void setup() {
		initialization();
		LoginPage loginPage = new LoginPage(driver);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void createContactRequiredFields() throws InterruptedException {
		
	}
	
	@DataProvider(name="contactsDataProvider")
	public Object[][] getContactsData() {
		return TestUtil.getTestData("contacts.xlsx");
		}
	
	@Test(dataProvider = "contactsDataProvider")
	public void getContactsTestData(String firstName, String lastName) throws InterruptedException {
		
		CreateContactsPage contactPage = homePage.getCreateContactsPage();
		contactPage.createContactRequiredFields(firstName, lastName);
		Thread.sleep(15000);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
