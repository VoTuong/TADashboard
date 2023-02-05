package driver.browser;

import com.tadashboard.common.Constants;
import driver.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriver extends DriverManager {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.getInstance(DriverManagerType.EDGE).setup();

        return new org.openqa.selenium.edge.EdgeDriver(getOptions());
    }

    @Override
    public EdgeOptions getOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setHeadless(Boolean.parseBoolean(Constants.HEADLESS));

        return edgeOptions;
    }
}
