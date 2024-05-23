package com.crm.qa.util;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.crm.qa.ExtentReportListener.ExtentReportsUtility;
import com.crm.qa.base.BaseTest;

public class TestEventListnersutility implements ITestListener {

    protected static ExtentReportsUtility extentreport = null;

    public void onTestStart(ITestResult result) {
        extentreport.startSingleTestReport(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        extentreport.logTestPassed(result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        extentreport.logTestFailed(result.getMethod().getMethodName());
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        if (driver != null) {
            String screenshotPath = new BaseTest().getScreenshotOfThePage(driver);
            extentreport.logTestScreenshot(screenshotPath);
        }
    }

    public void onStart(ITestContext context) {
        extentreport = ExtentReportsUtility.getInstance();
        extentreport.startExtentReport();
    }

    public void onFinish(ITestContext context) {
        extentreport.endReport();
    }

    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
    	extentreport.logTestSkipped(result.getMethod().getMethodName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }
}
