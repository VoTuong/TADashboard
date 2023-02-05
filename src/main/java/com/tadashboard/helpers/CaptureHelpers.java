package com.tadashboard.helpers;

import extentreports.ExtentLogger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.tadashboard.common.Constants;

import java.io.File;
import java.util.Date;

public class CaptureHelpers{

    private CaptureHelpers(){
    }

    public static void captureScreenshot(WebDriver driver, String screenName) {
        try {
            String path = Helpers.getCurrentDir() + Constants.EXPORT_CAPTURE_PATH;
            File file = new File(path);
            if (!file.exists()) {
                ExtentLogger.info("No Folder: " + path);
                file.mkdir();
                ExtentLogger.info("Folder created: " + file);
            }

            ExtentLogger.info("Driver for Screenshot: " + driver);

            TakesScreenshot ts = (TakesScreenshot) driver;

            File source = ts.getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(source, new File(path + "/" + screenName + "_" + Constants.DATE_FORMAT.format(new Date()) + ".png"));
            ExtentLogger.info("Screenshot taken: " + screenName);
            ExtentLogger.info("Screenshot taken current URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

}
