package Listenerpack;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import utilitise.ExtentReporter;
import utilitise.Readxlsdata;

public class MyListerners implements ITestListener {
	ExtentReports extentreport;
	ExtentTest extenttest;
	
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
		extentreport= ExtentReporter.generateExtentReport();
			System.out.print("onstart");
	}


	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String testname=result.getName();
		
		extenttest=extentreport.createTest(testname);
		extenttest.log(Status.INFO,testname +"stated executing");
		System.out.print(testname + "onTestStart");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		String testname=result.getName();
		extenttest.log(Status.PASS,testname +"got successfuly executing");
		System.out.print(testname + "onTestSuccess");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destinationScreenshotPath = Readxlsdata.captureScreenshot(driver,result.getName());
		
		extenttest.addScreenCaptureFromPath(destinationScreenshotPath);
		extenttest.log(Status.INFO,result.getThrowable());
		extenttest.log(Status.FAIL,result.getName()+" got failed");
		
	}


	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String testname=result.getName();
		System.out.print(testname + "test skipped");
		System.out.print(result.getThrowable());
	}

		
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.print("Finished");
		extentreport.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}
