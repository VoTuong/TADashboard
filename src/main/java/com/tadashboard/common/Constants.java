package com.tadashboard.common;

import com.tadashboard.helpers.Helpers;
import com.tadashboard.helpers.PropertiesHelpers;

import java.text.SimpleDateFormat;

public class Constants {

    public static final String VALID_SPECIAL_CHARACTERS = "@";
    public static final String INVALID_SPECIAL_CHARACTERS = "#$%";

    public static final String TEST_REPOSITORY = "TestRepository";
    public static final String EMPTY_USER_ERROR_MESSAGE = "Please enter username!";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    public static final String PROPERTIES_FILE_PATH_ROOT = "src/test/resources/properties/configs.properties";
    public static final String PROJECT_PATH = Helpers.getCurrentDir();

    //DashBoard general Page
    public static final String OVERVIEW_PAGE = "Overview";
    public static final String INVALID_ACCOUNT_ERROR_MESSAGE = "Username or password is invalid";

    public static final String URL = PropertiesHelpers.getValue("URL_LOGIN");
    public static final String REMOTE_URL = PropertiesHelpers.getValue("REMOTE_URL");
    public static final String REMOTE_PORT = PropertiesHelpers.getValue("REMOTE_PORT");
    public static final String REPORT_TITLE = PropertiesHelpers.getValue("REPORT_TITLE");
    public static final String EXTENT_REPORT_NAME = PropertiesHelpers.getValue("EXTENT_REPORT_NAME");
    public static final String EXTENT_REPORT_FOLDER = PropertiesHelpers.getValue("EXTENT_REPORT_FOLDER");
    public static final String EXPORT_CAPTURE_PATH = PropertiesHelpers.getValue("EXPORT_CAPTURE_PATH");
    public static final String AUTHOR = PropertiesHelpers.getValue("AUTHOR");
    public static final String TARGET = PropertiesHelpers.getValue("TARGET");
    public static final String HEADLESS = PropertiesHelpers.getValue("HEADLESS");
    public static final String OVERRIDE_REPORTS = PropertiesHelpers.getValue("OVERRIDE_REPORTS");
    public static final String OPEN_REPORTS_AFTER_EXECUTION = PropertiesHelpers.getValue("OPEN_REPORTS_AFTER_EXECUTION");

    public static final String SCREENSHOT_FAILED_STEPS = PropertiesHelpers.getValue("SCREENSHOT_FAILED_STEPS");

    public static final int WAIT_DEFAULT = Integer.parseInt(PropertiesHelpers.getValue("WAIT_DEFAULT"));
    public static final int WAIT_EXPLICIT = Integer.parseInt(PropertiesHelpers.getValue("WAIT_EXPLICIT"));
    public static final int WAIT_PAGE_LOADED = Integer.parseInt(PropertiesHelpers.getValue("WAIT_PAGE_LOADED"));
    public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + EXTENT_REPORT_FOLDER;
    public static final String EXTENT_REPORT_FILE_NAME = EXTENT_REPORT_NAME + ".html";


}
