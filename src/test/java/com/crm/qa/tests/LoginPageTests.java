package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestListener;

@Listeners(TestListener.class)
public class LoginPageTests extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	Integer i = 0;
	
	@BeforeMethod
	public void setup() {
		initialization();
		loginPage = new LoginPage(TestBase.driver);
	}
	
	@Test
	public void loginPage_loginWithValidCred_Success() {
		homePage = loginPage.login("krunalkadu.qa@gmail.com", "Krunal1233");
		Assert.assertFalse(homePage.settingsIcon.isDisplayed());
	}
	
	@Test(timeOut = 1000)
	public void testTimeOutinItestListeners() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(invocationCount = 4, successPercentage = 80)
	public void testOnTestFailureWithinTestPercentage() {
		System.out.println("Inside Test method");
		if(i%2 == 0) {
			Assert.assertTrue(false);
		}
		i = i + 1;		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
