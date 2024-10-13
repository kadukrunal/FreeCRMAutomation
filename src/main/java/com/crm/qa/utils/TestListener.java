package com.crm.qa.utils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.crm.qa.base.TestBase;

public class TestListener extends TestBase implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("***onTestStart***: "+result.getMethod());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("***onTestSuccess***: "+result.getMethod());
		extentTest.log(Status.PASS, "Test Method Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("***Test FAILED***: "+result.getMethod());
		extentTest.log(Status.FAIL, "Test Method Failed");
		try {
			TestUtil.takeScreenshot(this.driver, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, "Test Method Skipped");
		System.out.println("***onTestSkipped***: "+result.getMethod());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		extentTest.log(Status.FAIL, "Test Method Failed");
		System.out.println("***TEST FAILED WITHIN SUCCESS PERCENTAGE*** ");
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		extentTest.log(Status.FAIL, "Test Method Failed");
		System.out.println("***Test method "+result.getTestName()+ " FAILED WITH TIMEOUT EXCEPTION");
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("***onStart***: "+context.getSuite().getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("***onFinish"+context.getSuite().getName()+"HAS ENDED***");
	}

}
