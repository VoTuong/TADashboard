package com.tadashboard.enums;

import driver.DriverManager;
import driver.browser.ChromeDriver;
import driver.browser.EdgeDriver;
import driver.browser.FirefoxDriver;

public enum WebDriverType {
    CHROME{
        @Override
        public DriverManager getDriverManager() {
            return new ChromeDriver();
        }
    }, FIREFOX{
        @Override
        public DriverManager getDriverManager() {
            return new FirefoxDriver();
        }
    }, EDGE {
        @Override
        public DriverManager getDriverManager() {
            return new EdgeDriver();
        }
    };
    public abstract DriverManager getDriverManager();
}
