package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.qa.utils.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static ChromeOptions chromeOptions;
	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;

	public TestBase() {
		File file = new File(".\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--remote-allow-origins=*");

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");

			driver = new ChromeDriver(chromeOptions);
			
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		//***Initialize Explicit wait, Implicit Wait and PageLoad Timeouts here at Global level***:
		wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.EXPLICIT_WAIT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		//** Sets the amount of time to wait for a page to load before throwing an error.
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.get(prop.getProperty("url"));
		
		

	}
	
	@BeforeSuite
	public void beforeSuiteSetup() {
		extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\extentReports\\extentReport.html");
		extentSparkReporter.config().setDocumentTitle("Automation Test Results for CRM Product");
		extentSparkReporter.config().setReportName("Automation Test Report for CRM Product");
		extentSparkReporter.config().setTheme(Theme.STANDARD);
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
	}
	
	@AfterSuite
	public void afterSuiteTearDown() {
	       extentReports.flush();
	}
}
