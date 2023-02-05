package driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public DriverManager() {
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver){
        DriverManager.driver.set(driver);
    }

    public static void quit() {
        DriverManager.getDriver().quit();
        DriverManager.driver.remove();
    }

    public abstract WebDriver createDriver();

    public abstract MutableCapabilities getOptions();
}
