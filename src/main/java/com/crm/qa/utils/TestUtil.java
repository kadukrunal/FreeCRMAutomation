package com.crm.qa.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestUtil {

	// Initializing Wait times at one place
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static long EXPLICIT_WAIT = 20;

	
	public static Object[][] getTestData(String excelFileName) {
		File file = new File(".\\src\\main\\java\\com\\crm\\qa\\testdata\\" + excelFileName);
		Object[][] objectArray = null;
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();
			objectArray = new Object[rowCount][colCount];

			for (int i = 1; i <= rowCount; i++) {
				for (int j = 0; j < colCount; j++) {
					String s = sheet.getRow(i).getCell(j).getStringCellValue();
					objectArray[i - 1][j] = s;
				}
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objectArray;
	}
	
	public static void takeScreenshot(WebDriver driver, String testMethodName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(".\\screenshots\\"+testMethodName+".png"));
	}
	
	
}
