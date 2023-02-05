package com.tadashboard.helpers;

import com.tadashboard.common.Constants;
import driver.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertHelper {

    /**
     * To click on the 'Cancel' button of the alert.
     */
    public static void dismissAlert() {
        waitForAlertVisible();
        DriverManager.getDriver().switchTo().alert().dismiss();
    }

    /**
     * To click on the 'OK' button of the alert.
     */
    public static void acceptAlert() {
        waitForAlertVisible();
        DriverManager.getDriver().switchTo().alert().accept();
    }

    /**
     * To get the alert message.
     */
    public static String getTextAlert() {
        waitForAlertVisible();
        return DriverManager.getDriver().switchTo().alert().getText();
    }

    /**
     * To wait for the alert appear.
     */
    public static void waitForAlertVisible() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Constants.WAIT_PAGE_LOADED));
        wait.until(ExpectedConditions.alertIsPresent());
    }


    public static boolean isAlertPresent() {
        try {
            waitForAlertVisible();
            DriverManager.getDriver().switchTo().alert();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}
