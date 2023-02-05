package extentreports;

import com.aventstack.extentreports.Status;

public class ExtentLogger {

	private ExtentLogger() {}

	public static void pass(String msg) {
		ExtentTestManager.getExtentTest().pass(msg);
	}

	public static void warn(String message) {
		ExtentTestManager.getExtentTest().warning(message);
	}

	public static void fail(String msg) {
		ExtentTestManager.getExtentTest().fail(msg);
	}

	public static void skip(String msg) {
		ExtentTestManager.getExtentTest().skip(msg);
	}

	public static void info(String message) {
		ExtentTestManager.getExtentTest().info(message);
	}

	public static void logMessage(Status status, Object message) {
		ExtentTestManager.getExtentTest().log(status, (Throwable) message);
	}

}
