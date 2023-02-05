package com.tadashboard.control;

import driver.DriverManager;
import extentreports.ExtentLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;
import java.util.stream.Collectors;

import static com.tadashboard.common.Constants.WAIT_DEFAULT;
import static com.tadashboard.common.Constants.WAIT_EXPLICIT;
import static com.tadashboard.control.Action.waitForPageLoaded;

public class Element {

    private String locator;
    private String dynamicLocator;

    public Element(String locator) {
        this.locator = locator;
        this.dynamicLocator = locator;
    }

    private String locator() {
        return this.locator;
    }

    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public String getLocator() {
        return this.locator;
    }

    private By by(String locator) {
        String body = this.locator.replaceAll("[\\w\\s]*=(.*)", "$1").trim();
        String type = this.locator.replaceAll("([\\w\\s]*)=.*", "$1").trim();
        switch (type) {
            case "css":
                return By.cssSelector(body);
            case "id":
                return By.id(body);
            case "link":
                return By.linkText(body);
            case "xpath":
                return By.xpath(body);
            case "text":
                return By.xpath(String.format("//*[contains(text(), '%s')]", body));
            case "name":
                return By.name(body);
            default:
                return By.xpath(locator);
        }
    }

    public void setDynamicValue(Object... args) {
        this.locator = String.format(this.dynamicLocator, args);
    }

    public WebElement findElement() {
        return getDriver().findElement(by(locator()));
    }

    public List<WebElement> findElements() {
        return getDriver().findElements(by(locator()));
    }

    public void selectOptionByText(String value) {
        ExtentLogger.info("Select '" + value + "' in combo box" + this.locator);
        new Select(findElement()).selectByVisibleText(value);
    }

    public void selectOptionByValue(String value) {
        ExtentLogger.info("Select '" + value + "' in combo box" + this.locator);
        new Select(findElement()).selectByValue(value);
    }

    public void clearText() {
        ExtentLogger.info("Clear value in text box " + this.locator);
        findElement().clear();
    }

    public void inputValue(String value) {
        ExtentLogger.info("Set '" + value + "' in text box " + this.locator);
        findElement().sendKeys(value);
    }

    public void waitForDisplay() {
        waitForDisplay(WAIT_DEFAULT);
    }

    public void waitForDisplay(int timeOutInSeconds) {
        try {
            Action.getWebDriverWait(timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(by(locator())));
        } catch (NoSuchElementException e1) {
            ExtentLogger.warn(String.format("waitForDisplay: Element is not visible: %s", this.locator));
        } catch (Exception e) {
            ExtentLogger.warn(String.format("waitForDisplay: Has error with control '%s': %s", this.locator,
                    e.getMessage()));
        }
    }

    public boolean verifyElementToBeClickable() {
        waitForPageLoaded();

        try {
            Action.getWebDriverWait(WAIT_EXPLICIT).until(ExpectedConditions.elementToBeClickable(by(locator())));
            return true;
        } catch (Exception e) {
            ExtentLogger.info(e.getMessage());
            return false;
        }
    }

    public boolean verifyElementPresent(int timeout) {
        waitForPageLoaded();

        try {
            Action.getWebDriverWait(timeout).until(ExpectedConditions.presenceOfElementLocated(by(locator())));
            return true;
        } catch (Exception e) {
            ExtentLogger.info("The element does NOT present. " + e.getMessage());
            Assert.fail("The element does NOT present. " + e.getMessage());
            return false;
        }
    }

    public boolean verifyElementNotPresent(int timeout) {
        waitForPageLoaded();

        try {
            Action.getWebDriverWait(timeout).until(ExpectedConditions.presenceOfElementLocated(by(locator())));
            ExtentLogger.info("Element is present " + this.locator);
            Assert.fail("The element presents. " + this.locator);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public boolean verifyElementNotClickable(int timeout) {
        waitForPageLoaded();

        try {
            Action.getWebDriverWait(timeout).until(ExpectedConditions.elementToBeClickable(by(locator())));
            ExtentLogger.info("Element is present " + this.locator);
            Assert.fail("The element presents. " + this.locator);
            return false;
        } catch (Exception e) {
            return true;
        }
    }


    public boolean verifyElementVisible(int timeout) {
        waitForPageLoaded();

        try {
            Action.getWebDriverWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(by(locator())));
            return true;
        } catch (Exception e) {
            ExtentLogger.info("The element is not visible. " + e.getMessage());
            Assert.fail("The element is not visible. " + this.locator);
            return false;
        }
    }

    public boolean verifyElementVisible(int timeout, String message) {
        waitForPageLoaded();

        try {
            Action.getWebDriverWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(by(locator())));
            return true;
        } catch (Exception e) {
            if (message.isEmpty()) {
                ExtentLogger.info("The element is not visible. " + this.locator);
                Assert.fail("The element is not visible. " + this.locator);
            } else {
                ExtentLogger.info(message + this.locator);
                Assert.fail(message + this.locator);
            }
            return false;
        }
    }

    public WebElement waitForElementPresent(int timeOut) {
        waitForPageLoaded();

        try {
            return Action.getWebDriverWait(WAIT_EXPLICIT).until(ExpectedConditions.presenceOfElementLocated(by(locator())));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element to exist. " + this.locator);
            ExtentLogger.warn(("Timeout waiting for the element to exist. " + this.locator));
        }
        return null;
    }

    public boolean verifyElementPresent(By by, int timeout) {
        waitForPageLoaded();

        try {
            Action.getWebDriverWait(timeout).until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (Exception e) {
            ExtentLogger.info("The element does NOT present. " + e.getMessage());
            Assert.fail("The element does NOT present. " + e.getMessage());
            return false;
        }
    }

    public void submit() {
        findElement().submit();
    }

    public void click() {
        ExtentLogger.info("Click on the element " + this.locator);
        findElement().click();
    }

    public String getTagName() {
        return findElement().getTagName();
    }

    public String getAttribute(String name) {
        return findElement().getAttribute(name);
    }

    public boolean isSelected() {
        return findElement().isSelected();
    }

    public boolean isEnabled() {
        return findElement().isEnabled();
    }

    public String getText() {
        return findElement().getText();
    }

    public boolean verifyElementText(String text) {

        return getText().trim().equals(text.trim());
    }

    public void waitForDisappear(int timeOutInSeconds) {
        try {
            Action.getWebDriverWait(timeOutInSeconds).until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(by(locator()))));
        } catch (NoSuchElementException e1) {
            ExtentLogger.warn(String.format("waitForDisplay: Element is not visible: %s", this.locator));
        } catch (Exception e) {
            ExtentLogger.warn(String.format("waitForDisplay: Has error with control '%s': %s", this.locator,
                    e.getMessage()));
        }
    }

    public boolean isVisible() {
        try {
            return (Action.getWebDriverWait(WAIT_DEFAULT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by(locator()))) != null);
        } catch (Exception e) {
            ExtentLogger.warn(String.format("IsVisible: Has error with control '%s': %s", this.getLocator(), e.getMessage()));
            return false;
        }
    }

    public void moveToElement() {
        waitForDisplay();

        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(findElement()).build().perform();
        } catch (Exception e) {
            ExtentLogger.warn(e.getMessage());
        }
    }

    public void selectCheckBox(boolean select) {
        waitForDisplay();
        if (findElement().isSelected() != select) {
            findElement().click();
        }
    }

    public List<String> getOptions() {
        waitForDisplay();
        Select selectCbb = new Select(findElement());
        return selectCbb.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void selectByPartialText(String text) {
        waitForDisplay();
        this.getChildElement(String.format(".//option[contains(text(),'%s')]", text)).click();
    }

    private WebElement getChildElement(String xpath) {
        return findElement().findElement(By.xpath(xpath));
    }


}
