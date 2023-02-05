package driver.browser;

import com.tadashboard.common.Constants;
import driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriver extends DriverManager {
    @Override
    public WebDriver createDriver() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();

        return new org.openqa.selenium.chrome.ChromeDriver(getOptions());
    }

    @Override
    public ChromeOptions getOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.setHeadless(Boolean.parseBoolean(Constants.HEADLESS));

        return chromeOptions;
    }
}
