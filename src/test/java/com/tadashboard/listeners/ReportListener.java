package com.tadashboard.listeners;

import com.aventstack.extentreports.Status;
import extentreports.ExtentLogger;
import com.tadashboard.helpers.PropertiesHelpers;
import com.tadashboard.helpers.CaptureHelpers;
import driver.DriverManager;
import extentreports.ExtentReportManager;
import org.testng.*;

import static com.tadashboard.common.Constants.*;


public class ReportListener implements ITestListener, ISuiteListener, IInvokedMethodListener{

    public ReportListener() {
    }

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // Before every method in the Test Class
        System.out.println(method.getTestMethod().getMethodName() + " is starting");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // After every method in the Test Class
        System.out.println(method.getTestMethod().getMethodName()+ " is ending");
    }

    @Override
    public void onStart(ISuite iSuite) {
        System.out.println(" ");
        System.out.println("========= INSTALLING CONFIGURATION DATA =========");
        PropertiesHelpers.loadAllFiles();
        ExtentReportManager.initReports();
        System.out.println("========= INSTALLED CONFIGURATION DATA =========");
        System.out.println(" ");
        iSuite.setAttribute("WebDriver", DriverManager.getDriver());
    }

    @Override
    public void onFinish(ISuite iSuite) {
        ExtentReportManager.flushReports();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ExtentReportManager.createTest(getTestDescription(iTestResult));
        ExtentReportManager.addAuthor(AUTHOR);

        ExtentLogger.info("Test case: " + getTestName(iTestResult) + " test is starting...");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ExtentLogger.pass("Test case: " + getTestName(iTestResult) + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        if (SCREENSHOT_FAILED_STEPS.equals("yes")) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }
        ExtentReportManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
        ExtentLogger.fail("Test case: " + getTestName(iTestResult) + " is failed.");
        ExtentLogger.fail(String.valueOf(iTestResult.getThrowable()));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        ExtentLogger.skip("Test case: " + getTestName(iTestResult) + " is skipped.");
    }

}