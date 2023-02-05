package driver;

import com.aventstack.extentreports.Status;
import com.tadashboard.enums.DriverMode;
import com.tadashboard.enums.WebDriverType;
import com.tadashboard.exceptions.TargetNotValidException;
import extentreports.ExtentLogger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.tadashboard.common.Constants;

import java.net.URL;

public class TargetFactory {
    public WebDriver createInstance(String browser) {
        DriverMode driverMode = DriverMode.valueOf(Constants.TARGET.toUpperCase());
        WebDriver webdriver;

        switch (driverMode) {
            case LOCAL:
                webdriver = WebDriverType.valueOf(browser.toUpperCase()).getDriverManager().createDriver();
                break;
            case REMOTE:
                webdriver = createRemoteInstance(WebDriverType.valueOf(browser.toUpperCase()).getDriverManager().getOptions());
                break;
            default:
                throw new TargetNotValidException(driverMode.toString());
        }
        return webdriver;
    }

    private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            String gridURL = String.format("http://%s:%s", Constants.REMOTE_URL, Constants.REMOTE_PORT);

            remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
        } catch (java.net.MalformedURLException e) {
            ExtentLogger.warn("Grid URL is invalid or Grid Port is not available");
            ExtentLogger.logMessage(Status.valueOf(String.format("Browser: %s", capability.getBrowserName())), e);
        } catch (IllegalArgumentException e) {
            ExtentLogger.logMessage(Status.valueOf(String.format("Browser %s is not valid or recognized", capability.getBrowserName())), e);
        }

        return remoteWebDriver;
    }
}
