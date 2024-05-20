package com.crm.qa.ExtentReportListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.qa.util.Constants;

public class ExtentReportsUtility {
    private static ExtentReports report;
    private static ExtentSparkReporter sparkReporter;
    private static ExtentTest testLogger;
    private static ExtentReportsUtility extentObject = null;

    private ExtentReportsUtility() {}

    public static ExtentReportsUtility getInstance() {
        if (extentObject == null) {
            extentObject = new ExtentReportsUtility();
        }
        return extentObject;
    }

    public void startExtentReport() {
        if (report == null) {
            sparkReporter = new ExtentSparkReporter(Constants.SPARKS_HTML_REPORT_PATH);
            report = new ExtentReports();
            report.attachReporter(sparkReporter);

            report.setSystemInfo("Host Name", "CRM");
            report.setSystemInfo("Environment", "Automation Testing");
            report.setSystemInfo("User Name", "Jan");

            sparkReporter.config().setDocumentTitle("Test Execution Report");
            sparkReporter.config().setReportName("CRM tests");
            sparkReporter.config().setTheme(Theme.DARK);
        }
    }

    public void startSingleTestReport(String testScript_Name) {
        testLogger = report.createTest(testScript_Name);
    }

    public void logTestInfo(String text) {
        testLogger.info(text);
    }

    public void logTestPassed(String testCaseName) {
        testLogger.pass(MarkupHelper.createLabel(testCaseName + " is passTest", ExtentColor.GREEN));
    }

    public void logTestFailed(String testCaseName) {
        testLogger.fail(MarkupHelper.createLabel(testCaseName + " is failed", ExtentColor.RED));
    }

    public void logTestFailedWithException(Exception e) {
        testLogger.log(Status.FAIL, e);
    }

    public void logTestScreenshot(String path) {
        testLogger.addScreenCaptureFromPath(path);
    }

    public void logTestSkipped(String testCaseName) {
        testLogger.skip(MarkupHelper.createLabel(testCaseName + " is skipped", ExtentColor.ORANGE));
    }

    public void endReport() {
        if (report != null) {
            report.flush();
        }
    }
}
