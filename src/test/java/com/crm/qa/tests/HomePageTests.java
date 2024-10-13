package com.crm.qa.tests;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestListener;


//BEFORE each test case we must launch browser and login and 
//AFTER each test case we must close the browser
//Why? Because if you don't close the browser after each test and your class has 100s of tests then your browser will be exhausted it's cache will become full and there is greater chance of browser crashing

@Listeners(TestListener.class)
public class HomePageTests extends TestBase{
	
	HomePage homePage;
	
	@BeforeMethod
	public void setup()
	{	
		initialization();
		LoginPage loginPage = new LoginPage(driver);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void homePage_navigationLinks_correct() {
		homePage.clickCalendarLink();
		assertTrue(homePage.getPageHeader().equals("Calendar"));
	}
	
	@Test
	public void homePage_navigateToDealsPage_success() {
		DealsPage dealsPage = homePage.getDealsPage();
		assertTrue(dealsPage.getPageHeaderTitle().equals("Deals"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
