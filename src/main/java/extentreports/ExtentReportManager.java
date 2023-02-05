package extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.tadashboard.common.Constants;
import com.tadashboard.utils.ReportUtils;

import java.util.Objects;

public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static String link = "";

    public static void initReports() {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();
            link = ReportUtils.createExtentReportPath();

            ExtentSparkReporter spark = new ExtentSparkReporter(link);
            extentReports.attachReporter(spark);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle(Constants.REPORT_TITLE);
            spark.config().setReportName(Constants.REPORT_TITLE);
            extentReports.setSystemInfo("Framework Name", Constants.REPORT_TITLE);
            extentReports.setSystemInfo("Author", Constants.AUTHOR);
            System.out.println("Extent Reports is installed.");
        }
    }

    public static void flushReports() {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
        ExtentTestManager.unload();
        ReportUtils.openReports(link);
    }

    public static void createTest(String testCaseName) {
        ExtentTestManager.setExtentTest(extentReports.createTest(testCaseName));
    }

    public static void addScreenShot(Status status, String message) {
        String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);

        ExtentTestManager.getExtentTest().log(status, message,
                ExtentTestManager.getExtentTest().addScreenCaptureFromBase64String(base64Image).getModel().getMedia().get(0));
    }

    synchronized public static void addAuthor(String author) {

        ExtentTestManager.getExtentTest().assignAuthor(author);

    }


}
