package com.tadashboard.control;

import driver.DriverManager;
import extentreports.ExtentLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static com.tadashboard.common.Constants.*;

public class Action {

    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public static WebDriverWait getWebDriverWait(int timeOut){
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
    }

    public static JavascriptExecutor getExecutorJS(){
        return (JavascriptExecutor) getDriver();
    }

    // Wait Page loaded
    public static void waitForPageLoaded() {

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                    .toString().equals("complete");
        };

        //Get JS is Ready
        boolean jsReady = getExecutorJS().executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            ExtentLogger.info("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                getWebDriverWait(WAIT_EXPLICIT).until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load (Javascript). (" + WAIT_PAGE_LOADED + "s)");
            }
        } else {
            ExtentLogger.info("Javascript is Ready!");
        }
    }

}
