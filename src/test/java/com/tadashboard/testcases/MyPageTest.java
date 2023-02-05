package com.tadashboard.testcases;

import com.tadashboard.base.BaseTest;
import com.tadashboard.enums.enum_ta.GlobalSettingOptions;
import com.tadashboard.objects.Page;
import com.tadashboard.objects.User;
import com.tadashboard.pages.DashBoardPage;
import com.tadashboard.pages.HomePage;
import com.tadashboard.pages.LoginPage;
import com.tadashboard.pages.popup.NewPagePopup;
import extentreports.ExtentLogger;
import com.tadashboard.utils.UserUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MyPageTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    NewPagePopup newPagePopup = new NewPagePopup();
    User validAccount = UserUtils.getValidAccount();

    @Test(description = "DA_MP_TC012: Verify that user is able to add additional pages besides 'Overview' page successfully")
    public void additionalPagesBesidesOverviewPage() {

        Page newPublicRandomPageName = Page.createPublicPageWithRandomName();

        ExtentLogger.info("Login with valid account: " + validAccount.getUsername());
        loginPage.login(validAccount);

        ExtentLogger.info("Create new Public Page with name: " + newPublicRandomPageName.getPageName());
        newPagePopup.createPage(newPublicRandomPageName);

        ExtentLogger.info("Verify '"+ newPublicRandomPageName.getPageName() +"' page is displayed besides 'Overview' page");
        softAssert.assertTrue(dashBoardPage.doesNewPageNextToOverView(newPublicRandomPageName.getPageName()),
                "New Page is not displayed besides 'Overview' page");

        ExtentLogger.info("Post-Condition: Delete newly added main page");
        dashBoardPage.deletePageTab(newPublicRandomPageName.getPageName());
        dashBoardPage.waitForNewlyPageRemoved(newPublicRandomPageName.getPageName());

        ExtentLogger.info("Post-Condition: Logout from TA DashBoard");
        loginPage.logout(validAccount);

        softAssert.assertAll();
    }

    @Test(description = "DA_MP_TC011: Verify that user is unable open more than 1 'New Page' dialog")
    public void openMoreThanOnePage() {
        ExtentLogger.info("Login with valid account: " + validAccount.getUsername() + " on repository " + validAccount.getRepository());
        loginPage.login(validAccount);

        ExtentLogger.info("Go to Global Setting -> Add page");
        homePage.selectOptionOnGlobalSetting(GlobalSettingOptions.ADD_PAGE);
        homePage.waitForNewPagePopUpDisplay();

        ExtentLogger.info("Check 'Add Page' link in Setting disappears");
        softAssert.assertTrue(homePage.isGlobalSettingOptionNotVisible(GlobalSettingOptions.ADD_PAGE),
                "Add Page link in Setting still appears");

        ExtentLogger.info("Post-Condition: Logout from TA DashBoard");
        loginPage.logout(validAccount);

        softAssert.assertAll();
    }

}
